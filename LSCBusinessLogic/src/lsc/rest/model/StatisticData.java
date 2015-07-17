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
	
	
	
	
	
	
	// Accessory methods
	// 
	
	public Date _getFromDate() { return _parseDate(this.from_date); }
	
	public void _setFromDate(Date date) { this.from_date = _formatDate(date); }
	
	
	public Date _getToDate() { return _parseDate(this.to_date); }
	
	public void _setToDate(Date date) { this.to_date = _formatDate(date); }
	
	
	@XmlTransient
	public Map<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Float> > > getValues() {
		Map<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Float> > > values = new HashMap<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Float> > >();
		for(Function f : Function.values()){
			values.put(f, new HashMap<Goal.Reference, Map<Goal.Perc, Float>>() );
			for(Reference r : Reference.values()){
				values.get(f).put(r, new HashMap<Goal.Perc, Float>());
				for(Perc p : Perc.values()){
					values.get(f).get(r).put(p, Float.valueOf(0));
				}
			}
		}
		values.get(Function.average).get(Reference.target).put(Perc.abs, average_target_abs);
		values.get(Function.average).get(Reference.target).put(Perc.perc, average_target_perc);
		values.get(Function.average).get(Reference.increment).put(Perc.abs, average_increment_abs);
		values.get(Function.average).get(Reference.increment).put(Perc.perc, average_increment_perc);
		
		values.get(Function.sum).get(Reference.target).put(Perc.abs, sum_target_abs);
		values.get(Function.sum).get(Reference.target).put(Perc.perc, sum_target_perc);
		values.get(Function.sum).get(Reference.increment).put(Perc.abs, sum_increment_abs);
		values.get(Function.sum).get(Reference.increment).put(Perc.perc, sum_increment_perc);
		
		values.get(Function.max).get(Reference.target).put(Perc.abs, max_target_abs);
		values.get(Function.max).get(Reference.target).put(Perc.perc, max_target_perc);
		values.get(Function.max).get(Reference.increment).put(Perc.abs, max_increment_abs);
		values.get(Function.max).get(Reference.increment).put(Perc.perc, max_increment_perc);
		
		values.get(Function.min).get(Reference.target).put(Perc.abs, min_target_abs);
		values.get(Function.min).get(Reference.target).put(Perc.perc, min_target_perc);
		values.get(Function.min).get(Reference.increment).put(Perc.abs, min_increment_abs);
		values.get(Function.min).get(Reference.increment).put(Perc.perc, min_increment_perc);
		
		values.get(Function.last).get(Reference.target).put(Perc.abs, last_target_abs);
		values.get(Function.last).get(Reference.target).put(Perc.perc, last_target_perc);
		values.get(Function.last).get(Reference.increment).put(Perc.abs, last_increment_abs);
		values.get(Function.last).get(Reference.increment).put(Perc.perc, last_increment_perc);
		
		values.get(Function.first).get(Reference.target).put(Perc.abs, first_target_abs);
		values.get(Function.first).get(Reference.target).put(Perc.perc, first_target_perc);
		values.get(Function.first).get(Reference.increment).put(Perc.abs, first_increment_abs);
		values.get(Function.first).get(Reference.increment).put(Perc.perc, first_increment_perc);
		
		return values;
	}
	
	
	public void setValues(Map<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Float> > > values) {
		for(Function f : values.keySet()){
			for(Reference r : values.get(f).keySet()){
				for(Perc p : values.get(f).get(r).keySet()){
					
					if(f==Function.average && r==Reference.target && p==Perc.abs)
					values.get(Function.average).get(Reference.target).put(Perc.abs, average_target_abs);
					if(f==Function.average && r==Reference.target && p==Perc.perc)
					values.get(Function.average).get(Reference.target).put(Perc.perc, average_target_perc);
					if(f==Function.average && r==Reference.increment && p==Perc.abs)
					values.get(Function.average).get(Reference.increment).put(Perc.abs, average_increment_abs);
					if(f==Function.average && r==Reference.increment && p==Perc.abs)
					values.get(Function.average).get(Reference.increment).put(Perc.perc, average_increment_perc);
					if(f==Function.average && r==Reference.target && p==Perc.abs)

					if(f==Function.sum && r==Reference.target && p==Perc.abs)
					values.get(Function.sum).get(Reference.target).put(Perc.abs, sum_target_abs);
					if(f==Function.sum && r==Reference.target && p==Perc.perc)
					values.get(Function.sum).get(Reference.target).put(Perc.perc, sum_target_perc);
					if(f==Function.sum && r==Reference.increment && p==Perc.abs)
					values.get(Function.sum).get(Reference.increment).put(Perc.abs, sum_increment_abs);
					if(f==Function.sum && r==Reference.increment && p==Perc.perc)
					values.get(Function.sum).get(Reference.increment).put(Perc.perc, sum_increment_perc);

					if(f==Function.max && r==Reference.target && p==Perc.abs)
					values.get(Function.max).get(Reference.target).put(Perc.abs, max_target_abs);
					if(f==Function.max && r==Reference.target && p==Perc.perc)
					values.get(Function.max).get(Reference.target).put(Perc.perc, max_target_perc);
					if(f==Function.max && r==Reference.increment && p==Perc.abs)
					values.get(Function.max).get(Reference.increment).put(Perc.abs, max_increment_abs);
					if(f==Function.max && r==Reference.increment && p==Perc.perc)
					values.get(Function.max).get(Reference.increment).put(Perc.perc, max_increment_perc);

					if(f==Function.min && r==Reference.target && p==Perc.abs)
					values.get(Function.min).get(Reference.target).put(Perc.abs, min_target_abs);
					if(f==Function.min && r==Reference.target && p==Perc.perc)
					values.get(Function.min).get(Reference.target).put(Perc.perc, min_target_perc);
					if(f==Function.min && r==Reference.increment && p==Perc.abs)
					values.get(Function.min).get(Reference.increment).put(Perc.abs, min_increment_abs);
					if(f==Function.min && r==Reference.increment && p==Perc.perc)
					values.get(Function.min).get(Reference.increment).put(Perc.perc, min_increment_perc);

					if(f==Function.last && r==Reference.target && p==Perc.abs)
					values.get(Function.last).get(Reference.target).put(Perc.abs, last_target_abs);
					if(f==Function.last && r==Reference.target && p==Perc.perc)
					values.get(Function.last).get(Reference.target).put(Perc.perc, last_target_perc);
					if(f==Function.last && r==Reference.increment && p==Perc.abs)
					values.get(Function.last).get(Reference.increment).put(Perc.abs, last_increment_abs);
					if(f==Function.last && r==Reference.increment && p==Perc.perc)
					values.get(Function.last).get(Reference.increment).put(Perc.perc, last_increment_perc);

					if(f==Function.first && r==Reference.target && p==Perc.abs)
					values.get(Function.first).get(Reference.target).put(Perc.abs, first_target_abs);
					if(f==Function.first && r==Reference.target && p==Perc.perc)
					values.get(Function.first).get(Reference.target).put(Perc.perc, first_target_perc);
					if(f==Function.first && r==Reference.increment && p==Perc.abs)
					values.get(Function.first).get(Reference.increment).put(Perc.abs, first_increment_abs);
					if(f==Function.first && r==Reference.increment && p==Perc.perc)
					values.get(Function.first).get(Reference.increment).put(Perc.perc, first_increment_perc);
					
				}
			}
		}
	}
	
	
	
	public void _initValues(Double x) {
		Map<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Float>>> map =  new HashMap<Goal.Function, Map<Goal.Reference, Map<Goal.Perc, Float>>>();
		for(Function f : Function.values()){
			map.put(f, new HashMap<Goal.Reference, Map<Goal.Perc, Float>>() );
			for(Reference r : Reference.values()){
				map.get(f).put(r, new HashMap<Goal.Perc, Float>());
				for(Perc p : Perc.values()){
					map.get(f).get(r).put(p, Float.valueOf(0));
				}
			}
		}
		setValues(map);
	}
	
	
	
	
	
	
	
	
	
	
	
}

