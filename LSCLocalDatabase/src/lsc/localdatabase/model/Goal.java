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
@Table(name="\"goal\"")
@NamedQuery(name = "Goal.findAll", query = "SELECT g FROM Goal g")
//@XmlType(name = "goal", propOrder = { "selfLink", "userLink", "record_type", "data_name", "operator", "value", "function", "reference", "perc", "repeat", "days", "deadlineLink" })
@XmlRootElement(name="goal")
public class Goal extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_goal")
	@TableGenerator(name="sqlite_goal", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="goal")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"user_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "\"record_type\"")
	private String record_type;
	
	@Column(name = "\"data_name\"")
	private String data_name;
	
	@Column(name = "\"operator\"")
	private String operator;
	
	@Column(name = "\"value\"")
	private String value;
	
	@Column(name = "\"function\"")
	private String function;
	
	@Column(name = "\"reference\"")
	private String reference;
	
	@Column(name = "\"perc\"")
	private String perc;
	
	@Column(name = "\"repeat\"")
	private String repeat;
	
	@Column(name = "\"days\"")
	private String days;

	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="goal",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Deadline> deadline;
	
	
	
	
	
	public Goal() { }
	
	
	
	
	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getRecord_type() { return this.record_type; }
	
	public void setRecord_type(String value) { this.record_type = value; }
	
	
	public String getData_name() { return this.data_name; }
	
	public void setData_name(String value) { this.data_name = value; }
	
	
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
	

	@XmlTransient
	public List<Deadline> getDeadline() { return this.deadline; }
	
	public void setDeadline(List<Deadline> param) { this.deadline = param; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return getLink("self"); }
	
	public void setSelfLink(Link link) { putLink(link); }
	
	@XmlElement(name="link")
	public Link getUserLink() { return getLink("user"); }
	
	public void setUserLink(Link link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link getDeadlineLink() { return getLink("deadlines"); }
	
	public void setDeadlineLink(Link link) { putLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"goal/"+this.getId();
	}
	
}


