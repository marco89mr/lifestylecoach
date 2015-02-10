package lsc.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="users")
public class UserCollection	extends BaseCollection<User> {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="user")
	public List<User> getList() { return this; }
	
	
	
	
	
}
