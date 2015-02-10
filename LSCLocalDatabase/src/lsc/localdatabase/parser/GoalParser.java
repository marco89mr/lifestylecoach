package lsc.localdatabase.parser;

import lsc.localdatabase.dao.dataaccess.LocalDatabaseDataAccess;
import lsc.localdatabase.dao.model.GoalDao;
import lsc.localdatabase.dao.model.GoalCollectionDao;
import lsc.rest.model.Goal;
import lsc.rest.model.GoalCollection;


public class GoalParser extends BaseParser<	GoalDao,
											GoalCollectionDao,
											Goal,
											GoalCollection		> {
	

	
	public Goal toRest(GoalDao dao) {
		Goal rest = new Goal();
		try{
		rest.setDataName	( dao.getData_name()						);
		}catch (IllegalArgumentException e){}
		try{
		rest.setDays		( dao.getDays()								);
		}catch (IllegalArgumentException e){}
		try{
		rest.setFunction	( Goal.Function.valueOf(dao.getFunction())	);
		}catch (IllegalArgumentException e){}
		try{
		rest.setOperator	( Goal.Operator.valueOf(dao.getOperator())	);
		}catch (IllegalArgumentException e){}
		try{
		rest.setPerc		( Goal.Perc.valueOf(dao.getPerc())			);
		}catch (IllegalArgumentException e){}
		try{
		rest.setRepeat		( Goal.Interval.valueOf(dao.getReapeat())	);
		}catch (IllegalArgumentException e){}
		try{
		rest.setRecordType	( dao.getRecord_type()						);
		}catch (IllegalArgumentException e){}
		try{
		rest.setReference	( Goal.Reference.valueOf(dao.getReference()));
		}catch (IllegalArgumentException e){}
		try{
		rest.setValue		( dao.getValue()							);
		}catch (IllegalArgumentException e){}
		try{
		rest.setId			( dao.getId()								);
		}catch (IllegalArgumentException e){}
		try{
		rest.setUserId		( dao.getUser().getId()						);
		}catch (IllegalArgumentException e){}
		return rest;
	}
	
	public GoalDao toDao(Goal rest) {
		GoalDao dao = new GoalDao();
		try{
			dao.setId			(	rest.getId()							);
		}catch (IllegalArgumentException e){}
		try{
			dao.setData_name	(	rest.getDataName()						);
		}catch (IllegalArgumentException e){}
		try{
			dao.setDays			(	rest.getDays()							);
		}catch (IllegalArgumentException e){}
		try{
			dao.setFunction		(	rest.getFunction().toString()			);
		}catch (IllegalArgumentException e){}
		try{
			dao.setOperator		(	rest.getOperator().toString()			);
		}catch (IllegalArgumentException e){}
		try{
			dao.setPerc			(	rest.getPerc().toString()				);
		}catch (IllegalArgumentException e){}
		try{
			dao.setReapeat		(	rest.getRepeat().toString()			);
		}catch (IllegalArgumentException e){}
		try{
			dao.setRecord_type	(	rest.getRecordType().toString()		);
		}catch (IllegalArgumentException e){}
		try{
			dao.setReference	(	rest.getReference().toString()			);
		}catch (IllegalArgumentException e){}
		try{
			dao.setValue		(	rest.getValue()							);
		}catch (IllegalArgumentException e){}
		try{
			dao.setUser			(	LocalDatabaseDataAccess.user.getById(rest.getUserId())	);
		}catch (IllegalArgumentException e){}
		return dao;
	}

	@Override
	protected GoalCollectionDao new_collection_dao() {
		return new GoalCollectionDao();
	}
	@Override
	protected GoalCollection new_collection() {
		return new GoalCollection();
	}
	
	
	
	
}
