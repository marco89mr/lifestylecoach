package lsc.localdatabase.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="todos")
public class ToDoList implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<ToDo> list;	
	
	public ToDoList() { }
	
	public ToDoList(List<ToDo> list) {
		this.list = list;
	}

	@XmlElement(name="todo")
	public List<ToDo> getList() { return this.list; }
	public void setList(List<ToDo> param) { this.list = param; }
		
}
