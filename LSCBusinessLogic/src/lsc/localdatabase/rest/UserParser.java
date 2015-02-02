package lsc.localdatabase.rest;

import java.util.List;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Record;
import lsc.localdatabase.dao.model.User;
import lsc.localdatabase.dao.model.UserCollection;
import lsc.localdatabase.rest.model.LinkRest;
import lsc.localdatabase.rest.model.UserCollectionRest;
import lsc.localdatabase.rest.model.UserRest;
import lsc.localdatabase.rest.path.PathFactory;


public class UserParser extends BaseParser			<	User,
														UserCollection,
														UserRest,
														UserCollectionRest,
														UserRest.Relation			> {

	
	public UserRest toRest(User entity) {
		UserRest user = new UserRest();
		user.setName		(	entity.getName()							);
		user.setMail		(	entity.getMail()							);
		user.setBirthdate	(	entity.getBirthdate()						);
		user.setPassword	(	entity.getPassword()						);
		user.putLink		(	getSelfLink(entity)							);
		user.putLink		(	getRecordsLink(entity)						);
		user.putLink		(	getGoalsLink(entity)						);
		user.putLink		(	getNotificationsLink(entity)				);
		user.putLink		(	getTodosLink(entity)						);
		return user;
	}
	public UserCollectionRest toRest(UserCollection dao) {
		UserCollectionRest rest = new UserCollectionRest();
		for(User d : dao)
			rest.add( toRest(d) );
		return rest;
	}
	
	public User toDao(UserRest entity) {
		User user = new User();
		user.setId			(	getId(entity)				);
		user.setName		(	entity.getName()			);
		user.setMail		(	entity.getMail()			);
		user.setBirthdate	(	entity.getBirthdate()		);
		user.setPassword	(	entity.getPassword()		);
		return user;
	}
	public UserCollection toDao(UserCollectionRest rest) {
		UserCollection dao = new UserCollection();
		for(UserRest d : rest)
			dao.add( toDao(d) );
		return dao;
	}
	
	
	
	// To Rest
	
	public LinkRest<UserRest.Relation> getSelfLink(User entity) {
		return LinkRest.create( UserRest.Relation.self, PathFactory.user().id(entity.getId()).getCompletePath() );
	}
	
	public LinkRest<UserRest.Relation> getRecordsLink(User entity) {
		return LinkRest.create( UserRest.Relation.records, PathFactory.user().id(entity.getId()).record().getCompletePath() );
	}
	
	public LinkRest<UserRest.Relation> getGoalsLink(User entity) {
		return LinkRest.create( UserRest.Relation.goals, PathFactory.user().id(entity.getId()).goal().getCompletePath() );
	}
	
	public LinkRest<UserRest.Relation> getNotificationsLink(User entity) {
		return LinkRest.create( UserRest.Relation.notifications, PathFactory.user().id(entity.getId()).notification().getCompletePath() );
	}
	
	public LinkRest<UserRest.Relation> getTodosLink(User entity) {
		return LinkRest.create( UserRest.Relation.todos, PathFactory.user().id(entity.getId()).todo().getCompletePath() );
	}
	
	
	
	// To Dao
	/*
	public int getId(UserRest entity){
		return entity.getSelfLink()._parseId();
	}
	*/
	
	public List<Record> getRecords(UserRest entity){
		return DaoFactory.user.getById( getId(entity) ).getRecord();
	}
	
	
	
}
