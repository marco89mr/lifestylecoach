package lsc.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="records")
public class RecordCollection	extends BaseCollection<Record> {

	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="record")
	public List<Record> getList() { return this; }
	 
	
	
	
}
