package lsc.localdatabase.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="datas")
public class DataCollectionRest extends BaseCollectionRest<DataRest> {
	
	private static final long serialVersionUID = 1L;
	
	
	@XmlElement(name="data")
	public List<DataRest> getList() { return this; }	
	
	
	
	
	
}
