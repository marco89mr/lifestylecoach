package lsc.businesslogic.ws;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.model.User;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface LSCLogic {
	
	
    @WebMethod(operationName="register_record")
    @WebResult(name="record")
    public int register_record(					@WebParam(name="record") Record record			);
    
    
    @WebMethod(operationName="read_goal")
    @WebResult(name="GoalDeadline")
    public GoalDeadline read_goal(				@WebParam(name="goal_id") int goal_id			);
    
    
    @WebMethod(operationName="register_goal")
    @WebResult(name="void")
    public int register_goal(					@WebParam(name="goal") Goal goal				);
    
    
    @WebMethod(operationName="complete_todo")
    @WebResult(name="void")
    public int complete_todo(					@WebParam(name="todo_id") int todo_id			);
    
    
    @WebMethod(operationName="update_today_goal_and_notification")
    @WebResult(name="void")
    public int update_today_goal_and_notification(												);
    
    
    @WebMethod(operationName="compute_statistic")
    @WebResult(name="Statistic")
    public Statistic compute_statistic(			@WebParam(name="id")		int user_id,
	    										@WebParam(name="type")		String type,
	    										@WebParam(name="from")		String from,
	    										@WebParam(name="to") 		String to,
	    										@WebParam(name="oninterval")String oninterval,
	    										@WebParam(name="function")	String function		);
    
    
}


