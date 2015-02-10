package lsc.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="goals")
public class GoalCollection extends BaseCollection<Goal> {

	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="goal")
	public List<Goal> getList() { return this; }
	
	
	
	
}
