package lsc.localdatabase.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.LifeStyleCoachDao;
import lsc.localdatabase.dao.model.User;
import lsc.localdatabase.dao.model.UserCollection;


public class UserDao extends BaseDao<User, UserCollection> {
	
	
	public UserDao() {
		this.model_class = User.class;
		this.model_collection_class = UserCollection.class;
	}
	
	
	
	public UserCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.user.getAll(filters)");
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		// building query
		String where = " WHERE u.id > 0";
		
		if(param.containsKey("name"))
			where+=" and u.name LIKE \""+param.getFirst("name")+"\"";
		if(param.containsKey("mail"))
			where+=" and u.mail LIKE \""+param.getFirst("mail")+"\"";
		if(param.containsKey("olderthan")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    int years = Integer.parseInt( param.getFirst("olderthan") );
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.YEAR, -years);
		    String string_date = sdf.format( cal.getTime() );
			where+=" and u.birthdate < \""+string_date+"\"";
		}
		if(param.containsKey("youngerthan")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    int years = Integer.parseInt( param.getFirst("olderthan") );
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.YEAR, -years);
		    String string_date = sdf.format( cal.getTime() );
			where+=" and u.birthdate > \""+string_date+"\"";
		}
		if(param.containsKey("bornbeforedate"))
			where+=" and u.birthdate < \""+param.getFirst("bornbeforedate")+"\"";
		if(param.containsKey("bornafterdate"))
			where+=" and u.birthdate > \""+param.getFirst("bornafterdate")+"\"";
		
		System.out.println("--> "+"SELECT u FROM User u"+where);
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u"+where, User.class);
		// querying
		UserCollection list = new UserCollection();
		list.addAll( query.getResultList() );
		LifeStyleCoachDao.instance.closeConnections(em);
	    return list;
	}
	
}
