package lsc.localdatabase.dao.dataaccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.model.UserDao;
import lsc.localdatabase.dao.model.UserCollectionDao;


public class UserDataAccess extends BaseDataAccess<UserDao, UserCollectionDao> {
	
	
	public UserDataAccess() {
		this.model_class = UserDao.class;
		this.model_collection_class = UserCollectionDao.class;
	}
	
	
	
	public UserCollectionDao getAll(MultivaluedMap<String,String> param) {
		String where = "";
		if(param.containsKey("user_id")) {
			if(!where.equals("")) where+=" and";
			where+=" u.id = "+param.getFirst("user_id");
		}
		if(param.containsKey("name")) {
			if(!where.equals("")) where+=" and";
			where+=" u.name LIKE \""+param.getFirst("name")+"\"";
		}
		if(param.containsKey("mail")) {
			if(!where.equals("")) where+=" and";
			where+=" u.mail LIKE \""+param.getFirst("mail")+"\"";
		}
		if(param.containsKey("olderthan")) {
			if(!where.equals("")) where+=" and";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    int years = Integer.parseInt( param.getFirst("olderthan") );
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.YEAR, -years);
		    String string_date = sdf.format( cal.getTime() );
			where+=" u.birthdate <= \""+string_date+"\"";
		}
		if(param.containsKey("youngerthan")) {
			if(!where.equals("")) where+=" and";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    int years = Integer.parseInt( param.getFirst("olderthan") );
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.YEAR, -years);
		    String string_date = sdf.format( cal.getTime() );
			where+=" u.birthdate >= \""+string_date+"\"";
		}
		if(param.containsKey("bornbeforedate")) {
			if(!where.equals("")) where+=" and";
			where+=" u.birthdate <= \""+param.getFirst("bornbeforedate")+"\"";
		}
		if(param.containsKey("bornafterdate")) {
			if(!where.equals("")) where+=" and";
			where+=" u.birthdate >= \""+param.getFirst("bornafterdate")+"\"";
		}
		if(!where.equals(""))
			where = " WHERE" + where;
		String personal_query = "SELECT u FROM UserDao u"+where;
		return getAll(new UserCollectionDao(), personal_query);
	}
	
}
