package lsc.businesslogic.logic;

import java.util.Date;

import lsc.rest.model.Base;
import lsc.rest.model.Deadline;
import lsc.rest.model.Deadline.Status;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Interval;
import lsc.rest.model.Goal.Operator;
import lsc.rest.model.Statistic;
import lsc.storage.rest.client.StorageClient;


public class DeadlineLogic {
	
	
	
	
	
	// accepts only deadline with status active
	// return true if status is changed to succeeded
	// if status remains unchanged it returns false
	public static Deadline updateValueAndStatusPutTheNotify(Deadline deadline, Goal goal) {
		System.out.println("DeadlineLogic.updateValueAndStatusNotifyThenPost(deadline, goal)");
		deadline._print();
		goal._print();
		
		// save
		String old_value = deadline.getActualValue();
		Status old_status = deadline.getStatus();
		
		// value
		deadline = DeadlineLogic.updateDeadlineActualValue(deadline, goal);
		
		// status
		deadline = DeadlineLogic.updateDeadlineStatus(deadline, goal);

		// save
		if( old_value!=deadline.getActualValue() | old_status!=deadline.getStatus() )
			StorageClient.deadline.put(deadline);

		// notify
		if( old_status!=deadline.getStatus() )
			DeadlineLogic.notifyStatus(deadline, goal);
		
		return deadline;
	}
	
	
	
	public static Deadline updateDeadlineActualValue(Deadline deadline, Goal goal) {
		Statistic stat = computeDeadlineStatistic(deadline, goal);
		if( stat.getDatas() == null) {
			System.out.println("ERROR DeadlineLogic.updateDeadlineActualValue stat.getDatas() is NULL");
			return deadline;
		}
		if( goal.getFunction() == null) {
			System.out.println("ERROR DeadlineLogic.updateDeadlineActualValue goal.getFunction() is NULL");
			return deadline;
		}
		if( goal.getReference() == null) {
			System.out.println("ERROR DeadlineLogic.updateDeadlineActualValue goal.getReference() is NULL");
			return deadline;
		}
		if( goal.getPerc() == null) {
			System.out.println("ERROR DeadlineLogic.updateDeadlineActualValue goal.getPerc() is NULL");
			return deadline;
		}
		Float actual_value = stat.getDatas().get(0)._getValue(goal.getFunction(), goal.getReference(), goal.getPerc());
		deadline.setActualValue( String.valueOf(actual_value) );
		return deadline;
	}
	
	
	
	// accepts only deadline with status active
	// return true if status is changed to succeeded
	// if status remains unchanged it returns false
	public static Deadline updateDeadlineStatus(Deadline deadline, Goal goal) {
		
		// actual_value & target_value
		double actual_value = Double.parseDouble( deadline.getActualValue() );
		double target_value = Double.parseDouble( goal.getValue() );
		
		// test goal
		boolean result = false;
		if(goal.getOperator()==Operator.larger)
			result = actual_value >= target_value;
		else if(goal.getOperator()==Operator.smaller)
			result = actual_value <= target_value;
		else
			result = actual_value == target_value;
		/*
			switch(goal.getOperator()){
			case larger:	result = (actual_value >= target_value);
			case smaller:	result = (actual_value <= target_value);
			case equals:	result = (actual_value == target_value);
		}
		*/
		
		// update deadline
		if( new Date().after(Base._parseDate(deadline.getEndDate())) )
			deadline.setStatus( result ? Status.succeeded : Status.failed );
		else
			deadline.setStatus( result ? Status.succeeding : Status.failing );
			
		return deadline;
	}

	
	
	private static Statistic computeDeadlineStatistic(Deadline deadline, Goal goal) {
		return StatisticLogic.computeCompleteStatistic(	goal.getUserId(),
														goal.getRecordType(),
														goal.getDataName(),
														deadline.getStartDate(),
														deadline.getEndDate(),
														Interval.fixed	);
	}
	
	
	
	public static void notifyStatus(Deadline deadline, Goal goal) {
		if(deadline.getStatus()==Status.failed)
			NotificationLogic.postNewNotification(
				goal.getUserId(), deadline.getId(),
				"deadline failed",
				"Bad news: you are out of time about the deadline for accomplish your goal: "+goal._description()+"by "+deadline.getEndDate()	);
		else if(deadline.getStatus()==Status.succeeded)
			NotificationLogic.postNewNotification(
				goal.getUserId(), deadline.getId(),
				"deadline suceeded",
				"Good, deadline suceeded ("+deadline.getEndDate()+"): "+goal._description() );
		else if (deadline.getStatus()==Status.succeeding)
			NotificationLogic.postNewNotification(
				goal.getUserId(), deadline.getId(),
				"deadline getting better",
				"Goal could be near ("+deadline.getEndDate()+"): "+goal._description() );
		else if (deadline.getStatus()==Status.failing)
			NotificationLogic.postNewNotification(
				goal.getUserId(), deadline.getId(),
				"deadline getting worst",
				"Goal is going far ("+deadline.getEndDate()+"): "+goal._description() );
	}
	
	
}
