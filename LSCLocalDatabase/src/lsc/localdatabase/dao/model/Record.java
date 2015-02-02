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



/**
 * The persistent class for the "record" database table.
 * 
 */
@Entity
@Table(name="\"record\"")
@NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r")
public class Record extends Base {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_record")
	@TableGenerator(name="sqlite_record", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="record")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"user_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "\"type\"")
	private String type;

    @Column(name = "\"date\"")
	private String date;
	
	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="record",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Data> data;
	
	
	
	
	public Record() { }
	
	
	

	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	public List<Data> getData() { return this.data; }
	
	public void setData(List<Data> param) { this.data = param; }
	
	
	
	
	
	
}

