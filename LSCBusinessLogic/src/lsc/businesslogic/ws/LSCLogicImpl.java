package lsc.businesslogic.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jws.WebService;

import lsc.localdatabase.client.ClientFactory;
import lsc.localdatabase.client.DataFilter;
import lsc.localdatabase.model.Base;
import lsc.localdatabase.model.DataCollection;
import lsc.localdatabase.model.Deadline;
import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.model.Data;
import lsc.localdatabase.parser.Parser;
import lsc.localdatabase.utils.BaseUtils;
import lsc.businesslogic.transfer.AggregatedData;
import lsc.businesslogic.transfer.DataRecord;
import lsc.businesslogic.transfer.RecordData;
import lsc.businesslogic.transfer.Statistic;

//Service Implementation

@WebService(endpointInterface = "lsc.businesslogic.ws.LSCLogic", serviceName="LSCLogic")
public class LSCLogicImpl implements LSCLogic {
	
	@Override
	public int register_record(RecordData record_data) {
		System.out.println("SOAP register_record()");
		
		// Record
		Record record = new Record();
		record.setType( record_data.getType() );
		record.setDate( record_data.getDate() );
		record.setUser( ClientFactory.user.getById(record_data.getUserId()) );
		// POST
		ClientFactory.record.post(record);
		Parser.parse(record);
		
		// Data
		for( DataRecord d : record_data.getData() ){
			Data data = new Data();
			//data.setId( ClientFactory.data.getAll("record_id="+record_data.getId()+"&name=").getList().get(0).getId() );
			data.setName( d.getName() );
			data.setRecord( record );
			data.setValue( d.getValue() );
			// POST
			ClientFactory.data.post(data);
		}
		
		return 1;
	}

	/*
	@Override
	public GoalDeadline read_goal(int goal_id) {
		System.out.println("---> read_goal "+goal_id);
		return 0;
	}
	*/
	
	@Override
	public int register_goal(Goal goal, String end_date) {
		System.out.println("SOAP register_goal() "+goal.getRecord_type()+
												" "+goal.getData_name()+
												" "+goal.getReapeat()		);
		// Goal
		ClientFactory.goal.post(goal); //it should also GET on the created resource
		Parser.parse(goal);
		// Deadline
		Deadline deadline = new Deadline();
		deadline._setStart_date( new GregorianCalendar().getTime() );
		deadline.setEnd_date( end_date );
		deadline.setStatus("active");
		deadline.setGoal( goal );
		deadline.setNotification(null);
		deadline.setRecord(null);
		// POST
		ClientFactory.deadline.post(deadline);
				
		return 1;
	}

	@Override
	public int complete_todo(int todo_id) {
		System.out.println("---> complete_todo "+todo_id);
		ClientFactory.todo.getById(todo_id).setStatus("done");
		return 0;
	}

	@Override
	public int update_today_goal_and_notification() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public Statistic compute_statistic(	int user_id,
										String record_type,
										String field_name,
										String from,
										String to,
										String on_interval,
										String function) {
		System.out.println("---> compute_statistic");
		Statistic statistic = new Statistic();
		statistic.setUserId(user_id);
		statistic.setRecord_type(record_type);
		statistic.setField_name(field_name);
		statistic.setFrom_date(from);
		statistic.setTo_date(to);
		statistic.setOn_interval(on_interval);
		statistic.setFunction(function);
		//compute aggregated data
		List<AggregatedData> aggregated_datas = new ArrayList<AggregatedData>();
		switch(on_interval){
			case "day":
				Date from_date = Base._parseDate(from);
				Date to_date = Base._parseDate(to);
				long milliseconds = to_date.getTime() - from_date.getTime();
				int days = (int) milliseconds / 1000 / 60 / 60 / 24 + 1;
				System.out.println("DEBUG: days="+days);
				for(int i=0; i<days; i++) {
					Date date = new Date();
					
					date.setTime( from_date.getTime() + i * 1000 * 60 * 60 * 24 );
					String a = Base._formatDate(date);
					
					date.setTime( from_date.getTime() + i * 1000 * 60 * 60 * 24 );
					String b = Base._formatDate(date);
					/*
					DataCollection data_collection = ClientFactory.data.getAll(
							"user_id="+user_id+
							"&record_type="+record_type+	"&data_name="+field_name+
							"&fromdate="+a+					"&todate="+b						);
					*/
					DataCollection data_collection = ClientFactory.data.getAll(
							DataFilter.user_id.set(user_id),
							DataFilter.record_type.set(record_type),
							DataFilter.data_name.set(field_name),
							DataFilter.fromdate.set(a),
							DataFilter.todate.set(b)					);
					
					
					float value = BaseUtils.elaborateDataCollection(function, data_collection);
										
					AggregatedData aggregated_data = new AggregatedData();
					aggregated_data.setFrom_date( a );
					aggregated_data.setTo_date( b );
					aggregated_data.setValue( Float.toString(value) );
					aggregated_datas.add( aggregated_data );
					
				}
			case "week":
			case "month":
			case "threemonth":
			case "year":
		}
		statistic.setDatas(aggregated_datas);
		//elaborate aggregated data
		
		statistic.setAverage(String.valueOf(AggregatedData.average(aggregated_datas)));
		statistic.setCumulative(String.valueOf(AggregatedData.sum(aggregated_datas)));
		//statistic.setMax(AggregatedData.max(aggregated_datas));
		//statistic.setMin(AggregatedData.min(aggregated_datas));
		
		return statistic;
	}
	
    
    
	
}
