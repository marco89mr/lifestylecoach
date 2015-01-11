package lsc.localdatabase.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="notifications")
public class NotificationList implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Notification> list;	
	
	public NotificationList() { }
	
	public NotificationList(List<Notification> list) {
		this.list = list;
	}

	@XmlElement(name="notification")
	public List<Notification> getList() { return this.list; }
	public void setList(List<Notification> param) { this.list = param; }
		
}