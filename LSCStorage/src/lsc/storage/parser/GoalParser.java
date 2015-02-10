package lsc.storage.parser;

import lsc.rest.model.Goal;
import lsc.rest.model.GoalCollection;


public class GoalParser extends BaseParser<	Goal,
											GoalCollection	> {
	
	


	@Override
	public Goal toStorage(Goal entity) {
		return entity;
	}

	@Override
	public Goal toDatabase(Goal entity) {
		return entity;
	}
	
	
}
