package lsc.localdatabase.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="goals")
public class GoalCollectionRest extends BaseCollectionRest<GoalRest> {

	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="goal")
	public List<GoalRest> getList() { return this; }
	
	
	
	
}
