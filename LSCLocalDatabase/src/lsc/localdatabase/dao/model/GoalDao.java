package lsc.localdatabase.dao.model;

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


@Entity
@Table(name="\"goal\"")
@NamedQuery(name = "Goal.findAll", query = "SELECT g FROM GoalDao g")
public class GoalDao extends BaseDao {
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
	private UserDao user;
	
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
	private List<DeadlineDao> deadline;
	
	
	
	
	
	public GoalDao() { }
	
	
	
	
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	public UserDao getUser() {	return user; }
	
	public void setUser(UserDao userDao) { this.user = userDao;	}
	
	
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
	

	public List<DeadlineDao> getDeadline() { return this.deadline; }
	
	public void setDeadline(List<DeadlineDao> param) { this.deadline = param; }
	
	
	
	
}


