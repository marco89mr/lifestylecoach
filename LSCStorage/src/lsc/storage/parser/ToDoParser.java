package lsc.storage.parser;

import lsc.rest.model.ToDo;
import lsc.rest.model.ToDoCollection;


public class ToDoParser extends BaseParser<	ToDo,
											ToDoCollection			> {
	
	

	@Override
	public ToDo toStorage(ToDo entity) {
		return entity;
	}

	@Override
	public ToDo toDatabase(ToDo entity) {
		return entity;
	}
	
}
