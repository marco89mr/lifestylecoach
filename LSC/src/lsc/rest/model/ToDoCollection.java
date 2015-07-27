package lsc.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="todos")
public class ToDoCollection extends BaseCollection<ToDo> {

	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="todo")
	public List<ToDo> getList() { return this; }
	
	
	
	
	
}
