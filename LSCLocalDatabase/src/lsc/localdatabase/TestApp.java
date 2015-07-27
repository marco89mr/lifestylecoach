package lsc.localdatabase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.dataaccess.LocalDatabaseDataAccess;
import lsc.localdatabase.dao.model.DataCollectionDao;
import lsc.localdatabase.dao.model.UserDao;
import lsc.localdatabase.dao.model.UserCollectionDao;
import lsc.localdatabase.parser.LocalDatabaseParser;
import lsc.rest.model.DataCollection;
import lsc.rest.model.User;
import lsc.utils.MultivaluedMapImpl;


public class TestApp {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
	{
		
		/*
		List<UserDao> l = new ArrayList<UserDao>();
		System.out.println("DEBUG A");
		UserCollectionDao ll = new UserCollectionDao();
		ll.addAll(l);
		System.out.println("DEBUG B");
		*/
		
		
		
		
		
		/*
		User u1 = LocalDatabaseClient.user.getById(1);
		System.out.println( u1.getName() );
		
		u1.setName("marco");
		LocalDatabaseClient.user.put(u1);
		
		
		System.out.println( LocalDatabaseClient.user.getById(1).getName() );
		*/
		
		
		
		
		MultivaluedMap<String,String> param = new MultivaluedMapImpl();
		
		DataCollectionDao dao_collection = LocalDatabaseDataAccess.data.getAll(param);
		
		DataCollection model_collection = LocalDatabaseParser.data.toRest( dao_collection );
		
		
		
		
	}
	
}
