package lsc.humanapiadapter.ws;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;

import co.humanapi.client.HumanAPIClient;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment.ws.People", serviceName="PeopleService")
public class HumanAPIAdapterImpl implements LSCLogic {
	
	@Override
	public List<Person> readPersonList() {
		
		
		
		HumanAPIClient client = new HumanAPIClient();
		client.humanEntity().get();
		
		
		
		
		
		System.out.println("---> readPersonList");
		return Person.getAll();
	}
	
	@Override
	public Person readPerson(int id) {
		System.out.println("---> readPerson "+id);
		Person p = Person.getById(id);
		if (p!=null)
			System.out.println("-> Found Person by id = "+id+" => "+p.getFirstname());
		else
			System.out.println("-> Didn't find any Person with  id = "+id);
		return p;
	}
	
	@Override
	public Person updatePerson(Person person) {
		System.out.println("---> updatePerson");
		Person.update(person);
		return person;
	}
	
	@Override
	public Person createPerson(Person person) {
		System.out.println("---> createPerson");
		person = Person.save(person);
		return person;
	}
	
	@Override
	public int deletePerson(int id) {
		System.out.println("---> deletePerson "+id);
		Person p = Person.getById(id);
		if (p!=null) {
			Person.remove(p);
			return 0;
		} else {
			return -1;
		}
	}
    
	@Override
    public List<Measure> readPersonHistory( int id, String measureType ) {
		System.out.println("---> readPersonHistory "+id+" "+measureType);
		List<Measure> history = Person.getById(id).getHealthHistory();
		List<Measure> historyByType = new ArrayList<Measure>();
		for( Measure m : history)
			if( m.getMeasureType().equals(measureType) )
				historyByType.add(m);
		System.out.println("___ total size: "+history.size());
		System.out.println("___ typed size: "+historyByType.size());
		return historyByType;
	}
	
	@Override
    public Measure readPersonMeasurement( int id, String measureType, int mid ) {
		System.out.println("---> readPersonMeasurement "+id+" "+measureType+" "+mid);
		/*
		for( Measure m : introsde.assignment.model.Person.getById(id).getHealthHistory() )
			if( m.getMeasureType().equals(measureType) )
				if( m.getMid() == mid )
					return m;
		return null;
		*/
		return Measure.getById(mid);
	}
	
	@Override
    public Measure savePersonMeasurement( int id, Measure m ) {
		System.out.println("---> savePersonMeasurement "+id+" value:"+m.getMeasureValue());
		m.setPerson( Person.getById(id) );
		m = Measure.save(m);
		return Measure.getById( m.getMid() );
	}
	
	@Override
    public List<String> readMeasureTypes() {
		System.out.println("---> readMeasureTypes ");
		List<String> measuretypes = new ArrayList<String>();
		for( Measure m : Measure.getAll() )
			if( !measuretypes.contains( m.getMeasureType() ) )
				measuretypes.add( m.getMeasureType() );
		return measuretypes;
	}
	
	@Override
    public Measure updatePersonMeasure( int id, Measure m ) {
		System.out.println("---> updatePersonMeasure "+id+" value:"+m.getMeasureValue());
		m.setPerson( Person.getById(id) );
		Measure.update(m);
		return Measure.getById( m.getMid() );
	}
	
	@Override
    public List<Measure> readPersonMeasureByDates( int id, String measureType, XMLGregorianCalendar before, XMLGregorianCalendar after) {
		System.out.println("---> readPersonMeasureByDates "+id+
														" "+measureType+
														" "+before.toString()+
														" "+after.toString());
		List<Measure> filteredHistory = new ArrayList<Measure>();
		for( Measure m : Person.getById(id).getHealthHistory() )
			if( m.getMeasureType().equals(measureType) )
				if( m.getDateRegistered().toGregorianCalendar().after(after) )
					if( m.getDateRegistered().toGregorianCalendar().before(before) )
						filteredHistory.add(m);
		return filteredHistory;
	}
	
	@Override
    public List<Person> readPersonListByMeasurement(String measureType, String maxValue, String minValue) {
		System.out.println("---> readPersonListByMeasurement "+measureType+
															" "+maxValue+
															" "+minValue);
		if(maxValue.equals(""))
			maxValue = String.valueOf( Integer.MAX_VALUE );
		if(minValue.equals(""))
			minValue = String.valueOf( Integer.MIN_VALUE );
		List<Person> people = new ArrayList<Person>();
		for(Person p : Person.getAll() )
			for(Measure m : p.getCurrentHealth())
				if(m.getMeasureType().equals(measureType))
					if(Integer.parseInt(m.getMeasureValue())<=Integer.parseInt(maxValue))
						if(Integer.parseInt(m.getMeasureValue())>=Integer.parseInt(minValue))
							people.add(p);
		return people;
	}
    
    
	
}
