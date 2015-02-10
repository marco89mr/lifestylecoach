package lsc.businesslogic.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import lsc.rest.filter.Filter;
import lsc.rest.model.Base;
import lsc.rest.model.DataCollection;
import lsc.rest.model.Deadline;
import lsc.rest.model.Deadline.Status;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Function;
import lsc.rest.model.Goal.Interval;
import lsc.rest.model.Goal.Operator;
import lsc.rest.model.Goal.Perc;
import lsc.rest.model.Goal.Reference;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.Statistic;
import lsc.rest.model.StatisticData;
import lsc.rest.model.User;
import lsc.storage.rest.client.StorageClient;

//Service Implementation

@WebService(endpointInterface = "lsc.businesslogic.ws.LSCLogic", serviceName="LSCLogic")
public class LSCLogicImpl implements LSCLogic {
	
	
	
	@Override
	public NotificationCollection check_record(RecordComplex record) {
		System.out.println("SOAP check_record("+record.getId()+")");
		DeadlineCollection deadlineCollection
			= StorageClient.deadline.getAll(Filter.deadline
											.record_type( record.getType() )
											.include_date( record.getDate() )
											.user_id(user.getId())
											.status(Status.active)		);
	for(Deadline d : deadlineCollection){
		update_deadline_actual_value_and_status(StorageClient.goal.getById(d.getGoalId()), d);
	}
	return null;
	}
	
	@Override
	public NotificationCollection check_deadline(Deadline deadline) {
		System.out.println("SOAP check_deadline("+deadline.getId()+")");
		update_deadline_actual_value_and_status(StorageClient.goal.getById(deadline.getGoalId()), deadline);
		return null;
	}
	
