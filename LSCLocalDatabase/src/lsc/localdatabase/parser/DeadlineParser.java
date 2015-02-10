package lsc.localdatabase.parser;

import lsc.localdatabase.dao.dataaccess.LocalDatabaseDataAccess;
import lsc.localdatabase.dao.model.DeadlineDao;
import lsc.localdatabase.dao.model.DeadlineCollectionDao;
import lsc.rest.model.Deadline;
import lsc.rest.model.DeadlineCollection;


public class DeadlineParser extends BaseParser			<	DeadlineDao,
															DeadlineCollectionDao,
															Deadline,
															DeadlineCollection		> {
	

	
	public Deadline toRest(DeadlineDao dao) {
		Deadline rest = new Deadline();
		try{
		rest.setEndDate	(	dao.getEnd_date()								);
		}catch (IllegalArgumentException e){}
		try{
		rest.setStartDate	(	dao.getStart_date()							);
		}catch (IllegalArgumentException e){}
		try{
		rest.setStatus		(	Deadline.Status.valueOf(dao.getStatus())	);
		}catch (IllegalArgumentException e){}
		try{
		rest.setActualValue	(	dao.getActualValue()						);
		}catch (IllegalArgumentException e){}
		try{
		rest.setId			(	dao.getId()									);
		}catch (IllegalArgumentException e){}
		try{
		rest.setGoalId		(	dao.getGoal().getId()						);
		}catch (IllegalArgumentException e){}
		return rest;
	}

	public DeadlineDao toDao(Deadline rest) {
		DeadlineDao dao = new DeadlineDao();
		try{
		dao.setId			(	rest.getId()								);
		}catch (IllegalArgumentException e){}
		try{
		dao.setEnd_date		(	rest.getEndDate()							);
		}catch (IllegalArgumentException e){}
		try{
		dao.setStart_date	(	rest.getStartDate()						);
		}catch (IllegalArgumentException e){}
		try{
		dao.setStatus		(	rest.getStatus().toString()					);
		}catch (IllegalArgumentException e){}
		try{
		dao.setActualValue	(	rest.getActualValue()						);
		}catch (IllegalArgumentException e){}
		try{
		dao.setGoal			(	LocalDatabaseDataAccess.goal.getById(rest.getGoalId())	);
		}catch (IllegalArgumentException e){}
		return dao;
	}
	
	@Override
	protected DeadlineCollectionDao new_collection_dao() {
		return new DeadlineCollectionDao();
	}
	@Override
	protected DeadlineCollection new_collection() {
		return new DeadlineCollection();
	}
	
	
	
	
}
