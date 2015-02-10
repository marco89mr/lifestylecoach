package lsc.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="datas")
public class DataCollection extends BaseCollection<Data> {
	
	private static final long serialVersionUID = 1L;
	
	
	@XmlElement(name="data")
	public List<Data> getList() { return this; }	
	
	
	
	
	
}
