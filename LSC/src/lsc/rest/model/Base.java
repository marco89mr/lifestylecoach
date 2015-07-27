package lsc.rest.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class Base {
	
	public abstract int getId();
	public abstract void setId(int id);
	
	
	


	// Accessory methods
	// 
	
	public static Date _parseDate(String date) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try {
			return sdf.parse(date);
		} catch (ParseException e) { e.printStackTrace(); }
	    return null;
	}
	
	public static String _formatDate(Date date) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(date);
	}
	
	public static String _uniformDate(String date) {
		Date d = _parseDate(date);
		return _formatDate(d);
	}
	
	public void _print() { 
		try {
			System.out.println( new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this) );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	
	
}

