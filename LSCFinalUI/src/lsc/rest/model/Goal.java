package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "goal", propOrder = { "id", "userId", "recordType", "dataName", "operator", "value", "function", "reference", "perc", "repeat", "days" })
@XmlRootElement(name="goal")
public class Goal extends Base {

	public enum Operator {
		larger, smaller, equals;
	};
	public enum Function {
		average, sum, max, min, last, first;
	};
	public enum Reference {
		target, increment, decrement;
	};
	public enum Perc {
		abs, perc;
	};
	public enum Interval {
		fixed	(0),
		day		(1000 * 60 * 60 * 24),
		week	(1000 * 60 * 60 * 24 * 7),
		month	(1000 * 60 * 60 * 24 * 30),
		year	(1000 * 60 * 60 * 24 * 365);
		
		private long ms_per_interval = 0;
		
		Interval(long ms){
			ms_per_interval = ms;
		}
		
		public long getMs(){
			return ms_per_interval;
		}
	};
	
	
	
	
	
	
	
	
	
	private int id;
	
	private int user_id;
	
	private String record_type;
	
	private String data_name;
	
	private Operator operator;
	
	private String value;
	
	private Function function;
	
	private Reference reference;
	
	private Perc perc;
	
	private Interval repeat;
	
	private String days;
	
	
	
	
	public Goal() { }
	
	
	
	
	@XmlElement(name="id")
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	@XmlElement
	public int getUserId() { return this.user_id; }
	
	public void setUserId(int id) { this.user_id = id; }
	
	
	public String getRecordType() { return this.record_type; }
	
	public void setRecordType(String value) { this.record_type = value; }
	
	
	public String getDataName() { return this.data_name; }
	
	public void setDataName(String value) { this.data_name = value; }
	
	
	public Operator getOperator() { return this.operator; }
	
	public void setOperator(Operator operator) { this.operator = operator; }
	
	
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	public Function getFunction() { return this.function; }
	
	public void setFunction(Function x) { this.function = x; }
	
	
	public Reference getReference() { return this.reference; }
	
	public void setReference(Reference x) { this.reference = x; }
	
	
	public Perc getPerc() { return this.perc; }
	
	public void setPerc(Perc x) { this.perc = x; }
	
	
	public Interval getRepeat() { return this.repeat; }
	
	public void setRepeat(Interval x) { this.repeat = x; }
	
	
	public String getDays() { return this.days; }
	
	public void setDays(String x) { this.days = x; }
	
	
	
	
	
	
}


