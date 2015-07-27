package lsc.businesslogic.logic;

import java.util.Date;

import lsc.rest.filter.Filter;
import lsc.rest.model.Base;
import lsc.rest.model.Deadline;
import lsc.rest.model.Deadline.Status;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Interval;
import lsc.storage.rest.client.StorageClient;


public class GoalLogic {
	
	

	// AUTO START NEW GOAL
	public static Deadline autoStartGoalNow(Goal goal) {
		
		// Goal
		Date now = new Date();
		Date end = new Date();
		if( goal.getRepeat()!=Interval.fixed ) {
			end.setTime( now.getTime() + goal.getRepeat().getMs() - Interval.day.getMs() );
		}
		else {
			float days = 1; //default finish goal next day
			try {
				days = Float.parseFloat(goal.getDays());
			} catch (NumberFormatException e) {}
			if (days<1)
				days = 1;
			long ms = new Double( Math.floor(days*24*60*60*1000) ).longValue();
			end.setTime( now.getTime() + ms - Interval.day.getMs() );
		}
		goal = StorageClient.goal.post(goal);
		
		// Deadline
		Deadline deadline = new Deadline();
		deadline.setStartDate(Base._formatDate(now));
		deadline.setEndDate(Base._formatDate(end));
		deadline.setGoalId(goal.getId());
		deadline.setActualValue("0");
		deadline.setStatus(Status.failing);
		deadline = DeadlineLogic.updateDeadlineActualValue(deadline, goal);
		deadline = DeadlineLogic.updateDeadlineStatus(deadline, goal);
		deadline = StorageClient.deadline.post(deadline);
		
		// Notification
		NotificationLogic.postNewNotification(
				goal.getUserId(),
				deadline.getId(),
				"new goal",
				"News, your new goal has started: "+"DEADLINEtoTEXT"	);
		
		return deadline;
	}
	
	

	// CLOSE PASSED DEADLINE AND START A NEW ONE
	public static void manageExpiredDeadline(Goal goal) {
		while(true) {
			//get all
			DeadlineCollection deadlines = StorageClient.deadline.getAll(Filter.deadline().goal_id(goal.getId()));
			if(deadlines.size()==0)
				return;
			//find the last one
			Deadline last = deadlines.get( deadlines.size()-1 );
			//check if it is expired
			Date end = Base._parseDate( last.getEndDate() );
			if( new Date().after(end) ){
				//update it
				last = DeadlineLogic.updateValueAndStatusPutTheNotify(last, goal);
				//if it is expired and it is recursive: let's restart it again
				if( goal.getRepeat() != Interval.fixed ) {
					Date new_start = new Date();
					new_start.setTime( end.getTime() + Interval.day.getMs() );
					Date new_end = new Date();
					new_end.setTime( end.getTime() + goal.getRepeat().getMs() );
					Deadline deadline = new Deadline();
					deadline.setStartDate( Base._formatDate(new_start) );
					deadline.setEndDate( Base._formatDate(new_end) );
					deadline.setGoalId( goal.getId() );
					deadline.setActualValue("0");
					deadline.setStatus(Deadline.Status.failing);
					deadline = DeadlineLogic.updateDeadlineActualValue(deadline, goal);
					deadline = DeadlineLogic.updateDeadlineStatus(deadline, goal);
					deadline = StorageClient.deadline.post(deadline);
					// Notification
					NotificationLogic.postNewNotification(
						goal.getUserId(), deadline.getId(),
						"new deadline",
						"News, you have now a new deadline (by the "+deadline.getEndDate()+") for accomplish your goal: "+goal._description() );
				}
			}
			else
				return;
		}
	}
	
	
	
	
}
