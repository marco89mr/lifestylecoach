package lsc.businesslogic.transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="record")
public class RecordData {
	
	private int id;
	
	private int user_id;
	
	private String type;

    private String date;
	
	private List<DataRecord> data;
	
	
	
	
	public RecordData() { }
	
	
	

	@XmlElement
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlElement
	public int getUserId() {	return user_id; }
	
	public void setUserId(int user_id) { this.user_id = user_id;	}
	
	
	@XmlElement
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	@XmlElement
	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	@XmlElement(name="data")
	public List<DataRecord> getData() { return this.data; }
	
	public void setData(List<DataRecord> param) { this.data = param; }
	
	
	
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
	
	
	
}

