package lsc.localdatabase.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="notifications")
public class NotificationCollectionRest extends BaseCollectionRest<NotificationRest> {

	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="notification")
	public List<NotificationRest> getList() { return this; }
	
	
	
	
}
