package lsc.businesslogic.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import lsc.rest.model.Deadline;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Interval;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.Statistic;
import lsc.rest.model.User;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface LSCLogic {
	
	
	
    @WebMethod(operationName="check_record")
    @WebResult(name="NotificationCollection")
    public NotificationCollection check_record(	@WebParam(name="record") 	RecordComplex record	);
    
    
    
    @WebMethod(operationName="check_deadline")
    @WebResult(name="NotificationCollection")
    public NotificationCollection check_deadline(@WebParam(name="deadline") Deadline deadline		);
    
    
    
    @WebMethod(operationName="check_today")
    @WebResult(name="NotificationCollection")
    public NotificationCollection check_today(	@WebParam(name="user")		User user				);
    
    
    
    @WebMethod(operationName="compute_statistic")
    @WebResult(name="Statistic")
    public Statistic compute_statistic(			@WebParam(name="user_id")		int user_id,
	    										@WebParam(name="record_type")	String record_type,
	    										@WebParam(name="field_name")	String field_name,
	    										@WebParam(name="from")			String from,
	    										@WebParam(name="to") 			String to,
	    										@WebParam(name="on_interval")	Interval on_interval,
	    										@WebParam(name="function")		Goal.Function function	);
    
    
}


