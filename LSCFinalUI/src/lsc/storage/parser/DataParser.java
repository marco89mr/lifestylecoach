package lsc.storage.parser;

import lsc.rest.model.Data;
import lsc.rest.model.DataCollection;


public class DataParser extends BaseParser<	Data,
											DataCollection	> {
	
	


	@Override
	public Data toStorage(Data entity) {
		return entity;
	}

	@Override
	public Data toDatabase(Data entity) {
		return entity;
	}
	
	
}
