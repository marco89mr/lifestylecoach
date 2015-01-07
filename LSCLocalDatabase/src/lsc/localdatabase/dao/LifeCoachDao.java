package lsc.localdatabase.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lsc.localdatabase.model.User;

public enum LifeCoachDao {
	instance;
	private EntityManagerFactory emf;
	
	private LifeCoachDao() {
		if (emf!=null) {
			emf.close();
		}
		emf = Persistence.createEntityManagerFactory("introsde-jpa");
	}
	
	public EntityManager createEntityManager() {
		try {
			return emf.createEntityManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;    
	}

	public void closeConnections(EntityManager em) {
		em.close();
	}

	public EntityTransaction getTransaction(EntityManager em) {
		return em.getTransaction();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
	// Person related operations could also directly go into the "Person" Model
	// Check Methods in LifeStaus as example
	public static User getPersonById(Long personId) {
		EntityManager em = instance.createEntityManager();
		User p = em.find(User.class, personId);
		instance.closeConnections(em);
		return p;
	}
	
	public static List<User> getAll() {
		EntityManager em = instance.createEntityManager();
	    List<User> list = em.createNamedQuery("Person.findAll", User.class).getResultList();
	    instance.closeConnections(em);
	    return list;
	}
	
	// add other database global access operations

}
