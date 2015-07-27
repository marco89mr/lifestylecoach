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
		for(Data d : this){
			sum =+ Float.parseFloat( d.getValue() );
		}
		return sum;
	}
    
	public float _average() {
		float sum = 0;
		for(Data d : this){
			sum =+ Float.parseFloat( d.getValue() );
		}
		return sum / this.size();
	}
    
	public Data _max() {
		Data max = null;
		for(Data d : this)
			if( max==null || Float.parseFloat( d.getValue() ) > Float.parseFloat( max.getValue() ) )
				max = d;
		return max;
	}
    
	public Data _min() {
		Data min = null;
		for(Data d : this)
			if( min==null || Float.parseFloat( d.getValue() ) < Float.parseFloat( min.getValue() ) )
				min = d;
		return min;
	}
	
	
	
	
}
