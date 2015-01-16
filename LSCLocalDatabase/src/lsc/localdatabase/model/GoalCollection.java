package lsc.localdatabase.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="goals")
public class GoalCollection extends BaseCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Goal> list;	
	
	public GoalCollection() { }
	
	public GoalCollection(List<Goal> list) {
		this.list = list;
	}

	@XmlElement(name="goal")
	public List<Goal> getList() { return this.list; }
	public void setList(List<Goal> param) { this.list = param; }
	

	
	
	
	
	
	
}
