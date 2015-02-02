package lsc.localdatabase.rest;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Goal;
import lsc.localdatabase.dao.model.GoalCollection;
import lsc.localdatabase.dao.model.User;
import lsc.localdatabase.rest.model.GoalCollectionRest;
import lsc.localdatabase.rest.model.GoalRest;
import lsc.localdatabase.rest.model.LinkRest;
import lsc.localdatabase.rest.path.PathFactory;


public class GoalParser extends BaseParser<	Goal,
											GoalCollection,
											GoalRest,
											GoalCollectionRest,
											GoalRest.Relation		> {
	

	
	public GoalRest toRest(Goal dao) {
		GoalRest rest = new GoalRest();
		rest.setData_name	(	dao.getData_name()						);
		rest.setDays		(	dao.getDays()							);
		rest.setFunction	(	dao.getFunction()						);
		rest.setOperator	(	dao.getOperator()						);
		rest.setPerc		(	dao.getPerc()							);
		rest.setReapeat		(	dao.getReapeat()						);
		rest.setRecord_type	(	dao.getRecord_type()					);
		rest.setReference	(	dao.getReference()						);
		rest.setValue		(	dao.getValue()							);
		rest.putLink		(	getSelfLink(dao)						);
		rest.putLink		(	getUserLink(dao)						);
		rest.putLink		(	getDeadlinesLink(dao)					);
		return rest;
	}
	public GoalCollectionRest toRest(GoalCollection dao) {
		GoalCollectionRest rest = new GoalCollectionRest();
		for(Goal d : dao)
			rest.add( toRest(d) );
		return rest;
	}
	
	public Goal toDao(GoalRest rest) {
		Goal dao = new Goal();
		dao.setId			(	getId(rest)								);
		dao.setData_name	(	rest.getData_name()						);
		dao.setDays			(	rest.getDays()							);
		dao.setFunction		(	rest.getFunction()						);
		dao.setOperator		(	rest.getOperator()						);
		dao.setPerc			(	rest.getPerc()							);
		dao.setReapeat		(	rest.getReapeat()						);
		dao.setRecord_type	(	rest.getRecord_type()					);
		dao.setReference	(	rest.getReference()						);
		dao.setValue		(	rest.getValue()							);
		dao.setUser			(	getUser(rest)							);
		return dao;
	}
	public GoalCollection toDao(GoalCollectionRest rest) {
		GoalCollection dao = new GoalCollection();
		for(GoalRest d : rest)
			dao.add( toDao(d) );
		return dao;
	}
	
	
	
	// To Rest
	
	public LinkRest<GoalRest.Relation> getSelfLink(Goal entity) {
		return LinkRest.create( GoalRest.Relation.self, PathFactory.goal().id(entity.getId()).getCompletePath() );
	}
	
	public LinkRest<GoalRest.Relation> getUserLink(Goal entity) {
		return LinkRest.create( GoalRest.Relation.user, PathFactory.user().id(entity.getUser().getId()).getCompletePath() );
	}
	
	public LinkRest<GoalRest.Relation> getDeadlinesLink(Goal entity) {
		return LinkRest.create( GoalRest.Relation.deadlines, PathFactory.goal().id(entity.getId()).deadline().getCompletePath() );
	}
	
	
	
	// To Dao
	
	public User getUser(GoalRest entity){
		return DaoFactory.goal.getById( getId(entity) ).getUser();
	}
	
	
}
