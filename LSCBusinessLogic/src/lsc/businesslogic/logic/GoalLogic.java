package lsc.businesslogic.logic;

import java.util.Date;

import lsc.rest.model.Base;
import lsc.rest.model.Deadline;
import lsc.rest.model.Deadline.Status;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Interval;
import lsc.storage.rest.client.StorageClient;


public class GoalLogic {
	
	
	
	public static Deadline autoStartGoalNow(Goal goal) {
		// AUTO START NEW GOAL
		if( goal.getRepeat()!=Interval.fixed ) {
			Date now = new Date();
			Date end = new Date();
			end.setTime( now.getTime() + goal.getRepeat().getMs() );
			Deadline deadline = new Deadline();
			deadline.setStartDate(Base._formatDate(now));
			deadline.setEndDate(Base._formatDate(end));
			deadline.setStatus(Status.active);
			deadline.setGoalId(goal.getId());
			DeadlineLogic.updateValueAndStatusNotifyThenPost(deadline, goal);
			StorageClient.deadline.post(deadline);
			NotificationLogic.postNewNotification(
					goal.getUserId(),
					deadline.getId(),
					"new deadline",
					"News, you have now a new deadline for accomplish your goal: "+"DEADLINEtoTEXT"	);
			return deadline;
		}
		return null;
	}
	
	

	// CLOSE PASSED DEADLINE AND START A NEW ONE
	public static void manageExpiredDeadline(Goal goal, DeadlineCollection deadlines) {
		Deadline last = deadlines.get( deadlines.size()-1 );
		Date now = new Date();
		Date end = Base._parseDate( last.getEndDate() );
		if( last.getStatus()==Status.active && now.after(end) ){
			DeadlineLogic.updateValueAndStatusNotifyThenPost(last, goal);
			if( goal.getRepeat() != Interval.fixed ) {
				Date new_end = new Date();
				new_end.setTime( now.getTime() + goal.getRepeat().getMs() );
				Deadline deadline = new Deadline();
				deadline.setStartDate( Base._formatDate(now) );
				deadline.setEndDate( Base._formatDate(new_end) );
				deadline.setStatus(Deadline.Status.active);
				deadline.setGoalId( goal.getId() );
				StorageClient.deadline.post(deadline);
			}
		}
		// Notification
		// TODO
		//check_today( goal.getId() );
		return;
	}
	
	
	
	
}
