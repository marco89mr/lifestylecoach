package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlType(name = "todo", propOrder = { "id", "userId", "byDate", "message", "status" })
@XmlRootElement(name="todo")
public class ToDo extends Base {

	public enum Status {
		active, passed, succeeded, failed;
	};
	
	
	
	
	private int id;
	
	private int user_id;
	
	private String by_date;
	
	private String message;
	
	private Status status;
	
	
	
	
	
	
	public ToDo() { }
	
	
	
	
	
	@XmlElement(name="id")
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	@XmlElement
	public int getUserId() { return this.user_id; }
	
	public void setUserId(int id) { this.user_id = id; }
	
	
	public String getByDate() { return this.by_date; }
	
	public void setByDate(String value) { this.by_date = value; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public Status getStatus() { return this.status; }
	
	public void setStatus(Status x) { this.status = x; }
	
	
	
	
}


