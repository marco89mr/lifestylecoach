package lsc.localdatabase.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.LifeStyleCoachDao;
import lsc.localdatabase.dao.model.Record;
import lsc.localdatabase.dao.model.RecordCollection;


public class RecordDao extends BaseDao<Record, RecordCollection> {
	
	
	public RecordDao() {
		this.model_class = Record.class;
		this.model_collection_class = RecordCollection.class;
	}
	
	
	
	public RecordCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		// building query
		String where = " WHERE r.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and r.user.id = "+param.getFirst("user_id")+"";
		if(param.containsKey("type"))
			where+=" and r.type LIKE \""+param.getFirst("type")+"\"";
		if(param.containsKey("last"))
			where+=" and r.mail LIKE \""+param.getFirst("last")+"\"";
		if(param.containsKey("fromdate"))
			where+=" and r.date > \""+param.getFirst("fromdate")+"\"";
		if(param.containsKey("todate"))
			where+=" and r.date < \""+param.getFirst("todate")+"\"";
		
		System.out.println("--> "+"SELECT r FROM Record r"+where);
		TypedQuery<Record> query = em.createQuery("SELECT r FROM Record r"+where, Record.class);
		// querying
		RecordCollection list = new RecordCollection();
		list.addAll( query.getResultList() );
		LifeStyleCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	
}
