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
		System.out.println("StatisticLogic.computeCompleteStatistic(...)");
		System.out.println(" user_id:		"+user_id);
		System.out.println(" record_type:	"+record_type);
		System.out.println(" data_name:		"+data_name);
		System.out.println(" from:			"+from);
		System.out.println(" to:			"+to);
		System.out.println(" on_interval:	"+on_interval);
		
		// compute times intervals
		Date from_date = Base._parseDate(from);
		Date to_date = Base._parseDate(to);
		long milliseconds = to_date.getTime() - from_date.getTime() + Goal.Interval.day.getMs();
		long ms_per_interval = on_interval.getMs();
		long intervals = 0;
		if(ms_per_interval==0) {
			ms_per_interval = milliseconds;
			intervals = 1;
		}
		else {
			intervals = (int) milliseconds / ms_per_interval;
		}
		//System.out.println("DEBUG intervals:"+intervals);
		//System.out.println("DEBUG ms_per_interval:"+ms_per_interval);
		
		// Compose statistic initial params
		Statistic statistic = new Statistic();
		statistic.setUserId( user_id );
		statistic.setRecordType( record_type );
		statistic.setFieldName( data_name );
		statistic.setFromDate( from );
		statistic.setToDate( to );
		statistic.setOnInterval( on_interval );
		
		
		// compute statistic datas
		List<StatisticData> statisticDataList = new ArrayList<StatisticData>();
		Date a = new Date();
		Date b = new Date();
		StatisticData old_statistic_data = null;
		
		for(int i=0; i<intervals; i++) {
			//a
			a.setTime( from_date.getTime() + i * ms_per_interval );
			//String a = Base._formatDate(date);
			//b
			b.setTime( from_date.getTime() + i * ms_per_interval + ms_per_interval - Goal.Interval.day.getMs() );
			//String b = Base._formatDate(date);
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
		
		statistic.setDatas( statisticDataList );
		
		
		// compute global statistic
		statistic.setAverage( statistic._average() );
		statistic.setCumulative( statistic._sum() );
		statistic.setMax( statistic._max() );
		statistic.setMin( statistic._min() );
		
		return statistic;
	}
    
	
	
	
	
	
	public static StatisticData computeStatisticData(	int user_id,
														String record_type,
														String data_name,
														Date from,
														Date to		) {
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
												Date from,
												Date to,
												StatisticData old	) {
		System.out.println("StatisticLogic.computeStatisticData");
		System.out.println(" user_id:			"+user_id);
		System.out.println(" record_type:		"+record_type);
		System.out.println(" data_name:			"+data_name);
		System.out.println(" from:				"+from);
		System.out.println(" to:				"+to);
		System.out.println(" old_statistic_data:"+"old");
		
		// generate old for reference if unavailable
		if( old==null ){
			Date old_a = new Date();
			old_a.setTime( from.getTime() - (to.getTime() - from.getTime() + Goal.Interval.day.getMs() ));
			Date old_b = new Date();
			old_b.setTime( from.getTime() - Goal.Interval.day.getMs() );
			// load data
			Filter.DataFilter old_data_filter = Filter.data().user_id(user_id)
					.record_type(record_type).data_name(data_name)
					.fromdate(Base._formatDate(old_a)).todate(Base._formatDate(old_b));
			DataCollection old_data_collection = StorageClient.data.getAll(old_data_filter);
			// compute statistic
			old = new StatisticData();
			old.setFromDate( Base._formatDate(old_a) );
			old.setToDate( Base._formatDate(old_b) );
			old._initValues(null);
			completeTargetAbs(	old, old_data_collection );
		}
		
		// load datas
		Filter.DataFilter data_filter = Filter.data().user_id(user_id)
			.record_type(record_type).data_name(data_name)
			.fromdate( Base._formatDate(from) ).todate( Base._formatDate(to) );
		DataCollection data_collection = StorageClient.data.getAll(data_filter);
		
		// compute statistic datas
		StatisticData statisticData = new StatisticData();
		statisticData.setFromDate( Base._formatDate(from) );
		statisticData.setToDate( Base._formatDate(to) );
		//statisticData._initValues(null);
		completeTargetAbs( statisticData, data_collection );
		completeTargetPerc(	statisticData, old );
		completeIncrementAbs( statisticData, old );
		completeIncrementPerc( statisticData, old );
		//
		return statisticData;
	}
    

	
	
	
	
	
	
	private static void completeTargetAbs(	StatisticData statisticData, DataCollection data_collection ) {
		if(data_collection==null || statisticData==null) 
			return;
		//elaborate TARGET ABS values
		for(Goal.Function f : Goal.Function.values()){
			float value = elaborateDataCollection(f, data_collection);
			//double value = elaborateDataCollection(Function.average, data_collection);
			statisticData._setValue(f, Reference.target, Perc.abs, value);
		}
	}
	
	private static void completeTargetPerc( StatisticData statisticData, StatisticData old ) {
		if(old==null || statisticData==null) 
			return;
		// elaborate TARGET PERC values
		for(Goal.Function f : Goal.Function.values()){
			float value = statisticData._getValue(f, Reference.target, Perc.abs)
								   / old._getValue(f, Reference.target, Perc.abs)
								   * 100;
			statisticData._setValue(f, Reference.target, Perc.perc, value);
		}
	}
	
	private static void completeIncrementAbs( StatisticData statisticData, StatisticData old ) {
		if(old==null || statisticData==null) 
			return;
		// elaborate INCREMENT ABS values
		for(Goal.Function f : Goal.Function.values()){
			float value = statisticData._getValue(f, Reference.target, Perc.abs)
								  - old._getValue(f, Reference.target, Perc.abs);
			statisticData._setValue(f, Reference.increment, Perc.abs, value);
		}
	}
	
	private static void completeIncrementPerc( StatisticData statisticData, StatisticData old ) {
		if(old==null || statisticData==null) 
			return;
		// elaborate INCREMENT PERC values
		for(Goal.Function f : Goal.Function.values()){
			float value = statisticData._getValue(f, Reference.increment, Perc.abs)
								  / old._getValue(f, Reference.target, Perc.abs)
								  * 100 ;
			statisticData._setValue(f, Reference.increment, Perc.perc, value);
		}
	}
	
	private static float elaborateDataCollection(Function function, DataCollection data_collection) {
		if(function==null || data_collection==null || data_collection.size()==0) 
			return Float.valueOf(0);
		switch(function){
		case sum:
			return data_collection._sum();
		case average:
			return data_collection._average();
		case max:
			return Float.parseFloat( data_collection._max().getValue() );
		case min:
			return Float.parseFloat( data_collection._min().getValue() );
		case last:
			return Float.parseFloat( data_collection.get(data_collection.size()-1).getValue() );
		case first:
			return Float.parseFloat( data_collection.get(0).getValue() );
		}
		return 0;
	}
	
}
