package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "Deadline", propOrder = { "id", "goalId", "startDate", "endDate", "status", "actualValue" })
@XmlRootElement(name="deadline")
public class Deadline extends Base {

	public enum Status {
		active, sleeping, succeeded, failed;
	};
	
	
	
	
	
	private int id;
	
	private int goal_id;
	
	private String start_date;
	
	private String end_date;
	
	private Status status;
	
	private String actual_value;
	
	
	
	
	
	public Deadline() { }
	
	
	

	
	@XmlElement(name="id")
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	@XmlElement
	public int getGoalId() { return this.goal_id; }
	
	public void setGoalId(int id) { this.goal_id = id; }
	
	
	public String getStartDate() { return this.start_date; }
	
	public void setStartDate(String value) { this.start_date = value; }
	
	
	public String getEndDate() { return this.end_date; }
	
	public void setEndDate(String value) { this.end_date = value; }
	
	
	public Status getStatus() { return this.status; }
	
	public void setStatus(Status x) { this.status = x; }
	
	
	public String getActualValue() { return this.actual_value; }
	
	public void setActualValue(String x) { this.actual_value = x; }
	
	
}


