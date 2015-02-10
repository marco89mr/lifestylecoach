package lsc.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="deadlines")
public class DeadlineCollection extends BaseCollection<Deadline> {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="deadline")
	public List<Deadline> getList() { return this; }
	
	
	
}
