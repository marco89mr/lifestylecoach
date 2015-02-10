package lsc.rest.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class Base {
	
	public abstract int getId();
	public abstract void setId(int id);
	
	
	
	

	// Accessory methods
	// 
	
	public static Date _parseDate(String date) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
	    try {
			return sdf.parse(date);
		} catch (ParseException e) { e.printStackTrace(); }
	    return null;
	}
	
	public static String _formatDate(Date date) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
	    return sdf.format(date);
	}
	
	
	
}

