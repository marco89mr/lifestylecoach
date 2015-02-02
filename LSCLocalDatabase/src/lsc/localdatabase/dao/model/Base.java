package lsc.localdatabase.dao.model;

import java.io.Serializable;



public abstract class Base implements Serializable {
	
	protected static final long serialVersionUID = 1L;
	
	
	public int id = 0;
	public abstract int getId();
	public abstract void setId(int id);
	
}

