package lsc.businesslogic.client;
 
import com.fasterxml.jackson.databind.ObjectMapper;

import lsc.rest.model.Goal;
import lsc.rest.model.Statistic;
 
public class TestApp{
	public static void main(String[] args) throws Exception {
				
		/*
		RecordComplex r = new RecordComplex();
		r.setId(104);
		r.setType("weight");
		r.setDate("2015-07-22");
		
		BusinessLogicClient.check_record( r );
		*/
		
		
		/*
		System.out.println("DEBUG NotificationLogic.getAllUnderUser id:" + 1);
		BusinessLogicClient.check_today(1);
		System.out.println("DEBUG NotificationLogic.getAllUnderUser 2");
		*/
		
		
		
		
		
		Statistic s = BusinessLogicClient.computeStatisticFor(1, "weight", "Kg", "2015-07-20", "2015-07-22", Goal.Interval.day, Goal.Function.last);
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println( mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s) );
		
		
		
		
		
		
		
        
	}
	
}