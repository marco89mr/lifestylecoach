package lsc.localdatabase.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.LifeStyleCoachDao;
import lsc.localdatabase.dao.model.Notification;
import lsc.localdatabase.dao.model.NotificationCollection;


public class NotificationDao extends BaseDao<Notification, NotificationCollection>  {
	
	
	
	public NotificationDao() {
		this.model_class = Notification.class;
		this.model_collection_class = NotificationCollection.class;
	}
	
	

	public NotificationCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.goal.getAll(filters)");
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		// building query
		String where = " WHERE n.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and n.user.id = "+param.getFirst("user_id");
		if(param.containsKey("record_type"))
			where+=" and n.record.type LIKE "+param.getFirst("record_type");
		if(param.containsKey("data_name"))
			where+=" and n.name LIKE "+param.getFirst("data_name");
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		
		System.out.println("--> "+"SELECT n FROM Goal n"+where);
		TypedQuery<Notification> query = em.createQuery("SELECT n FROM Notification n"+where, Notification.class);
		// querying
		NotificationCollection list = new NotificationCollection();
		list.addAll( query.getResultList() );
		LifeStyleCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	
}
