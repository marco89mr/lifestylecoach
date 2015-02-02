package lsc.localdatabase.rest;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Deadline;
import lsc.localdatabase.dao.model.DeadlineCollection;
import lsc.localdatabase.dao.model.Goal;
import lsc.localdatabase.dao.model.Record;
import lsc.localdatabase.rest.model.DeadlineCollectionRest;
import lsc.localdatabase.rest.model.DeadlineRest;
import lsc.localdatabase.rest.model.LinkRest;
import lsc.localdatabase.rest.path.PathFactory;


public class DeadlineParser extends BaseParser			<	Deadline,
															DeadlineCollection,
															DeadlineRest,
															DeadlineCollectionRest,
															DeadlineRest.Relation		> {
	

	
	public DeadlineRest toRest(Deadline dao) {
		DeadlineRest rest = new DeadlineRest();
		rest.setEnd_date	(	dao.getEnd_date()						);
		rest.setStart_date	(	dao.getStart_date()						);
		rest.setStatus		(	dao.getStatus()							);
		rest.putLink		(	getSelfLink(dao)						);
		rest.putLink		(	getRecordLink(dao)						);
		rest.putLink		(	getGoalLink(dao)						);
		return rest;
	}
	public DeadlineCollectionRest toRest(DeadlineCollection dao) {
		DeadlineCollectionRest rest = new DeadlineCollectionRest();
		for(Deadline d : dao)
			rest.add( toRest(d) );
		return rest;
	}
	
	public Deadline toDao(DeadlineRest rest) {
		Deadline dao = new Deadline();
		dao.setId			(	getId(rest)								);
		dao.setEnd_date		(	rest.getEnd_date()						);
		dao.setStart_date	(	rest.getStart_date()					);
		dao.setStatus		(	rest.getStatus()						);
		dao.setRecord		(	getRecord(rest)							);
		dao.setGoal			(	getGoal(rest)							);
		return dao;
	}
	public DeadlineCollection toDao(DeadlineCollectionRest rest) {
		DeadlineCollection dao = new DeadlineCollection();
		for(DeadlineRest d : rest)
			dao.add( toDao(d) );
		return dao;
	}
	
	
	
	// To Rest
	
	public LinkRest<DeadlineRest.Relation> getSelfLink(Deadline entity) {
		return LinkRest.create( DeadlineRest.Relation.self, PathFactory.deadline().id(entity.getId()).getCompletePath() );
	}
	
	public LinkRest<DeadlineRest.Relation> getRecordLink(Deadline entity) {
		return LinkRest.create( DeadlineRest.Relation.record, PathFactory.record().id(entity.getRecord().getId()).getCompletePath() );
	}
	
	public LinkRest<DeadlineRest.Relation> getGoalLink(Deadline entity) {
		return LinkRest.create( DeadlineRest.Relation.goal, PathFactory.goal().id(entity.getRecord().getId()).getCompletePath() );
	}
	
	
	
	// To Dao
	
	public Record getRecord(DeadlineRest entity){
		return DaoFactory.deadline.getById( getId(entity) ).getRecord();
	}
	
	public Goal getGoal(DeadlineRest entity){
		return DaoFactory.deadline.getById( getId(entity) ).getGoal();
	}
	
	
}
