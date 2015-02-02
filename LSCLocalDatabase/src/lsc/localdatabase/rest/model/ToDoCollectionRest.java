package lsc.localdatabase.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="todos")
public class ToDoCollectionRest extends BaseCollectionRest<ToDoRest> {

	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="todo")
	public List<ToDoRest> getList() { return this; }
	
	
	
	
	
}
