package lsc.businesslogic.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;

import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.model.ToDoCollection;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment.ws.People", serviceName="PeopleService")
public class LSCLogicImpl implements LSCLogic {
	
	@Override
	public int register_record(Record record) {
		System.out.println("---> register_record");
		return 1;
	}

	@Override
	public GoalDeadline read_goal(int goal_id) {
		System.out.println("---> read_goal "+goal_id);
		return 0;
	}
	
	@Override
	public int register_goal(Goal goal) {
		System.out.println("---> register_goal "+goal.getRecord_type()+
											 " "+goal.getData_name()+
											 " "+goal.getReapeat()		);
		return 0;
	}

	@Override
	public int complete_todo(int todo_id) {
		System.out.println("---> complete_todo "+todo_id);
		ToDoConnect.getById(todo_id).setStatus("done");
		return 0;
	}

	@Override
	public int update_today_goal_and_notification() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Statistic compute_statistic(int user_id, String type, String from,
			String to, String oninterval, String function) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
	
}
