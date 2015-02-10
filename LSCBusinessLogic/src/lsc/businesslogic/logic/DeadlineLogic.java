package lsc.businesslogic.logic;

import lsc.rest.model.Deadline;
import lsc.rest.model.Goal;
import lsc.rest.model.Statistic;
import lsc.rest.model.Deadline.Status;
import lsc.rest.model.Goal.Interval;
import lsc.storage.rest.client.StorageClient;


public class DeadlineLogic {


	
	
	// accepts only deadline with status active
	// return true if status is changed to succeeded
	// if status remains unchanged it returns false
	public static boolean updateDeadlineActualValueAndStatus(Deadline deadline, Goal goal) {
		// check status
		if(deadline.getStatus()!=Status.active)
			return false;
		
		// actual_value
		computeDeadlineActualValue(deadline, goal);
		double actual_value = Double.parseDouble( deadline.getActualValue() );
		
		// target_value
		double target_value = Double.parseDouble( goal.getValue() );
		
		// test goal
		boolean result = false;
		switch(goal.getOperator()){
			case larger:	result = actual_value >= target_value;
			case smaller:	result = actual_value <= target_value;
			case equals:	result = actual_value == target_value;
		}
		
		// update deadline
		deadline.setActualValue( ""+actual_value );
		deadline.setStatus(( result ? Status.succeeded : Status.active ));
		StorageClient.deadline.put(deadline);
		// notification
		if(result)
			NotificationLogic.postNewNotification(	goal.getUserId(),
													deadline.getId(),
													"deadline",
													"Good, deadline suceeded: "+"DEADLINEtoTEXT" );
		return result;
	}
	
	
	
	
	
	private static void computeDeadlineActualValue(Deadline deadline, Goal goal) {
		Statistic stat = computeDeadlineStatistic(deadline, goal);
		Double actual_value = stat.getDatas().get(0).getValues()
				.get(goal.getFunction())
				.get(goal.getReference())
				.get(goal.getPerc());
		deadline.setActualValue( String.valueOf(actual_value) );
	}

	
	
	private static Statistic computeDeadlineStatistic(Deadline deadline, Goal goal) {
		return StatisticLogic.computeCompleteStatistic(	goal.getUserId(),
														goal.getRecordType(),
														goal.getDataName(),
														deadline.getStartDate(),
														deadline.getEndDate(),
														Interval.fixed	);
	}
	
	
}
