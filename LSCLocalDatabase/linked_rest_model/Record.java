package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;



/**
 * The persistent class for the "record" database table.
 * 
 */
@XmlType(name = "record", propOrder = { "selfLink", "userLink", "type", "date", "datasLink" })
@XmlRootElement(name="record")
public class Record extends Base<Record.Relation> {

	public enum Relation implements RelationInterface {
		all, self, user, datas;
	};
	
	/*
	public static LinkRest<Link> generateAllLink() {
		return new LinkRest<Link>( RecordRest.Relation.all, getBaseUrl()+path );
	}
	
	public static LinkRest<Link> generateSelfLink(int id) {
		return new LinkRest<Link>( RecordRest.Relation.self, getBaseUrl()+path+"/"+id );
	}
	
	public static LinkRest<Link> generateUserLink(int user_id) {
		return new LinkRest<Link>( RecordRest.Relation.user, getBaseUrl()+UserRest.path+"/"+user_id );
	}
	
	public static LinkRest<Link> generateDatasLink(int record_id) {
		return new LinkRest<Link>( RecordRest.Relation.datas, getBaseUrl()+path+"/"+record_id+"/record" );
	}
	*/
	
	
	
	
	
	private String type;

	private String date;
	
	
	
	
	public Record() { }
	
	
	

	@XmlElement
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	@XmlElement
	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link<Record.Relation> getSelfLink() { return getLink(Record.Relation.self); }
	
	public void setSelfLink(Link<Record.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link", required=true)
	public Link<Record.Relation> getUserLink() { return getLink(Record.Relation.user); }
	
	public void setUserLink(Link<Record.Relation> link) { putLink(link); }
	 
	
	@XmlElement(name="link")
	public Link<Record.Relation> getDatasLink() { return getLink(Record.Relation.datas); }
	
	public void setDatasLink(Link<Record.Relation> link) { putLink(link); }
	
	@XmlTransient
	public Link<Record.Relation> getParentLink() { return getLink(Record.Relation.user); }
	
	public void setParentLink(Link<Record.Relation> link) { putLink(link);	}
	
	
	
	
	
}