	@Override
	public NotificationCollection check_today(User user) {
		System.out.println("SOAP check_today("+user.getId()+")");
		DeadlineCollection deadlineCollection
			= StorageClient.deadline.getAll(Filter.deadline
											.user_id(user.getId())
											.status(Status.active)		);
		for(Deadline d : deadlineCollection){
			update_deadline_actual_value_and_status(StorageClient.goal.getById(d.getGoalId()), d);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void post_new_notification(int user_id, int deadline_id, String type, String message ) {
		Notification notification = new Notification();
		notification.setDate( Base._formatDate( new Date() ) );
		notification.setUserId( user_id );
		notification.setDeadlineId( deadline_id );
		notification.setType(type);
		notification.setMessage(message);
		notification.setStatus(Notification.Status.unread);
		StorageClient.notification.post(notification);
	}
	
	
	
	
	
	
	
	
	
	
	
	// accepts only deadline with status active
	// return true if status is changed to succeeded
	// if status remains unchanged it returns false
	public boolean update_deadline_actual_value_and_status(Goal goal, Deadline deadline) {
		// check status
		if(deadline.getStatus()!=Status.active)
			return false;
		// actual_goal_value
		float actual_value = compute_deadline_actual_value(	deadline.getStartDate(),
															deadline.getEndDate(),
															goal.getUserId(),
															goal.getRecordType(),
															goal.getDataName(),
															goal.getFunction(),
															goal.getReference(),
															goal.getPerc() );
		// test goal
		float target_value = Float.parseFloat( goal.getValue() );
		Operator operator = goal.getOperator();
		boolean result = false;
		switch(operator){
			case larger:	result = actual_value >= target_value;
			case smaller:	result = actual_value <= target_value;
			case equals:	result = actual_value == target_value;
		}
		// update deadline
		deadline.setActualValue( ""+actual_value );
		deadline.setStatus(( result ? Status.succeeded : Status.active ));
		StorageClient.deadline.put(deadline);
		// notification
		if(result)
			post_new_notification(	goal.getUserId(),
									deadline.getId(),
									"deadline",
									"Good, deadline suceeded: "+"DEADLINEtoTEXT"	);
		return result;
	}
	
	
	
	public float compute_deadline_actual_value( String 		start_date,
												String 		end_date,
												int 		user_id,
												String 		record_type,
												String 		data_name,
												Function 	function,
												Reference 	reference,
												Perc 		perc	) {
		// compute passed_date
		String passed_date = compute_passed_date(start_date, end_date);
		// compute statistic
		Statistic passed_stat = compute_statistic(	user_id,
													record_type,
													data_name,
													passed_date,
													start_date,
													Interval.fixed,
													function		);
		Statistic actual_stat = compute_statistic(	user_id,
													record_type,
													data_name,
													start_date,
													end_date,
													Interval.fixed,
													function		);
		float passed_value = Float.parseFloat( passed_stat.getDatas().get(0).getValue() );
		float actual_value = Float.parseFloat( actual_stat.getDatas().get(0).getValue() );
		// compute actual_goal_value
		float actual_goal_value = 0;
		switch(reference){
			case target:
				switch(perc){
					case abs:	actual_goal_value = actual_value;
					case perc:	actual_goal_value = actual_value / (actual_value+passed_value) * 100;
				}
			case increment:
				switch(perc){
					case abs:	actual_goal_value = actual_value - passed_value;
					case perc:	actual_goal_value = (actual_value-passed_value)/(actual_value+passed_value) * 100;
				}
			case decrement:
				switch(perc){
					case abs:	actual_goal_value = passed_value - actual_value;
					case perc:	actual_goal_value = (passed_value-actual_value)/(actual_value+passed_value) * 100;
				}
		}
		return actual_goal_value;
	}
	
	
	
	public String compute_passed_date(String start_date, String end_date) {
		Date start = Base._parseDate( start_date );
		Date end = Base._parseDate( end_date );
		long lenght_ms = end.getTime() - start.getTime();
		Date passed = new Date();
		passed.setTime( start.getTime() - lenght_ms );
		return Base._formatDate( passed );
	}
	
	
	
	
	
	
	
	
	public void auto_start_goal(Goal goal) {
		// AUTO START NEW GOAL
		if( goal.getRepeat()!=Interval.fixed ) {
			Date now = new Date();
			Date end = new Date();
			end.setTime( now.getTime() + goal.getRepeat().getMs() );
			Deadline deadline = new Deadline();
			deadline.setStartDate(Base._formatDate(now));
			deadline.setEndDate(Base._formatDate(end));
			deadline.setStatus(Status.active);
			deadline.setGoalId(goal.getId());/*
			deadline.setActualValue( ""+compute_deadline_actual_value(	deadline.getStartDate(),
																		deadline.getEndDate(),
																		goal.getUserId(),
																		goal.getRecordType(),
																		goal.getDataName(),
																		goal.getFunction(),
																		goal.getReference(),
																		goal.getPerc()		) );*/
			StorageClient.deadline.post(deadline);
		}
	}
	
	
	
	public void close_and_repeat_deadline(Goal goal, DeadlineCollection deadlines) {
		// CLOSE PASSED DEADLINE AND START A NEW ONE
		Deadline last = deadlines.get( deadlines.size()-1 );
		Date now = new Date();
		Date end = Base._parseDate( last.getEndDate() );
		if( last.getStatus()==Status.active && now.after(end) ){
			update_deadline_actual_value_and_status(goal, last);
			if( goal.getRepeat() != Interval.fixed ) {
				Date new_end = new Date();
				new_end.setTime( now.getTime() + goal.getRepeat().getMs() );
				Deadline deadline = new Deadline();
				deadline.setStartDate( Base._formatDate(now) );
				deadline.setEndDate( Base._formatDate(new_end) );
				deadline.setStatus(Deadline.Status.active);
				deadline.setGoalId( goal.getId() );
				StorageClient.deadline.post(deadline);
			}
		}
		// Notification
		// TODO
		//check_today( goal.getId() );
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Statistic compute_statistic(	int user_id,
										String record_type,
										String field_name,
										String from,
										String to,
										Interval on_interval,
										Goal.Function function) {
		System.out.println("---> compute_statistic");
		
		// compute times intervals
		Date from_date = Base._parseDate(from);
		Date to_date = Base._parseDate(to);
		long milliseconds = to_date.getTime() - from_date.getTime();
		long ms_per_interval = on_interval.getMs();
		long intervals = (int) milliseconds / ms_per_interval + 1;
		if(ms_per_interval==0) {
			ms_per_interval = milliseconds;
			intervals = 1;
		}
		// compute statistic datas
		List<StatisticData> statisticDataList = new ArrayList<StatisticData>();
		Date date = new Date();
		
		for(int i=0; i<intervals; i++) {
			//a
			date.setTime( from_date.getTime() + i * ms_per_interval );
			String a = Base._formatDate(date);
			//b
			date.setTime( from_date.getTime() + i * ms_per_interval );
			String b = Base._formatDate(date);
			//get
			DataCollection data_collection = StorageClient.data.getAll( Filter.data
					.user_id(user_id)
					.record_type(record_type)
					.data_name(field_name)
					.fromdate(a)
					.todate(b)					);
			//elaborate
			Float value = elaborateDataCollection(function, data_collection);
			//compose
			StatisticData statisticData = new StatisticData();
			statisticData.setValue( Float.toString(value) );
			statisticData.setFromDate( a );
			statisticData.setToDate( b );
			//add
			statisticDataList.add( statisticData );
		}
		
		// Compose statistic
		Statistic statistic = new Statistic();
		statistic.setUserId( user_id );
		statistic.setRecordType( record_type );
		statistic.setFieldName( field_name );
		statistic.setFromDate( from );
		statistic.setToDate( to );
		statistic.setOnInterval( on_interval );
		statistic.setFunction( function );
		statistic.setDatas( statisticDataList );
		statistic.setAverage( ""+statistic._average() );
		statistic.setCumulative( ""+statistic._sum() );
		//statistic.setMax( ""+statistic._max() );
		//statistic.setMin( ""+statistic._min() );
		
		return statistic;
	}
	public Float elaborateDataCollection(Goal.Function function, DataCollection data_collection) {
		switch(function){
		case average:
			return data_collection._sum();
		case sum:
			return data_collection._average();
		case max:
			return Float.parseFloat( data_collection._max().getValue() );
		case min:
			return Float.parseFloat( data_collection._min().getValue() );
		case last:
			return Float.parseFloat( data_collection.get(data_collection.size()).getValue() );
		case first:
			return Float.parseFloat( data_collection.get(0).getValue() );
		}
		return null;
	}
    
	
	
	
	
	
	
	
	
	public Statistic compute_complete_statistic(	int user_id,
													String record_type,
													String field_name,
													String from,
													String to,
													Interval on_interval,
													Goal.Function function) {
		System.out.println("---> compute_statistic");
		
		// compute times intervals
		Date from_date = Base._parseDate(from);
		Date to_date = Base._parseDate(to);
		long milliseconds = to_date.getTime() - from_date.getTime();
		long ms_per_interval = on_interval.getMs();
		long intervals = (int) milliseconds / ms_per_interval + 1;
		if(ms_per_interval==0) {
			ms_per_interval = milliseconds;
			intervals = 1;
		}
		// compute statistic datas
		List<StatisticData> statisticDataList = new ArrayList<StatisticData>();
		Date date = new Date();
		
		for(int i=0; i<intervals; i++) {
			//a
			date.setTime( from_date.getTime() + i * ms_per_interval );
			String a = Base._formatDate(date);
			//b
			date.setTime( from_date.getTime() + i * ms_per_interval );
			String b = Base._formatDate(date);
			//get
			DataCollection data_collection = StorageClient.data.getAll( Filter.data
					.user_id(user_id)
					.record_type(record_type)
					.data_name(field_name)
					.fromdate(a)
					.todate(b)					);
			//elaborate
			Float average = elaborateDataCollection(Function.average, data_collection);
			Float sum = elaborateDataCollection(Function.sum, data_collection);
			Float max = elaborateDataCollection(Function.max, data_collection);
			Float min = elaborateDataCollection(Function.min, data_collection);
			Float last = elaborateDataCollection(Function.last, data_collection);
			Float first = elaborateDataCollection(Function.first, data_collection);
			//compose
			StatisticData statisticData = new StatisticData();
			statisticData.setValue( Float.toString(value) );
			statisticData.setFromDate( a );
			statisticData.setToDate( b );
			//add
			statisticDataList.add( statisticData );
		}
		
		// Compose statistic
		Statistic statistic = new Statistic();
		statistic.setUserId( user_id );
		statistic.setRecordType( record_type );
		statistic.setFieldName( field_name );
		statistic.setFromDate( from );
		statistic.setToDate( to );
		statistic.setOnInterval( on_interval );
		statistic.setFunction( function );
		statistic.setDatas( statisticDataList );
		statistic.setAverage( ""+statistic._average() );
		statistic.setCumulative( ""+statistic._sum() );
		//statistic.setMax( ""+statistic._max() );
		//statistic.setMin( ""+statistic._min() );
		
		return statistic;
	}
    
	
	public Statistic compute_complete_statistic(	int user_id,
			String record_type,
			String field_name,
			String from,
			String to,
			Interval on_interval,
			Goal.Function function) {
System.out.println("---> compute_statistic");

// compute times intervals
Date from_date = Base._parseDate(from);
Date to_date = Base._parseDate(to);
long milliseconds = to_date.getTime() - from_date.getTime();
long ms_per_interval = on_interval.getMs();
long intervals = (int) milliseconds / ms_per_interval + 1;
if(ms_per_interval==0) {
ms_per_interval = milliseconds;
intervals = 1;
}
// compute statistic datas
List<StatisticData> statisticDataList = new ArrayList<StatisticData>();
Date date = new Date();

for(int i=0; i<intervals; i++) {
//a
date.setTime( from_date.getTime() + i * ms_per_interval );
String a = Base._formatDate(date);
//b
date.setTime( from_date.getTime() + i * ms_per_interval );
String b = Base._formatDate(date);
//get
DataCollection data_collection = StorageClient.data.getAll( Filter.data
.user_id(user_id)
.record_type(record_type)
.data_name(field_name)
.fromdate(a)
.todate(b)					);
//elaborate
Float average = elaborateDataCollection(Function.average, data_collection);
Float sum = elaborateDataCollection(Function.sum, data_collection);
Float max = elaborateDataCollection(Function.max, data_collection);
Float min = elaborateDataCollection(Function.min, data_collection);
Float last = elaborateDataCollection(Function.last, data_collection);
Float first = elaborateDataCollection(Function.first, data_collection);
//compose
StatisticData statisticData = new StatisticData();
statisticData.setValue( Float.toString(value) );
statisticData.setFromDate( a );
statisticData.setToDate( b );
//add
statisticDataList.add( statisticData );
}

// Compose statistic
Statistic statistic = new Statistic();
statistic.setUserId( user_id );
statistic.setRecordType( record_type );
statistic.setFieldName( field_name );
statistic.setFromDate( from );
statistic.setToDate( to );
statistic.setOnInterval( on_interval );
statistic.setFunction( function );
statistic.setDatas( statisticDataList );
statistic.setAverage( ""+statistic._average() );
statistic.setCumulative( ""+statistic._sum() );
//statistic.setMax( ""+statistic._max() );
//statistic.setMin( ""+statistic._min() );

return statistic;
}
	
}
