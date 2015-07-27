package lsc.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="datas")
public class DataCollection extends BaseCollection<Data> {
	
	private static final long serialVersionUID = 1L;
	
	
	@XmlElement(name="data")
	public List<Data> getList() { return this; }	
	
	
	

    
	
	

	
	
	
    
	public float _sum() {
		float sum = 0;
		for(Data d : this) {
			sum += Float.parseFloat( d.getValue() );
		}
		//System.out.println("DataCollection._sum: "+sum);
		return sum;
	}
    
	public float _average() {
		float sum = 0;
		for(Data d : this) {
			sum += Float.parseFloat( d.getValue() );
		}
		//System.out.println("DataCollection._average: "+sum / this.size());
		return sum / this.size();
	}
    
	public Data _max() {
		Data max = new Data();
		max.setValue("0");
		for(Data d : this) {
			if( max==null || Float.parseFloat( d.getValue() ) > Float.parseFloat( max.getValue() ) )
				max = d;
		}
		//System.out.println("DataCollection._max: "+max);
		return max;
	}
    
	public Data _min() {
		Data min = new Data();
		min.setValue(String.valueOf(Float.POSITIVE_INFINITY));
		for(Data d : this) {
			if( min==null || Float.parseFloat( d.getValue() ) < Float.parseFloat( min.getValue() ) )
				min = d;
		}
		//System.out.println("DataCollection._min: "+min);
		return min;
	}
	
	
	
	
}
