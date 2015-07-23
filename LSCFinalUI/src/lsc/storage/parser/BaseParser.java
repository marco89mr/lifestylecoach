package lsc.storage.parser;

import lsc.rest.model.Base;
import lsc.rest.model.BaseCollection;


public abstract class BaseParser <	M extends Base,
									MM extends BaseCollection<M>			> {
	
	
	
	// to Storage
	
	public abstract M toStorage(M entity);
	public MM toStorage(MM collection) {
		for(M d : collection)
			toStorage(d);
		return collection;
	}
	
	

	// to LocalDatabase
	
	public abstract M toDatabase(M entity);
	public MM toDatabase(MM collection) {
		for(M d : collection)
			toDatabase(d);
		return collection;
	}
	
	
	
	
}
