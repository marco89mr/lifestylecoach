package lsc.businesslogic.transfer;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lsc.localdatabase.model.Base;


@XmlRootElement(name="statistic")
public class Statistic extends Base{
		
	private int user_id;
	
	private String record_type;
	
	private String field_name;

    private String from_date;

    private String to_date;

    private String on_interval;

    private String function;
    
    private List<AggregatedData> datas;

    private String average;

    private String cumulative;

    private String i_max;

    private String i_min;
	
	
	
	
	public Statistic() { }
	
	
	


	@XmlElement
	public int getUserId() {	return user_id; }
	
	public void setUserId(int user_id) { this.user_id = user_id;	}
	
	
	@XmlElement
	public String getRecord_type() { return record_type; }

	public void setRecord_type(String record_type) { this.record_type = record_type; }
	
	
	@XmlElement
	public String getField_name() {	return field_name; }
	
	public void setField_name(String field_name) { this.field_name = field_name; }
	
	
	@XmlElement
	public String getFrom_date() { return from_date; }
	
	public void setFrom_date(String from_date) { this.from_date = from_date; }
	
	
	@XmlElement
	public String getTo_date() { return to_date; }
	
	public void setTo_date(String to_date) { this.to_date = to_date; }
	
	
	@XmlElement
	public String getOn_interval() { return on_interval; }
	
	public void setOn_interval(String on_interval) { this.on_interval = on_interval; }
	
	
	@XmlElement
	public String getFunction() { return function; }
	
	public void setFunction(String function) { this.function = function; }
	
	
	@XmlElement
	public List<AggregatedData> getDatas() { return datas; }
	
	public void setDatas(List<AggregatedData> datas) { this.datas = datas; }
	
	
	@XmlElement
	public String getAverage() { return average; }
	
	public void setAverage(String average) { this.average = average; }
	
	
	@XmlElement
	public String getCumulative() { return cumulative; }
	
	public void setCumulative(String cumulative) { this.cumulative = cumulative; }
	
	
	@XmlElement
	public String getI_max() { return i_max; }
	
	public void setI_max(String i_max) { this.i_max = i_max; }
	
	
	@XmlElement
	public String getI_min() { return i_min; }
	
	public void setI_min(String i_min) { this.i_min = i_min; }



	
	
	// Accessory methods
	// 

	public Date _getFrom_date() { return _parseDate(this.from_date); }
	
	public void _setFrom_date(Date date) { this.from_date = _formatDate(date); }
	
	
	public Date _getTo_date() { return _parseDate(this.to_date); }
	
	public void _setTo_date(Date date) { this.to_date = _formatDate(date); }
	
	
	
}

