package lsc.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="notifications")
public class NotificationCollection extends BaseCollection<Notification> {

	private static final long serialVersionUID = 1L;
	
	
	
	@XmlElement(name="notification")
	public List<Notification> getList() { return this; }
	
	
	
	
}
