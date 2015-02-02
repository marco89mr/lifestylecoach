package lsc.localdatabase.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="deadlines")
public class DeadlineCollectionRest extends BaseCollectionRest<DeadlineRest> {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="deadline")
	public List<DeadlineRest> getList() { return this; }
	
	
	
}
