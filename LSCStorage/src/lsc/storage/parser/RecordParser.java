package lsc.storage.parser;

import java.util.ArrayList;
import java.util.List;

import lsc.rest.model.Data;
import lsc.rest.model.DataCollection;
import lsc.rest.model.Record;
import lsc.rest.model.RecordCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.RecordComplexCollection;


public class RecordParser {
	
	
	// toRecordComplex
	
	public RecordComplex toRecordComplex(Record record, DataCollection data) {
		RecordComplex main = new RecordComplex();
		if(record == null)
			return main;
		main.setDate		(	record.getDate()			);
		main.setType		(	record.getType()			);
		main.setData		( 	toRecordComplex(data)		);
		main.setId			(	record.getId()				);
		main.setUserId		(	record.getUserId()		);
		return main;
	}
	public List<RecordComplex.Data> toRecordComplex(DataCollection collection) {
		List<RecordComplex.Data> list = new ArrayList<RecordComplex.Data>();
		if(collection == null) 
			return list;
		for(Data d : collection)
			list.add( toRecordComplex(d) );
		return list;
	}
	public RecordComplex.Data toRecordComplex(Data data) {
		RecordComplex.Data complex = new RecordComplex.Data();
		complex.setName		(	data.getName()				);
		complex.setValue	(	data.getValue()				);
		return complex;
	}
	
	
	// toRecord
	public RecordCollection toRecord(RecordComplexCollection complex) {
		RecordCollection collection = new RecordCollection();
		for(RecordComplex d : complex)
			collection.add( toRecord(d) );
		return collection;
	}
	public Record toRecord(RecordComplex complex) {
		Record record = new Record();
		record.setDate			(	complex.getDate()			);
		record.setType			(	complex.getType()			);
		record.setId			(	complex.getId()				);
		record.setUserId		(	complex.getUserId()	);
		return record;
	}
	
	
	// toData
	public DataCollection toData(RecordComplex complex) {
		DataCollection collection = new DataCollection();
		for(RecordComplex.Data d : complex.getData()) {
			collection.add( toData(complex.getId(), d) );
			//System.out.println( "toData data.recordId: " + collection.get(collection.size()-1).getRecordId() );
		}
		return collection;
	}
	public Data toData(int recordId, RecordComplex.Data complex) {
		Data data = new Data();
		data.setName		(	complex.getName()			);
		data.setValue		(	complex.getValue()			);
		data.setRecordId	(	recordId					);
		return data;
	}	
	
	
	
	
}
