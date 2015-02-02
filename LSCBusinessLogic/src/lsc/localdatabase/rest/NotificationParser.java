package lsc.localdatabase.rest;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Notification;
import lsc.localdatabase.dao.model.NotificationCollection;
import lsc.localdatabase.dao.model.User;
import lsc.localdatabase.rest.model.LinkRest;
import lsc.localdatabase.rest.model.NotificationCollectionRest;
import lsc.localdatabase.rest.model.NotificationRest;
import lsc.localdatabase.rest.path.PathFactory;


public class NotificationParser extends BaseParser<	Notification,
													NotificationCollection,
													NotificationRest,
													NotificationCollectionRest,
													NotificationRest.Relation> {
	

	
	public NotificationRest toRest(Notification dao) {
		NotificationRest rest = new NotificationRest();
		rest.setDate		(	dao.getDate()								);
		rest.setMessage		(	dao.getMessage()							);
		rest.setStatus		(	dao.getStatus()								);
		rest.setType		(	dao.getStatus()								);
		rest.putLink		(	getSelfLink(dao)							);
		rest.putLink		(	getUserLink(dao)							);
		rest.putLink		(	getDeadlinesLink(dao)						);
		return rest;
	}
	public NotificationCollectionRest toRest(NotificationCollection dao) {
		NotificationCollectionRest rest = new NotificationCollectionRest();
		for(Notification d : dao)
			rest.add( toRest(d) );
		return rest;
	}
	
	public Notification toDao(NotificationRest rest) {
		Notification dao = new Notification();
		dao.setId			(	getId(rest)						);
		dao.setDate			(	rest.getDate()					);
		dao.setMessage		(	rest.getMessage()				);
		dao.setStatus		(	rest.getStatus()				);
		dao.setType			(	rest.getStatus()				);
		dao.setUser			(	getUser(rest)					);
		return dao;
	}
	public NotificationCollection toDao(NotificationCollectionRest rest) {
		NotificationCollection dao = new NotificationCollection();
		for(NotificationRest d : rest)
			dao.add( toDao(d) );
		return dao;
	}
	
	
	
	// To Rest
	
	public LinkRest<NotificationRest.Relation> getSelfLink(Notification entity) {
		return LinkRest.create( NotificationRest.Relation.self, PathFactory.notification().id(entity.getId()).getCompletePath() );
	}
	
	public LinkRest<NotificationRest.Relation> getUserLink(Notification entity) {
		return LinkRest.create( NotificationRest.Relation.user, PathFactory.user().id(entity.getUser().getId()).getCompletePath() );
	}
	
	public LinkRest<NotificationRest.Relation> getDeadlinesLink(Notification entity) {
		return LinkRest.create( NotificationRest.Relation.deadline, PathFactory.deadline().id(entity.getDeadline().getId()).getCompletePath() );
	}
	
	
	
	// To Dao
	
	public int getId(NotificationRest entity){
		return entity.getSelfLink()._parseId();
	}
	
	public User getUser(NotificationRest entity){
		return DaoFactory.todo.getById( getId(entity) ).getUser();
	}
	
	
	
}
