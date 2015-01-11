package lsc.localdatabase.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import lsc.localdatabase.dao.LifeCoachDao;


public class TableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	

	// Database operations
	// 
	
	public static <T extends TableEntity> T getById(Class<T> c, int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		T instance = em.find(c, id);
		LifeCoachDao.instance.closeConnections(em);
		return instance;
	}

	
	public static <T extends TableEntityList> T getAll(Class<T> c) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
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

