package lsc.businesslogic.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lsc.rest.filter.Filter;
import lsc.rest.model.Base;
import lsc.rest.model.DataCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Function;
import lsc.rest.model.Goal.Interval;
import lsc.rest.model.Goal.Perc;
import lsc.rest.model.Goal.Reference;
import lsc.rest.model.Statistic;
import lsc.rest.model.StatisticData;
import lsc.storage.rest.client.StorageClient;


public class StatisticLogic {
	
	
	public static Statistic computeCompleteStatistic(	int user_id,
												String record_type,
												String data_name,
												String from,
												String to,
												Interval on_interval	) {
		
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
		StatisticData old_statistic_data = null;
		
		for(int i=0; i<intervals; i++) {
			//a
			date.setTime( from_date.getTime() + i * ms_per_interval );
			String a = Base._formatDate(date);
			//b
			date.setTime( from_date.getTime() + i * ms_per_interval );
			String b = Base._formatDate(date);
			//get
			StatisticData statisticData = computeStatisticData(	user_id,
																record_type,
																data_name,
																a,
																b,
																old_statistic_data	);
			statisticDataList.add( statisticData );
			old_statistic_data = statisticData;
		}
		
		// Compose statistic
		Statistic statistic = new Statistic();
		statistic.setUserId( user_id );
		statistic.setRecordType( record_type );
		statistic.setFieldName( data_name );
		statistic.setFromDate( from );
		statistic.setToDate( to );
		statistic.setOnInterval( on_interval );
		statistic.setDatas( statisticDataList );
		statistic.setAverage( statistic._average() );
		statistic.setCumulative( statistic._sum() );
		statistic.setMax( statistic._max() );
		statistic.setMin( statistic._min() );
		
		return statistic;
	}
    
	
	
	
	
	
	public static StatisticData computeStatisticData(	int user_id,
												String record_type,
												String data_name,
												String from,
												String to		) {
		return computeStatisticData(	user_id,
										record_type,
										data_name,
										from,
										to,
										null);
	}
	private static StatisticData computeStatisticData(	int user_id,
												String record_type,
												String data_name,
												String from,
												String to,
												StatisticData old	) {
		// generate old for reference if unavailable
		if( old==null ){
			// load data
			Filter.DataFilter old_data_filter = Filter.data.user_id(user_id)
					.record_type(record_type).data_name(data_name)
					.fromdate(compute_passed_date(from, to)).todate(from);
			DataCollection old_data_collection = StorageClient.data.getAll(old_data_filter);
			// compute statistic
			old = new StatisticData();
			old.setFromDate( from );
			old.setToDate( to );
			old._initValues(null);
			completeTargetAbs(	old, old_data_collection );
		}
		
		// load datas
		Filter.DataFilter data_filter = Filter.data.user_id(user_id)
			.record_type(record_type).data_name(data_name)
			.fromdate(from).todate(to);
		DataCollection data_collection = StorageClient.data.getAll(data_filter);
		
		// compute statistic datas
		StatisticData statisticData = new StatisticData();
		statisticData.setFromDate( from );
		statisticData.setToDate( to );
		statisticData._initValues(null);
		completeTargetAbs( statisticData, data_collection );
		completeTargetPerc(	statisticData, old );
		completeIncrementAbs( statisticData, old );
		completeIncrementPerc( statisticData, old );
		//
		return statisticData;
	}
	
	
	
	private static String compute_passed_date(String start_date, String end_date) {
		Date start = Base._parseDate( start_date );
		Date end = Base._parseDate( end_date );
		long lenght_ms = end.getTime() - start.getTime();
		Date passed = new Date();
		passed.setTime( start.getTime() - lenght_ms );
		return Base._formatDate( passed );
	}
    

	
	
	
	
	
	
	private static void completeTargetAbs(	StatisticData statisticData, DataCollection data_collection ) {
		//elaborate TARGET ABS values
		for(Goal.Function f : Goal.Function.values()){
			double value = elaborateDataCollection(Function.average, data_collection);
			statisticData.getValues().get(f).get(Reference.target).put(Perc.abs, value);
		}
	}
	
	private static void completeTargetPerc( StatisticData statisticData, StatisticData old ) {
		// elaborate TARGET PERC values
		for(Goal.Function f : Goal.Function.values()){
			double value = statisticData.getValues().get(f).get(Reference.target).get(Perc.abs)
							/ old.getValues().get(f).get(Reference.target).get(Perc.abs)
							* 100;
			statisticData.getValues().get(f).get(Reference.target).put(Perc.perc, value);
		}
	}
	
	private static void completeIncrementAbs( StatisticData statisticData, StatisticData old ) {
		// elaborate INCREMENT ABS values
		for(Goal.Function f : Goal.Function.values()){
			double value = statisticData.getValues().get(f).get(Reference.target).get(Perc.abs)
							- old.getValues().get(f).get(Reference.target).get(Perc.abs);
			statisticData.getValues().get(f).get(Reference.increment).put(Perc.abs, value);
		}
	}
	
	private static void completeIncrementPerc( StatisticData statisticData, StatisticData old ) {
		// elaborate INCREMENT PERC values
		for(Goal.Function f : Goal.Function.values()){
			double value = statisticData.getValues().get(f).get(Reference.increment).get(Perc.abs)
							/ old.getValues().get(f).get(Reference.target).get(Perc.abs)
							* 100 ;
			statisticData.getValues().get(f).get(Reference.increment).put(Perc.perc, value);
		}
	}
	
	private static Float elaborateDataCollection(Function function, DataCollection data_collection) {
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
	
}
