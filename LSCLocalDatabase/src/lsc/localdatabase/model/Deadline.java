package lsc.localdatabase.model;

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


@Entity
@Table(name="\"deadline\"")
@NamedQuery(name = "Deadline.findAll", query = "SELECT d FROM Deadline d")
//@XmlType(name = "Deadline", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="deadline")
public class Deadline extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_deadline")
	@TableGenerator(name="sqlite_deadline", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="deadline")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"goal_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private Goal goal;
	
	@Column(name = "\"start_date\"")
	private String start_date;
	
	@Column(name = "\"end_date\"")
	private String end_date;
	
	@Column(name = "\"status\"")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="\"record_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private Record record;
	
	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="deadline",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Notification> notification;
	
	
	
	
	
	public Deadline() { }
	
	
	

	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public Goal getGoal() { return goal; }

	public void setGoal(Goal goal) { this.goal = goal; }
	
	
	public String getStart_date() { return this.start_date; }
	
	public void setStart_date(String value) { this.start_date = value; }
	
	
	public String getEnd_date() { return this.end_date; }
	
	public void setEnd_date(String value) { this.end_date = value; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	

	@XmlTransient
	public Record getRecord() { return this.record; }
	
	public void setRecord(Record x) { this.record = x; }
	

	@XmlTransient
	public List<Notification> getNotification() { return this.notification; }
	
	public void setDeadline(List<Notification> param) { this.notification = param; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() {	return getLink("self"); }
	
	public void setSelfLink(Link link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link getGoalLink() { return getLink("goal"); }
	
	public void setGoalLink(Link link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link getRecordLink() { return getLink("record"); }
	
	public void setRecordLink(Link link) { putLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"deadline/"+this.id;
	}
	
	
}


