package lsc.businesslogic.transfer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "data", propOrder = { "name", "value" })
@XmlRootElement(name="data")
public class DataRecord {
	
	private String name;
	
	private String value;
	
	
	
	
	public DataRecord() { }
	
	
	
	
	@XmlElement(name="name")
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
	
	
	@XmlElement(name="value")
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	
	
}

