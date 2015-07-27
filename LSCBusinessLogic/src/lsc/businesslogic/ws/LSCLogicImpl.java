package lsc.businesslogic.ws;

import javax.jws.WebService;

import lsc.businesslogic.logic.DeadlineLogic;
import lsc.businesslogic.logic.GoalLogic;
import lsc.businesslogic.logic.StatisticLogic;
import lsc.rest.filter.Filter;
import lsc.rest.model.Deadline;
import lsc.rest.model.Deadline.Status;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Function;
import lsc.rest.model.Goal.Interval;
import lsc.rest.model.GoalCollection;
import lsc.rest.model.Statistic;
import lsc.storage.rest.client.StorageClient;

//Service Implementation

@WebService(endpointInterface = "lsc.businesslogic.ws.LSCLogic", serviceName="LSCLogic")
public class LSCLogicImpl implements LSCLogic {
	
	
	
	
	@Override
	public Goal postAndStartGoal(Goal goal) {
		System.out.println("SOAP postAndStartGoal(goal)");
		goal._print();
		
		GoalLogic.autoStartGoalNow(goal);
		return goal;
	}
	
	
	
	
	@Override
	public void checkRecordsUnder(int userId, String record_type, String date) {
		System.out.println("SOAP checkRecordsUnder("+userId+","+record_type+","+date+")");
		
		DeadlineCollection deadlineCollection
			= StorageClient.deadline.getAll(Filter.deadline()
											.record_type( record_type )
											.include_date( date )
											.user_id( userId )			);
		for(Deadline d : deadlineCollection) {
			d = DeadlineLogic.updateValueAndStatusPutTheNotify(d, StorageClient.goal.getById(d.getGoalId()));
		}
	}
	
	
	
	
	/*
	@Override
	public NotificationCollection checkGoal(int goal_id) {
		System.out.println("SOAP updateStatusByDeadline("+deadline.getId()+")");
		deadline._print();
		
		DeadlineLogic.updateValueAndStatusNotifyThenPost(deadline, StorageClient.goal.getById(deadline.getGoalId()));
		return null;
	}
	*/
	
	
	
	
	@Override
	public void checkExpiredDedline(int user_id) {
		System.out.println("SOAP checkExpiredDedline("+user_id+")");
		
		GoalCollection goals = StorageClient.goal.getAll( Filter.goal().user_id(user_id) );
		for(Goal g : goals) {
			GoalLogic.manageExpiredDeadline(g);
		}
		
		/*
		DeadlineCollection activeDeadlineCollection = new DeadlineCollection();
		// find succeeding goal
		activeDeadlineCollection.addAll(
			StorageClient.deadline.getAll( Filter.deadline().user_id(user_id).status(Status.succeeding) )
		);
		// find failing goal
		activeDeadlineCollection.addAll(
			StorageClient.deadline.getAll( Filter.deadline().user_id(user_id).status(Status.failing) )
		);
		for(Deadline d : activeDeadlineCollection) {
			if (activeGoalCollection._get(d.getGoalId()) == null) {
				Goal goal = StorageClient.goal.getById(d.getGoalId());
				if (goal == null) 
					continue;
				activeGoalCollection.add(goal);
				GoalLogic.manageExpiredDeadline(goal);
			}
		}
		// find failing goal
		DeadlineCollection failingDeadlineCollection = StorageClient.deadline.getAll(
				Filter.deadline().user_id(user_id).status(Status.failing)
		);
		for(Deadline d : failingDeadlineCollection) {
			if (activeGoalCollection._get(d.getGoalId()) == null) {
				Goal goal = StorageClient.goal.getById(d.getGoalId());
				if (goal == null) 
					continue;
				activeGoalCollection.add(goal);
			}
		}
		
		// manage expired goal
		for(Goal goal: activeGoalCollection) {
			GoalLogic.manageExpiredDeadline(goal);
		}
		*/
		
		return;
	}
	
	
	
	
	
	@Override
	public Statistic computeStatisticFor(	int user_id,
										String record_type,
										String data_name,
										String from,
										String to,
										Interval on_interval,
										Function function	) {
		System.out.println("SOAP computeStatistic("+user_id+","+record_type+","+data_name+","+from+","+to+","+on_interval+","+function+")");
		
		return StatisticLogic.computeCompleteStatistic(	user_id,
														record_type,
														data_name,
														from,
														to,
														on_interval	);
	}
	
	
	
	
	
	
	
	
	
	
	
}
