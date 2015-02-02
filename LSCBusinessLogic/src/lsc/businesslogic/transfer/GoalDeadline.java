package lsc.businesslogic.transfer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


//@XmlType(name = "goal", propOrder = { "selfLink", "userLink", "record_type", "data_name", "operator", "value", "function", "reference", "perc", "repeat", "days", "deadlineLink" })
@XmlRootElement(name="goal")
public class GoalDeadline implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private int user_id;
	
	private String record_type;
	
	private String field_name;
	
	private String operator;
	
	private String value;
	
	private String function;
	
	private String reference;
	
	private String perc;
	
	private String repeat;
	
	private String days;
	
	//from here it's about deadline
	
	private	int deadline_id;
	
	private	String start_date;
	
	private String deadline_date;
	
	private String status;
	
	private	int next_deadline_id;
	
	
	
	
	
	
	public GoalDeadline() { }
	
	
	
	
	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public int getUser_id() { return user_id; }
	
	public void setUser_id(int user_id) { this.user_id = user_id;	}
	
	
	public String getRecord_type() { return this.record_type; }
	
	public void setRecord_type(String value) { this.record_type = value; }
	
	
	public String getField_name() { return this.field_name; }
	
	public void setField_name(String value) { this.field_name = value; }
	
	
	public String getOperator() { return this.operator; }
	
	public void setOperator(String operator) { this.operator = operator; }
	
	
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	public String getFunction() { return this.function; }
	
	public void setFunction(String x) { this.function = x; }
	
	
	public String getReference() { return this.reference; }
	
	public void setReference(String x) { this.reference = x; }
	
	
	public String getPerc() { return this.perc; }
	
	public void setPerc(String x) { this.perc = x; }
	
	
	public String getReapeat() { return this.repeat; }
	
	public void setReapeat(String x) { this.repeat = x; }
	
	
	public String getDays() { return this.days; }
	
	public void setDays(String x) { this.days = x; }
	
	
	//from here it's about deadline
	

	@XmlTransient
	public int getDeadline_id() { return this.deadline_id; }
	
	public void setDeadline_id(int id) { this.deadline_id = id; }
	

	public String getStart_date() { return this.start_date; }
	
	public void setStart_date(String date) { this.start_date = date; }
	

	public String getDeadline_date() { return this.deadline_date; }
	
	public void setDeadline_date(String date) { this.deadline_date = date; }
	

	public String getStatus() { return this.status; }
	
	public void setStatus(String status) { this.status = status; }
	

	@XmlTransient
	public int getNext_deadline_id() { return this.next_deadline_id; }
	
	public void setNext_deadline_id(int id) { this.next_deadline_id = id; }
	
	

	
}


