package lsc.businesslogic.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Interval;
import lsc.rest.model.Statistic;

@WebService
//@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
@SOAPBinding(style = Style.RPC)
public interface LSCLogic {
	
	
	/*
	 * Post the goal in the storage and create the first deadline starting from today
	 */
    @WebMethod(operationName="postAndStartGoal")
    @WebResult(name="Goal")
    public Goal postAndStartGoal(						@WebParam(name="goal") 			Goal goal 		);
    
    
    /*
    @WebMethod(operationName="checkGoal")
    public void checkGoal(								@WebParam(name="goal_id")			int goal_id		);
	*/
	
	/*
	 * If a record is added deleted or modified with this parameter it is necessary to:
	 * recalculate the actual value influenced deadlines
	 * recalculate the status of actual value
	 * post notifications
	 */
    @WebMethod(operationName="checkRecordsUnder")
    public void checkRecordsUnder(						@WebParam(name="user_id")		int user_id,
														@WebParam(name="record_type")	String record_type,
														@WebParam(name="date")			String date				);
    
    
    /*
     * for the user specified, check for expired deadlines and:
     * compute the end status
     * create notification
     */
    @WebMethod(operationName="checkExpiredDedline")
    public void checkExpiredDedline(	@WebParam(name="user_id")		int user_id				);
    
    
    
    @WebMethod(operationName="computeStatisticFor")
    @WebResult(name="Statistic")
    public Statistic computeStatisticFor(				@WebParam(name="user_id")		int user_id,
			    										@WebParam(name="record_type")	String record_type,
			    										@WebParam(name="field_name")	String field_name,
			    										@WebParam(name="from")			String from,
			    										@WebParam(name="to") 			String to,
			    										@WebParam(name="on_interval")	Interval on_interval,
			    										@WebParam(name="function")		Goal.Function function	);
    
    
    
}


