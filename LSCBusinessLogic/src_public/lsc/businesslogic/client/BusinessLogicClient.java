package lsc.businesslogic.client;
 
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import lsc.businesslogic.ws.LSCLogic;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Interval;
import lsc.rest.model.Statistic;
 
public class BusinessLogicClient{
	
	
	
    public static Goal postAndStartGoal(Goal goal) {
		System.out.println("BusinessLogicClient.postAndStartGoal(goal)");
		goal._print();
		
		LSCLogic lscLogic = BusinessLogicClient.init();
		return lscLogic.postAndStartGoal( goal );
    }
	
	
	
    public static void checkRecordsUnder(int userId, String record_type, String date) {
		System.out.println("BusinessLogicClient.checkRecordsUnder("+userId+","+record_type+","+date+")");
		//record._print();
		
		LSCLogic lscLogic = BusinessLogicClient.init();
		lscLogic.checkRecordsUnder( userId, record_type, date );
    }
    
    
    
    /*
    public static NotificationCollection checkDeadline(Deadline deadline) {
		System.out.println("BusinessLogicClient.check_deadline(Deadline)");
		deadline._print();
		
		LSCLogic lscLogic = BusinessLogicClient.init();
		lscLogic.checkDeadline( deadline );
    	return null;
    }
    */
    
    
    
    public static void checkExpiredDedline(int user_id) {
		System.out.println("BusinessLogicClient.check_deadline(user_id:"+user_id+")");
		
		LSCLogic lscLogic = BusinessLogicClient.init();
		lscLogic.checkExpiredDedline(user_id);
    }
    
    
    
    public static Statistic computeStatisticFor(int user_id,
    											String record_type,
	    										String field_name,
	    										String from,
	    										String to,
	    										Interval on_interval,
	    										Goal.Function function	) {
		System.out.println("BusinessLogicClient.compute_statistic(...)");
		System.out.println(" user_id:		"+user_id);
		System.out.println(" record_type:	"+record_type);
		System.out.println(" field_name:	"+field_name);
		System.out.println(" from:			"+from);
		System.out.println(" to:			"+to);
		System.out.println(" on_interval:	"+on_interval);
		System.out.println(" function:		"+function);
		
		LSCLogic lscLogic = BusinessLogicClient.init();
		return lscLogic.computeStatisticFor(user_id,
											record_type,
											field_name,
											from,
											to,
											on_interval,
											function	);
    }
	
    
    
	public static LSCLogic init() {
		LSCLogic lscLogic = null;
		URL url;
		try {
			url = new URL("http://localhost:3333/lsc?wsdl");
	        //1st argument service URI, refer to wsdl document above
			//2nd argument is service name, refer to wsdl document above
	        QName qname = new QName("http://ws.businesslogic.lsc/", "LSCLogic");
	        Service service = Service.create(url, qname);
	        //End-point
	        lscLogic = service.getPort(LSCLogic.class);
			service.setHandlerResolver(new JaxWsHandlerResolver());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lscLogic;
	}
	
	
	
}

