package lsc.businesslogic.client;
 
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import lsc.businesslogic.ws.LSCLogic;
import lsc.rest.model.Deadline;
import lsc.rest.model.Goal;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.Statistic;
import lsc.rest.model.Goal.Interval;
import lsc.rest.model.User;
 
public class BusinessLogicClient{
	

	
    public static NotificationCollection check_record(RecordComplex record) {
    	return null;
    }
    
    public static NotificationCollection check_deadline(Deadline deadline) {
    	return null;
    }
    
    public static NotificationCollection check_today(User user) {
    	return null;
    }
    
    public static Statistic compute_statistic(	int user_id,
    											String record_type,
	    										String field_name,
	    										String from,
	    										String to,
	    										Interval on_interval,
	    										Goal.Function function	) {
    	return null;
    }
	
	public static LSCLogic init() throws MalformedURLException {		
		URL url = new URL("http://localhost:3333/lsc?wsdl");
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.businesslogic.lsc/", "LSCLogic");
        Service service = Service.create(url, qname);
        //End-point
        LSCLogic lscLogic = service.getPort(LSCLogic.class);
		service.setHandlerResolver(new JaxWsHandlerResolver());
		/*
		PeopleService service = new PeopleService();
        People people = service.getPort(People.class);
		service.setHandlerResolver(new JaxWsHandlerResolver());
		*/
		return lscLogic;
	}
	
	
	
}