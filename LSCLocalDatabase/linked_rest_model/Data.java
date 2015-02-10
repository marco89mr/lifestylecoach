package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "data", propOrder = { "selfLink", "name", "value", "recordLink" })
@XmlRootElement(name="data")
public class Data extends Base<Data.Relation> {

	public enum Relation implements RelationInterface {
		all, self, record;
	}
	
	/*
	public static LinkRest<Link> generateAllLink() {
		return new LinkRest<Link>( DataRest.Link.all, getBaseUrl()+path );
	}
	
	public static LinkRest<Link> generateSelfLink(int id) {
		return new LinkRest<Link>( DataRest.Link.self, getBaseUrl()+path+"/"+id );
	}
	
	public static LinkRest<Link> generateRecordLink(int record_id) {
		return new LinkRest<Link>( DataRest.Link.record, getBaseUrl()+RecordRest.path+"/"+record_id );
	}
	*/
	
	
	
	
	
	
	
	
	
	private String name;
	
	private String value;
	
	
	
	
	
	
	public Data() { }
	
	
	
	
	
	
	@XmlElement(name="name")
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
	
	
	@XmlElement(name="value")
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link<Data.Relation> getSelfLink() {	return getLink(Data.Relation.self); }
	
	public void setSelfLink(Link<Data.Relation> link) { putLink(link);	}
	
	
	@XmlElement(name="link")
	public Link<Data.Relation> getRecordLink() { return getLink(Data.Relation.record); }
	
	public void setRecordLink(Link<Data.Relation> link) { putLink(link); }
	
	@XmlTransient
	public Link<Data.Relation> getParentLink() { return getLink(Data.Relation.record); }
	
	public void setParentLink(Link<Data.Relation> link) { putLink(link);	}
	
	
	
	
	
	
}

