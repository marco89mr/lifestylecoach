package lsc.rest.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lsc.rest.model.Goal.Function;
import lsc.rest.model.Goal.Perc;
import lsc.rest.model.Goal.Reference;


@XmlRootElement(name="aggregatedData")
public class StatisticData extends Base {

	private String from_date;
	
	private String to_date;
	
	private Map<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Double> > > values;
	/*
	private float average_target_abs;
	private float average_target_perc;
	private float average_increment_abs;
	private float average_increment_perc;
	
	private float sum_target_abs;
	private float sum_target_perc;
	private float sum_increment_abs;
	private float sum_increment_perc;
	
	private float max_target_abs;
	private float max_target_perc;
	private float max_increment_abs;
	private float max_increment_perc;
	
	private float min_target_abs;
	private float min_target_perc;
	private float min_increment_abs;
	private float min_increment_perc;
	
	private float last_target_abs;
	private float last_target_perc;
	private float last_increment_abs;
	private float last_increment_perc;
	
	private float first_target_abs;
	private float first_target_perc;
	private float first_increment_abs;
	private float first_increment_perc;
	*/
	
	
	
	public StatisticData() { }
	
	
	

	@XmlTransient
	@Override
	public int getId() { return 0; }
	@Override
	public void setId(int id) { }
	

	@XmlElement
	public String getFromDate() { return this.from_date; }
	
	public void setFromDate(String from_date) { this.from_date = from_date; }


	@XmlElement
	public String getToDate() { return this.to_date; }
	
	public void setToDate(String to_date) { this.to_date = to_date; }
	

	@XmlElement
	public Map<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Double> > > getValues() {
		return values;
	}
	public void setValues(Map<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Double> > > values) {
		this.values = values;
	}
	
	/*
	@XmlElement
	public float getAverageTargetAbs() { return this.average_target_abs; }
	public void setAverageTargetAbs(float value) { this.average_target_abs = value; }
	@XmlElement
	public float getAverageTargetPerc() { return this.average_target_perc; }
	public void setAverageTargetPerc(float value) { this.average_target_perc = value; }
	@XmlElement
	public float getAverageIncrementAbs() { return this.average_increment_abs; }
	public void setAverageIncrementAbs(float value) { this.average_increment_abs = value; }
	@XmlElement
	public float getAverageIncrementPerc() { return this.average_increment_perc; }
	public void setAverageIncrementPerc(float value) { this.average_increment_perc = value; }

	@XmlElement
	public float getSumTargetAbs() { return this.sum_target_abs; }
	public void setSumTargetAbs(float value) { this.sum_target_abs = value; }
	@XmlElement
	public float getSumTargetPerc() { return this.sum_target_perc; }
	public void setSumTargetPerc(float value) { this.sum_target_perc = value; }
	@XmlElement
	public float getSumIncrementAbs() { return this.sum_increment_abs; }
	public void setSumIncrementAbs(float value) { this.sum_increment_abs = value; }
	@XmlElement
	public float getSumIncrementPerc() { return this.sum_increment_perc; }
	public void setSumIncrementPerc(float value) { this.sum_increment_perc = value; }

	@XmlElement
	public float getMaxTargetAbs() { return this.max_target_abs; }
	public void setMaxTargetAbs(float value) { this.max_target_abs = value; }
	@XmlElement
	public float getMaxTargetPerc() { return this.max_target_perc; }
	public void setMaxTargetPerc(float value) { this.max_target_perc = value; }
	@XmlElement
	public float getMaxIncrementAbs() { return this.max_increment_abs; }
	public void setMaxIncrementAbs(float value) { this.max_increment_abs = value; }
	@XmlElement
	public float getMaxIncrementPerc() { return this.max_increment_perc; }
	public void setMaxIncrementPerc(float value) { this.max_increment_perc = value; }

	@XmlElement
	public float getMinTargetAbs() { return this.min_target_abs; }
	public void setMinTargetAbs(float value) { this.min_target_abs = value; }
	@XmlElement
	public float getMinTargetPerc() { return this.min_target_perc; }
	public void setMinTargetPerc(float value) { this.min_target_perc = value; }
	@XmlElement
	public float getMinIncrementAbs() { return this.min_increment_abs; }
	public void setMinIncrementAbs(float value) { this.min_increment_abs = value; }
	@XmlElement
	public float getMinIncrementPerc() { return this.min_increment_perc; }
	public void setMinIncrementPerc(float value) { this.min_increment_perc = value; }

	@XmlElement
	public float getLastTargetAbs() { return this.last_target_abs; }
	public void setLastTargetAbs(float value) { this.last_target_abs = value; }
	@XmlElement
	public float getLastTargetPerc() { return this.last_target_perc; }
	public void setLastTargetPerc(float value) { this.last_target_perc = value; }
	@XmlElement
	public float getLastIncrementAbs() { return this.last_increment_abs; }
	public void setLastIncrementAbs(float value) { this.last_increment_abs = value; }
	@XmlElement
	public float getLastIncrementPerc() { return this.last_increment_perc; }
	public void setLastIncrementPerc(float value) { this.last_increment_perc = value; }

	@XmlElement
	public float getFirstTargetAbs() { return this.first_target_abs; }
	public void setFirstTargetAbs(float value) { this.first_target_abs = value; }
	@XmlElement
	public float getFirstTargetPerc() { return this.first_target_perc; }
	public void setFirstTargetPerc(float value) { this.first_target_perc = value; }
	@XmlElement
	public float getFirstIncrementAbs() { return this.first_increment_abs; }
	public void setFirstIncrementAbs(float value) { this.first_increment_abs = value; }
	@XmlElement
	public float getFirstIncrementPerc() { return this.first_increment_perc; }
	public void setFirstIncrementPerc(float value) { this.first_increment_perc = value; }
	*/
	
	
	// Accessory methods
	// 
	
	public Date _getFromDate() { return _parseDate(this.from_date); }
	
	public void _setFromDate(Date date) { this.from_date = _formatDate(date); }
	
	
	public Date _getToDate() { return _parseDate(this.to_date); }
	
	public void _setToDate(Date date) { this.to_date = _formatDate(date); }


	public void _initValues(Double x) {
		values =  new HashMap<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Double>>>();
		for(Function f : Function.values()){
			
			values.put(f, new HashMap<Goal.Reference, Map<Goal.Perc, Double>>() );
			for(Reference r : Reference.values()){
				
				values.get(f).put(r, new HashMap<Goal.Perc, Double>());
				for(Perc p : Perc.values()){
					
					values.get(f).get(r).put(p, x);
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}

