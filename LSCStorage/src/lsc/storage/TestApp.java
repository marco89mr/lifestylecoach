package lsc.storage;

import java.io.IOException;
import java.net.URISyntaxException;

import lsc.localdatabase.rest.client.LocalDatabaseClient;
import lsc.rest.filter.Filter;
import lsc.rest.model.DataCollection;
import lsc.rest.model.Record;
import lsc.rest.model.RecordCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.RecordComplexCollection;
import lsc.storage.parser.RecordParser;
import lsc.storage.parser.StorageParser;


public class TestApp {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
	{
		
		
		
		
		//DataCollection dd = LocalDatabaseClient.data.getAll(Filter.data);
		
		//StorageParser.data.toStorage( dd );
		
		
		
		
		
		
		
		
		
		RecordCollection recs = LocalDatabaseClient.record.getAll( Filter.record() );
		RecordComplexCollection recsc = new RecordComplexCollection();
		for(Record r : recs){
			int record_id = r.getId();
			Filter.DataFilter data_filters = Filter.data().record_id(record_id);
			DataCollection datas = LocalDatabaseClient.data.getAll( data_filters );
			RecordComplex rc = StorageParser.record.toRecordComplex(r, datas);
			recsc.add(rc);
		}
		
		for(RecordComplex rc : recsc) {
			System.out.println(rc.getId());
			System.out.println(rc.getData());
		}
		
		
		
	}
	
}
