package lsc.localdatabase.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="records")
public class RecordCollectionRest	extends BaseCollectionRest<RecordRest> {

	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="record")
	public List<RecordRest> getList() { return this; }
	 
	
	
	
}
