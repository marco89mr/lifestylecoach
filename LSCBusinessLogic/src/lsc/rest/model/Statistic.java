package lsc.rest.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lsc.rest.model.Goal.Interval;


@XmlRootElement(name="statistic")
public class Statistic extends Base {

	
	
	
	
	
	private int user_id;
	
	private String record_type;
	
	private String field_name;
	
    private String from_date;
    
    private String to_date;
   
    private Interval on_interval;
    
    private List<StatisticData> datas;
    
    private StatisticData average;
    
    private StatisticData sum;
    
    private StatisticData max;
    
    private StatisticData min;
	
	
	
	
	public Statistic() { }
	
	

	

	@XmlTransient
	@Override
	public int getId() { return 0; }
	@Override
	public void setId(int id) { }
	

	@XmlElement
	public int getUserId() {	return user_id; }
	
	public void setUserId(int user_id) { this.user_id = user_id;	}
	
	
	@XmlElement
	public String getRecordType() { return record_type; }

	public void setRecordType(String record_type) { this.record_type = record_type; }
	
	
	@XmlElement
	public String getFieldName() {	return field_name; }
	
	public void setFieldName(String field_name) { this.field_name = field_name; }
	
	
	@XmlElement
	public String getFromDate() { return from_date; }
	
	public void setFromDate(String from_date) { this.from_date = from_date; }
	
	
	@XmlElement
	public String getToDate() { return to_date; }
	
	public void setToDate(String to_date) { this.to_date = to_date; }
	
	
	@XmlElement
	public Interval getOnInterval() { return on_interval; }
	
	public void setOnInterval(Interval on_interval) { this.on_interval = on_interval; }
	
	/*
	@XmlElement
	public Function getFunction() { return function; }
	
	public void setFunction(Function function) { this.function = function; }
	*/
	
	@XmlElement
	public List<StatisticData> getDatas() { return datas; }
	
	public void setDatas(List<StatisticData> datas) { this.datas = datas; }
	
	
	@XmlElement
	public StatisticData getAverage() { return average; }
	
	public void setAverage(StatisticData average) { this.average = average; }
	
	
	@XmlElement
	public StatisticData getCumulative() { return sum; }
	
	public void setCumulative(StatisticData sum) { this.sum = sum; }
	
	
	@XmlElement
	public StatisticData getMax() { return max; }
	
	public void setMax(StatisticData max) { this.max = max; }
	
	
	@XmlElement
	public StatisticData getMin() { return min; }
	
	public void setMin(StatisticData min) { this.min = min; }

	
	
	
	
	// Accessory methods
	// 

	public Date _getFromDate() { return _parseDate(this.from_date); }
	
	public void _setFromDate(Date date) { this.from_date = _formatDate(date); }
	
	
	public Date _getToDate() { return _parseDate(this.to_date); }
	
	public void _setToDate(Date date) { this.to_date = _formatDate(date); }
	
	
	
	
	
	
	
	
	
	
	

    
	public StatisticData _sum() {
		StatisticData sum = new StatisticData();
		sum._initValues(0.0);
		for(StatisticData d : datas)
			for(Goal.Function f : Goal.Function.values())
				for(Goal.Reference r : Goal.Reference.values())
					for(Goal.Perc p : Goal.Perc.values()) {
						double now = d.getValues().get(f).get(r).get(p);
						double since = sum.getValues().get(f).get(r).get(p);
						sum.getValues().get(f).get(r).put(p, since + now );
					}
		return sum;
	}
    
	public StatisticData _average() {
		StatisticData sum = _sum();
		StatisticData avg = new StatisticData();
		avg._initValues(0.0);
		for(Goal.Function f : Goal.Function.values())
			for(Goal.Reference r : Goal.Reference.values())
				for(Goal.Perc p : Goal.Perc.values()) {
					double s = sum.getValues().get(f).get(r).get(p);
					avg.getValues().get(f).get(r).put(p, s / datas.size() );
				}
		return avg;
	}
    
	public StatisticData _max() {
		StatisticData max = new StatisticData();
		max._initValues(null);
		for(StatisticData d : datas)
			for(Goal.Function f : Goal.Function.values())
				for(Goal.Reference r : Goal.Reference.values())
					for(Goal.Perc p : Goal.Perc.values()) {
						Double now = d.getValues().get(f).get(r).get(p);
						Double since = max.getValues().get(f).get(r).get(p);
						if( since==null || now > since)
							max.getValues().get(f).get(r).put(p, now );
					}
		return max;
	}
    
	public StatisticData _min() {
		StatisticData min = new StatisticData();
		min._initValues(null);
		for(StatisticData d : datas)
			for(Goal.Function f : Goal.Function.values())
				for(Goal.Reference r : Goal.Reference.values())
					for(Goal.Perc p : Goal.Perc.values()) {
						Double now = d.getValues().get(f).get(r).get(p);
						Double since = min.getValues().get(f).get(r).get(p);
						if( since==null || now < since)
							min.getValues().get(f).get(r).put(p, now );
					}
		return min;
	}




	
	
	
	
	
	
	
	
	
}

