package lsc.businesslogic.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import lsc.businesslogic.transfer.RecordData;
import lsc.businesslogic.transfer.Statistic;
import lsc.localdatabase.rest.model.GoalRest;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface LSCLogic {
	
	
    @WebMethod(operationName="register_record")
    @WebResult(name="record")
    public int register_record(					@WebParam(name="record") RecordData record		);
    
    /*
    @WebMethod(operationName="read_goal")
    @WebResult(name="GoalDeadline")
    public GoalDeadline read_goal(				@WebParam(name="goal_id") int goal_id			);
    */
    
    @WebMethod(operationName="register_goal")
    @WebResult(name="void")
    public int register_goal(					@WebParam(name="goal") GoalRest goal,
    											@WebParam(name="end_date") String end_date		);
    
    
    @WebMethod(operationName="complete_todo")
    @WebResult(name="void")
    public int complete_todo(					@WebParam(name="todo_id") int todo_id			);
    
    
    @WebMethod(operationName="update_today_goal_and_notification")
    @WebResult(name="void")
    public int update_today_goal_and_notification(												);
    
    
    @WebMethod(operationName="compute_statistic")
    @WebResult(name="Statistic")
    public Statistic compute_statistic(			@WebParam(name="user_id")	int user_id,
	    										@WebParam(name="record_type")String record_type,
	    										@WebParam(name="field_name")String field_name,
	    										@WebParam(name="from")		String from,
	    										@WebParam(name="to") 		String to,
	    										@WebParam(name="on_interval")String on_interval,
	    										@WebParam(name="function")	String function		);
    
    
}


