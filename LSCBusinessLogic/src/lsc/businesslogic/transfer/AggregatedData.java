package lsc.businesslogic.transfer;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lsc.localdatabase.model.Base;
import lsc.localdatabase.model.Data;
import lsc.localdatabase.model.DataCollection;
import lsc.localdatabase.utils.BaseUtils;


@XmlRootElement(name="aggregatedData")
public class AggregatedData extends Base {
	
	private String from_date;
	
	private String to_date;
	
	private String value;
	
	
	
	
	public AggregatedData() { }
	
	
	

	@XmlElement
	public String getFrom_date() { return this.from_date; }
	
	public void setFrom_date(String from_date) { this.from_date = from_date; }


	@XmlElement
	public String getTo_date() { return this.to_date; }
	
	public void setTo_date(String to_date) { this.to_date = to_date; }
	
	
	@XmlElement
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	
	// Accessory methods
	// 
	
	public Date _getFrom_date() { return _parseDate(this.from_date); }
	
	public void _setFrom_date(Date date) { this.from_date = _formatDate(date); }
	
	
	public Date _getTo_date() { return _parseDate(this.to_date); }
	
	public void _setTo_date(Date date) { this.to_date = _formatDate(date); }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

    
	public static float sum(List<AggregatedData> datas) {
		float sum = 0;
		for(AggregatedData d : datas){
			sum =+ Float.parseFloat( d.getValue() );
		}
		return sum;
	}
    
	public static float average(List<AggregatedData> datas) {
		float sum = 0;
		for(AggregatedData d : datas){
			sum =+ Float.parseFloat( d.getValue() );
		}
		return sum / datas.size();
	}
    
	public static AggregatedData max(List<AggregatedData> datas) {
		AggregatedData max = null;
		for(AggregatedData d : datas)
			if( max==null || Float.parseFloat( d.getValue() ) > Float.parseFloat( max.getValue() ) )
				max = d;
		return max;
	}
    
	public static AggregatedData min(List<AggregatedData> datas) {
		AggregatedData min = null;
		for(AggregatedData d : datas)
			if( min==null || Float.parseFloat( d.getValue() ) < Float.parseFloat( min.getValue() ) )
				min = d;
		return min;
	}
	
	
	
	
	
	
}

