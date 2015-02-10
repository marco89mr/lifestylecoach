package lsc.storage.parser;

import lsc.rest.model.Deadline;
import lsc.rest.model.DeadlineCollection;


public class DeadlineParser extends BaseParser			<	Deadline,
															DeadlineCollection		> {
	
	
	
	@Override
	public Deadline toStorage(Deadline entity) {
		return entity;
	}
	
	
	@Override
	public Deadline toDatabase(Deadline entity) {
		return entity;
	}
	
	
	
}
