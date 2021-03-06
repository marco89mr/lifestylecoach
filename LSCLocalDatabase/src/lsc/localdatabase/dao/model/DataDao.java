package lsc.localdatabase.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="\"data\"")
@NamedQuery(name = "Data.findAll", query = "SELECT d FROM DataDao d")
public class DataDao extends BaseDao {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_data")
	@TableGenerator(name="sqlite_data", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="data")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"record_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private RecordDao record;
	
	@Column(name = "\"name\"")
	private String name;
	
	@Column(name = "\"value\"")
	private String value;
	
	
	
	
	
	public DataDao() { }
	
	
	
	
	
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	public RecordDao getRecord() {	return record; }
	
	public void setRecord(RecordDao record) { this.record = record;	}
	
	
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
	
	
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	
	
	
	
}

