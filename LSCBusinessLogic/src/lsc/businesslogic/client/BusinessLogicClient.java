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
		LSCLogic lscLogic = init();
		RecordComplex r = new RecordComplex();
		r.setId( record.getId() );
		r.setType( record.getType() );
		r.setDate( record.getDate() );
		r.setUserId( record.getUserId() );
		lscLogic.updateStatusByRecord( r );
    	return null;
    }
    
    public static NotificationCollection check_deadline(Deadline deadline) {
		LSCLogic lscLogic = init();
		lscLogic.updateStatusByDeadline( deadline );
    	return null;
    }
    
    public static NotificationCollection check_today(int user_id) {
		LSCLogic lscLogic = init();
		NotificationCollection nn = lscLogic.checkToday(user_id);
    	return nn;
    }
    
    public static Statistic compute_statistic(	int user_id,
    											String record_type,
	    										String field_name,
	    										String from,
	    										String to,
	    										Interval on_interval,
	    										Goal.Function function	) {
		LSCLogic lscLogic = init();
		return lscLogic.computeStatistic(	user_id,
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
		
		/*
		LSCLogic_Service lscLogicService = new LSCLogic_Service();
		LSCLogic lscLogic = lscLogicService.getLSCLogicImplPort();
        //System.out.println(hello.updateStatusByDeadline(deadline));
		*/
		
		return lscLogic;
	}
	
	
	
}