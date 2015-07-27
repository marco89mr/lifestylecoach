package lsc.localdatabase.parser;

import lsc.localdatabase.dao.dataaccess.LocalDatabaseDataAccess;
import lsc.localdatabase.dao.model.ToDoDao;
import lsc.localdatabase.dao.model.ToDoCollectionDao;
import lsc.rest.model.Base;
import lsc.rest.model.ToDo;
import lsc.rest.model.ToDoCollection;


public class ToDoParser extends BaseParser<	ToDoDao,
											ToDoCollectionDao,
											ToDo,
											ToDoCollection		> {
	

	
	public ToDo toRest(ToDoDao dao) {
		ToDo rest = new ToDo();
		try{
		rest.setByDate		(	dao.getBy_date()						);
		}catch (IllegalArgumentException e){}
		try{
		rest.setMessage		(	dao.getMessage()						);
		}catch (IllegalArgumentException e){}
		try{
		rest.setStatus		(	ToDo.Status.valueOf(dao.getStatus())	);
		}catch (IllegalArgumentException e){}
		try{
		rest.setId			(	dao.getId()								);
		}catch (IllegalArgumentException e){}
		try{
		rest.setUserId		(	dao.getUser().getId()					);
		}catch (IllegalArgumentException e){}
		return rest;
	}
	
	public ToDoDao toDao(ToDo rest) {
		ToDoDao dao = new ToDoDao();
		try{
		dao.setId			( rest.getId()							);
		}catch (IllegalArgumentException e){}
		try{
		dao.setBy_date		( Base._uniformDate(rest.getByDate())	);
		}catch (IllegalArgumentException e){}
		try{
		dao.setMessage		( rest.getMessage()						);
		}catch (IllegalArgumentException e){}
		try{
		dao.setStatus		( rest.getStatus().toString()			);
		}catch (IllegalArgumentException e){}
		try{
		dao.setUser			( LocalDatabaseDataAccess.user.getById(rest.getUserId())	);
		}catch (IllegalArgumentException e){}
		return dao;
	}

	@Override
	protected ToDoCollectionDao new_collection_dao() {
		return new ToDoCollectionDao();
	}
	@Override
	protected ToDoCollection new_collection() {
		return new ToDoCollection();
	}
	
	
	
}
