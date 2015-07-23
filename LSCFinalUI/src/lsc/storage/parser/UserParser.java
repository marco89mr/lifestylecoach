package lsc.storage.parser;

import lsc.rest.model.User;
import lsc.rest.model.UserCollection;


public class UserParser extends BaseParser<	User,
											UserCollection			> {


	@Override
	public User toDatabase(User entity) {
		return entity;
	}
	
	
	@Override
	public User toStorage(User entity) {
		return entity;
	}
	
	

	
	
	
	
	
}
