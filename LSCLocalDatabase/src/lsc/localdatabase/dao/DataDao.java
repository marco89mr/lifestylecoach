package lsc.localdatabase.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.LifeStyleCoachDao;
import lsc.localdatabase.dao.model.Data;
import lsc.localdatabase.dao.model.DataCollection;


public class DataDao extends BaseDao<Data, DataCollection>{
	
	
	public DataDao() {
		this.model_class = Data.class;
		this.model_collection_class = DataCollection.class;
	}
	
	

	public DataCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		// building query
		String where = " WHERE d.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and d.record.user.id = "+param.getFirst("user_id");
		if(param.containsKey("record_id"))
			where+=" and d.record.id LIKE "+param.getFirst("record_id");
		if(param.containsKey("record_type"))
			where+=" and d.record.type LIKE "+param.getFirst("record_type");
		if(param.containsKey("data_name"))
			where+=" and s.name LIKE "+param.getFirst("data_name");
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		if(param.containsKey("fromdate"))
			where+=" and d.record.date > \""+param.getFirst("fromdate")+"\"";
		if(param.containsKey("todate"))
			where+=" and d.record.date < \""+param.getFirst("todate")+"\"";
		
		System.out.println("--> "+"SELECT d FROM Data d"+where);
		TypedQuery<Data> query = em.createQuery("SELECT d FROM Data d"+where, Data.class);
		// querying
		DataCollection list = new DataCollection();
		list.addAll( query.getResultList() );
		LifeStyleCoachDao.instance.closeConnections(em);
	    return list;
	}
	
}
