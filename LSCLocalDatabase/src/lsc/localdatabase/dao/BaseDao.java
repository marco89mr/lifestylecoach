package lsc.localdatabase.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;

import lsc.localdatabase.model.Base;
import lsc.localdatabase.model.BaseCollection;


public class BaseDao {
	
	
	public static <T extends Base> T getById(Class<T> c, int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		T instance = em.find(c, id);
		LifeCoachDao.instance.closeConnections(em);
		return instance;
	}

	
	public static <T extends BaseCollection> T getAll(Class<T> c) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
		//System.out.println("--> model.user.getAll()");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<T> list = em.createNamedQuery(c.getName()+".findAll", c).getResultList();
		T listObj = c.newInstance();
		Method method = c.getDeclaredMethod("setList");
		method.invoke( listObj, list );
	    LifeCoachDao.instance.closeConnections(em);
	    return listObj;
	}
}
