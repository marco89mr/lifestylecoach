package lsc.localdatabase.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="users")
public class UserList extends TableEntityList implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<User> list;	
	
	public UserList() { }
	
	public UserList(List<User> list) {
		this.list = list;
	}
	
	@XmlElement(name="user")
	public List<User> getList() { return this.list; }
	public void setList(List<User> param) { this.list = param; }
		
}
