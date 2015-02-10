package lsc.localdatabase.parser;

import lsc.localdatabase.dao.dataaccess.LocalDatabaseDataAccess;
import lsc.localdatabase.dao.model.NotificationCollectionDao;
import lsc.localdatabase.dao.model.NotificationDao;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;


public class NotificationParser extends BaseParser<	NotificationDao,
													NotificationCollectionDao,
													Notification,
													NotificationCollection		> {
	

	
	public Notification toRest(NotificationDao dao) {
		Notification rest = new Notification();
		try{
		rest.setDate		(	dao.getDate()									);
		}catch (IllegalArgumentException e){}
		try{
		rest.setMessage		(	dao.getMessage()								);
		}catch (IllegalArgumentException e){}
		try{if(dao.getStatus()!=null)
			rest.setStatus	(	Notification.Status.valueOf(dao.getStatus())	);
		}catch (IllegalArgumentException e){}
		try{
		rest.setType		(	dao.getType()									);
		}catch (IllegalArgumentException e){}
		try{
		rest.setId			(	dao.getId()										);
		}catch (IllegalArgumentException e){}
		try{
		rest.setUserId		(	dao.getUser().getId()							);
		}catch (NullPointerException e){}
		try{
		rest.setDeadlineId	(	dao.getDeadline().getId()						);
		}catch (NullPointerException e){}
		return rest;
	}
	
	public NotificationDao toDao(Notification rest) {
		NotificationDao dao = new NotificationDao();
		try{
		dao.setId			( rest.getId()						);
		}catch (IllegalArgumentException e){}
		try{
		dao.setDate			( rest.getDate()					);
		}catch (IllegalArgumentException e){}
		try{
		dao.setMessage		( rest.getMessage()					);
		}catch (IllegalArgumentException e){}
		try{if(rest.getStatus()!=null)
		dao.setStatus		( rest.getStatus().toString()		);
		}catch (IllegalArgumentException e){}
		try{
		dao.setType			( rest.getType()					);
		}catch (IllegalArgumentException e){}
		try{
		dao.setUser			( LocalDatabaseDataAccess.user.getById( rest.getUserId() )	);
		}catch (IllegalArgumentException e){}
		try{
		dao.setDeadline		( LocalDatabaseDataAccess.deadline.getById( rest.getDeadlineId() )	);
		}catch (NullPointerException e){}
		return dao;
	}
	
	@Override
	protected NotificationCollectionDao new_collection_dao() {
		return new NotificationCollectionDao();
	}
	@Override
	protected NotificationCollection new_collection() {
		return new NotificationCollection();
	}
	
	
	
	
}
