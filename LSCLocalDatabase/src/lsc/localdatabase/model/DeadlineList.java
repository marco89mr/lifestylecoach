package lsc.localdatabase.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="deadlines")
public class DeadlineList implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Deadline> list;	
	
	public DeadlineList() { }
	
	public DeadlineList(List<Deadline> list) {
		this.list = list;
	}

	@XmlElement(name="deadline")
	public List<Deadline> getList() { return this.list; }
	public void setList(List<Deadline> param) { this.list = param; }
		
}