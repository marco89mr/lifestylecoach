package lsc.localdatabase.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="records")
public class RecordList implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Record> list;	
	
	public RecordList() { }
	
	public RecordList(List<Record> list) {
		this.list = list;
	}

	@XmlElement(name="record")
	public List<Record> getList() { return this.list; }
	public void setList(List<Record> param) { this.list = param; }
		
}
