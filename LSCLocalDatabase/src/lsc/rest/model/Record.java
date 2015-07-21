package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The persistent class for the "record" database table.
 * 
 */
@XmlType(name = "record", propOrder = { "id", "userId", "type", "date" })
@XmlRootElement(name="record")
public class Record extends Base {

	
	
	private int id;
	
	private int user_id;
	
	private String type;
	
	private String date;
	
	
	
	
	public Record() { }
	
	
	

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
	
	
	
	
}

