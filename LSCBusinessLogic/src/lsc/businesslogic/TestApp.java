package lsc.businesslogic;
 
import com.fasterxml.jackson.databind.ObjectMapper;

import lsc.businesslogic.logic.StatisticLogic;
import lsc.rest.model.Goal;
import lsc.rest.model.Statistic;
 
public class TestApp{
	public static void main(String[] args) throws Exception {
		
		
		
		
		
		Statistic s = StatisticLogic.computeCompleteStatistic(1, "weight", "Kg", "2015-07-21", "2015-07-22", Goal.Interval.fixed);
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println( mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s) );
		
		
		
		
		
		
		
        
	}
	
}