package lsc.localdatabase.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;



@XmlType(name = "record", propOrder = { "selfLink", "userLink", "type", "date", "datasLink" })
@XmlRootElement(name="record")
public class Record extends EntityModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private User user;
	
	private String type;

    private String date;
	
	private List<Data> data;
	
	private Link user_link;
	
	
	
	
	public Record() { }
	
	
	

	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	@XmlElement
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	@XmlElement
	public String getDate() { return this.date; }
	
	public void setDate(String date) throws ParseException { this.date = date; }
	
	
	@XmlTransient
	public List<Data> getData() { return this.data; }
	
	public void setData(List<Data> param) { this.data = param; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelf_link() { return Link.create("self", this._getUrl() ); }
	
	public void setSelf_link(Link link) {
		System.out.println("DEBUG: selfLink");
		if(link.getRel().equals("user"))
			this.user_link = link;
	}
	
	
	@XmlElement(name="link", required=true)
	public Link getUser_link() { return Link.create("user", this.getUser()._getUrl() ); }
	
	public void setUser_link(Link link) { setSelf_link(link); }
	 
	
	@XmlElement(name="link")
	public Link getDatas_link() { return Link.create("datas", this._getUrl()+"/data"); }
	
	public void setDatas_link(Link link) { setSelf_link(link); }
	
	
	
	// Accessory methods
	// 
	
	public Date _getDate() throws ParseException { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
	    return sdf.parse(this.date);
	}
	
	public void _setDate(Date date) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
	    this.date = sdf.format(date);
	}
	
	public String _getUrl() {
		return _getBaseUrl()+"record/"+this.id;
	}
	
	
	
}

