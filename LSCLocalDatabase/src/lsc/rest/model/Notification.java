package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "notification", propOrder = { "id", "userId", "date", "message", "type", "status", "deadlineId", "external_resource" })
@XmlRootElement(name="notification")
public class Notification extends Base {

	public enum Status {
		read, unread;
	};
	
	
	
	
	
	
	private int id;
	
	private int user_id;
	
	private String date;
	
	private String type;

	private String message;
	
	private Status status;
	
	private int deadline_id;
	
	
	
	
	
	
	public Notification() { }
	
	
	

	@XmlElement(name="id")
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	@XmlElement
	public int getUserId() { return this.user_id; }
	
	public void setUserId(int id) { this.user_id = id; }
	
	
	@XmlElement
	public String getExternal_resource() { return "User"; }
	
	
	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public Status getStatus() { return this.status; }
	
	public void setStatus(Status x) { this.status = x; }
	
	@XmlElement
	public int getDeadlineId() { return this.deadline_id; }
	
	public void setDeadlineId(int id) { this.deadline_id = id; }
	
	
	
	
	
	
	
}


