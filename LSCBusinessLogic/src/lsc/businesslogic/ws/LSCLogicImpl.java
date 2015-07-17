package lsc.businesslogic.ws;

import javax.jws.WebService;

import lsc.businesslogic.logic.DeadlineLogic;
import lsc.businesslogic.logic.GoalLogic;
import lsc.businesslogic.logic.NotificationLogic;
import lsc.businesslogic.logic.StatisticLogic;
import lsc.rest.filter.Filter;
import lsc.rest.model.Deadline;
import lsc.rest.model.Deadline.Status;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Function;
import lsc.rest.model.Goal.Interval;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.Statistic;
import lsc.rest.model.User;
import lsc.storage.rest.client.StorageClient;

//Service Implementation

@WebService(endpointInterface = "lsc.businesslogic.ws.LSCLogic", serviceName="LSCLogic")
public class LSCLogicImpl implements LSCLogic {
	
	
	
	
	@Override
	public void autoStartGoalNow(Goal goal) {
		System.out.println("SOAP autoStartGoalNow("+goal.getId()+")");
		GoalLogic.autoStartGoalNow(goal);
	}
	
	
	
	
	@Override
	public NotificationCollection updateStatusByRecord(RecordComplex record) {
		System.out.println("SOAP updateStatusByRecord("+record.getId()+")");
		DeadlineCollection deadlineCollection
			= StorageClient.deadline.getAll(Filter.deadline
											.record_type( record.getType() )
											.include_date( record.getDate() )
											.user_id(record.getUserId())
											.status(Status.active)		);
		for(Deadline d : deadlineCollection)
			DeadlineLogic.updateValueAndStatusNotifyThenPost(d, StorageClient.goal.getById(d.getGoalId()));
		return null;
	}
	
	
	
	
	
	@Override
	public NotificationCollection updateStatusByDeadline(Deadline deadline) {
		System.out.println("SOAP updateStatusByDeadline("+deadline.getId()+")");
		DeadlineLogic.updateValueAndStatusNotifyThenPost(deadline, StorageClient.goal.getById(deadline.getGoalId()));
		return null;
	}
	
	
	
	
	
	@Override
	public NotificationCollection checkToday(User user) {
		System.out.println("SOAP checkToday("+user.getId()+")");
		DeadlineCollection deadlineCollection
			= StorageClient.deadline.getAll(Filter.deadline
											.user_id(user.getId())
											.status(Status.active)		);
		for(Deadline d : deadlineCollection) {
			Goal goal = StorageClient.goal.getById(d.getGoalId());
			DeadlineLogic.updateValueAndStatusNotifyThenPost(d, goal);
			GoalLogic.manageExpiredDeadline(
				goal,
				StorageClient.deadline.getAll( Filter.deadline.goal_id(goal.getId()) )
			);
		}
		return null;
	}
	
	
	
	
	
	@Override
	public Statistic computeStatistic(int user_id, String record_type, String data_name,
			String from, String to, Interval on_interval, Function function) {
		return StatisticLogic.computeCompleteStatistic
				( user_id, record_type, data_name, from, to, on_interval );
	}
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
