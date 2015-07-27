package lsc.rest.model;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseCollection<MR extends Base> extends ArrayList<MR> {
	
	private static final long serialVersionUID = 1L;
	
	public abstract List<MR> getList();
	
	
	
	public MR _get(int id) {
		for(MR m : getList()) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	
	
}
