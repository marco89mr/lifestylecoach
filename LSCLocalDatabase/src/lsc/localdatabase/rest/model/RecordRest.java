package lsc.localdatabase.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The persistent class for the "record" database table.
 * 
 */
@XmlType(name = "record", propOrder = { "selfLink", "userLink", "type", "date", "datasLink" })
@XmlRootElement(name="record")
public class RecordRest extends BaseRest<RecordRest.Relation> {

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
	
	
	
	
	public RecordRest() { }
	
	
	

	@XmlElement
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	@XmlElement
	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public LinkRest<RecordRest.Relation> getSelfLink() { return getLink(RecordRest.Relation.self); }
	
	public void setSelfLink(LinkRest<RecordRest.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link", required=true)
	public LinkRest<RecordRest.Relation> getUserLink() { return getLink(RecordRest.Relation.user); }
	
	public void setUserLink(LinkRest<RecordRest.Relation> link) { putLink(link); }
	 
	
	@XmlElement(name="link")
	public LinkRest<RecordRest.Relation> getDatasLink() { return getLink(RecordRest.Relation.datas); }
	
	public void setDatasLink(LinkRest<RecordRest.Relation> link) { putLink(link); }
	
	
	
	
	
}

