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


//@XmlType(name = "goal", propOrder = { "selfLink", "userLink", "record_type", "data_name", "operator", "value", "function", "reference", "perc", "repeat", "days", "deadlineLink" })
@XmlRootElement(name="goal")
public class Goal extends EntityModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private User user;
	
	private String record_type;
	
	private String data_name;
	
	private String operator;
	
	private String value;
	
	private String function;
	
	private String reference;
	
	private String perc;
	
	private String repeat;
	
	private String days;

	private List<Deadline> deadline;
	
	private Link user_link;
	
	
	
	
	
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
	public Link getSelfLink() { return Link.create("self", this._getUrl() ); }
	
	public void setSelfLink(Link link) {
		if(link.getRel().equals("user"))
			this.user_link = link;
	}
	
	@XmlElement(name="link")
	public Link getUserLink() { return Link.create("user", this.user._getUrl() ); }
	
	public void setUserLink(Link link) { setSelfLink(link); }
	
	
	@XmlElement(name="link")
	public Link getDeadlineLink() { return Link.create("deadlines", this._getUrl()+"/deadline"); }
	
	public void setDeadlineLink(Link link) { setSelfLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"goal/"+this.getId();
	}
	
}


