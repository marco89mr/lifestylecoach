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
@Table(name="\"deadline\"")
@NamedQuery(name = "Deadline.findAll", query = "SELECT d FROM DeadlineDao d")
public class DeadlineDao extends BaseDao {
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
	private GoalDao goal;
	
	@Column(name = "\"start_date\"")
	private String start_date;
	
	@Column(name = "\"end_date\"")
	private String end_date;
	
	@Column(name = "\"status\"")
	private String status;
	
	@Column(name = "\"actual_value\"")
	private String actual_value;
	
	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="deadline",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<NotificationDao> notification;
	
	
	
	
	
	public DeadlineDao() { }
	
	
	

	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	public GoalDao getGoal() { return goal; }

	public void setGoal(GoalDao goalDao) { this.goal = goalDao; }
	
	
	public String getStart_date() { return this.start_date; }
	
	public void setStart_date(String value) { this.start_date = value; }
	
	
	public String getEnd_date() { return this.end_date; }
	
	public void setEnd_date(String value) { this.end_date = value; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	public String getActualValue() { return this.actual_value; }
	
	public void setActualValue(String x) { this.actual_value = x; }
	

	public List<NotificationDao> getNotification() { return this.notification; }
	
	public void setNotification(List<NotificationDao> param) { this.notification = param; }
	
	
	
	
}


