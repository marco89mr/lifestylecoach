package lsc.localdatabase.parser;

import lsc.localdatabase.dao.model.UserDao;
import lsc.localdatabase.dao.model.UserCollectionDao;
import lsc.rest.model.User;
import lsc.rest.model.UserCollection;


public class UserParser extends BaseParser			<	UserDao,
														UserCollectionDao,
														User,
														UserCollection		> {

	
	public User toRest(UserDao entity) {
		User user = new User();
		try{
			user.setName		(	entity.getName()							);
		}catch (IllegalArgumentException e){}
		try{
			user.setMail		(	entity.getMail()							);
		}catch (IllegalArgumentException e){}
		try{
			user.setBirthdate	(	entity.getBirthdate()						);
		}catch (IllegalArgumentException e){}
		try{
			user.setPassword	(	entity.getPassword()						);
		}catch (IllegalArgumentException e){}
		try{
			user.setId			(	entity.getId()								);
		}catch (IllegalArgumentException e){}
		return user;
	}
	
	public UserDao toDao(User entity) {
		UserDao userDao = new UserDao();
		try{
			userDao.setId		(	entity.getId()				);
		}catch (IllegalArgumentException e){}
		try{
			userDao.setName		(	entity.getName()			);
		}catch (IllegalArgumentException e){}
		try{
			userDao.setMail		(	entity.getMail()			);
		}catch (IllegalArgumentException e){}
		try{
			userDao.setBirthdate(	entity.getBirthdate()		);
		}catch (IllegalArgumentException e){}
		try{
			userDao.setPassword	(	entity.getPassword()		);
		}catch (IllegalArgumentException e){}
		return userDao;
	}

	@Override
	protected UserCollectionDao new_collection_dao() {
		return new UserCollectionDao();
	}
	@Override
	protected UserCollection new_collection() {
		return new UserCollection();
	}
	
	
	
	
}
