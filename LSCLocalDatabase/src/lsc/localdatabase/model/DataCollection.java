package lsc.localdatabase.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="datas")
public class DataCollection extends BaseCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Data> list;	
	
	public DataCollection() { }
	
	public DataCollection(List<Data> list) {
		this.list = list;
	}
	
	@XmlElement(name="data")
	public List<Data> getList() { return this.list; }
	public void setList(List<Data> param) { this.list = param; }

	
	
	
	
	
	
}
