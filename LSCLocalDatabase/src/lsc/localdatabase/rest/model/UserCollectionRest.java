package lsc.localdatabase.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="users")
public class UserCollectionRest	extends BaseCollectionRest<UserRest> {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="user")
	public List<UserRest> getList() { return this; }
	
	
	
	
	
}
