package lsc.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The persistent class for the "record" database table.
 * 
 */
@XmlType(name = "record", propOrder = { "id", "userId", "type", "date", "data" })
@XmlRootElement(name="record")
public class RecordComplex extends Base {

	@XmlType(name = "data", propOrder = {"name", "value"} )
	@XmlRootElement(name="data")
	public static class Data {
		
		private String name;
		private String value;
		
		public Data() { }
		
		@XmlElement(name="name")
		public String getName() { return this.name; }
		public void setName(String name) { this.name = name; }
		
		@XmlElement(name="value")
		public String getValue() { return this.value; }
		public void setValue(String value) { this.value = value; }
	}
	

	private int id;
	
	private int user_id;
	
	private String type;

	private String date;
	
	private List<Data> data;
	
	
	
	
	public RecordComplex() { }
	
	
	

	@XmlElement(name="id")
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	@XmlElement
	public int getUserId() { return this.user_id; }
	
	public void setUserId(int id) { this.user_id = id; }
	
	
	@XmlElement
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	@XmlElement
	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	@XmlElementWrapper(name="datas")
	@XmlElement(name="data")
	public List<Data> getData() { return this.data; }
	
	public void setData(List<Data> data) { this.data = data; }
	
	
	
	
	
	
	
}

