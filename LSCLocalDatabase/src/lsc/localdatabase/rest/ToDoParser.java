package lsc.localdatabase.rest;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.ToDo;
import lsc.localdatabase.dao.model.ToDoCollection;
import lsc.localdatabase.dao.model.User;
import lsc.localdatabase.rest.model.LinkRest;
import lsc.localdatabase.rest.model.ToDoCollectionRest;
import lsc.localdatabase.rest.model.ToDoRest;
import lsc.localdatabase.rest.path.PathFactory;


public class ToDoParser extends BaseParser<	ToDo,
											ToDoCollection,
											ToDoRest,
											ToDoCollectionRest,
											ToDoRest.Relation			> {
	

	
	public ToDoRest toRest(ToDo dao) {
		ToDoRest rest = new ToDoRest();
		rest.setBy_date		(	dao.getBy_date()						);
		rest.setMessage		(	dao.getMessage()						);
		rest.setStatus		(	dao.getStatus()							);
		rest.putLink		(	getSelfLink(dao)						);
		rest.putLink		(	getUserLink(dao)						);
		return rest;
	}
	public ToDoCollectionRest toRest(ToDoCollection dao) {
		ToDoCollectionRest rest = new ToDoCollectionRest();
		for(ToDo d : dao)
			rest.add( toRest(d) );
		return rest;
	}
	
	public ToDo toDao(ToDoRest rest) {
		ToDo dao = new ToDo();
		dao.setId			(	getId(rest)								);
		dao.setBy_date		(	rest.getBy_date()						);
		dao.setMessage		(	rest.getMessage()						);
		dao.setStatus		(	rest.getStatus()						);
		return dao;
	}
	public ToDoCollection toDao(ToDoCollectionRest rest) {
		ToDoCollection dao = new ToDoCollection();
		for(ToDoRest d : rest)
			dao.add( toDao(d) );
		return dao;
	}
	
	
	
	// To Rest
	
	public LinkRest<ToDoRest.Relation> getSelfLink(ToDo entity) {
		return LinkRest.create( ToDoRest.Relation.self, PathFactory.record().id(entity.getId()).getCompletePath() );
	}
	
	public LinkRest<ToDoRest.Relation> getUserLink(ToDo entity) {
		return LinkRest.create( ToDoRest.Relation.user, PathFactory.record().id(entity.getUser().getId()).getCompletePath() );
	}
	
	
	
	// To Dao
		
	public User getUser(ToDoRest entity){
		return DaoFactory.todo.getById( getId(entity) ).getUser();
	}
	
	
}
