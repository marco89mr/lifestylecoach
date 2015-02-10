package lsc.localdatabase.rest.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import lsc.localdatabase.dao.model.UserDao;
import lsc.localdatabase.dao.model.UserCollectionDao;
import lsc.rest.model.User;


public class TestApp {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
	{
		
		
		List<UserDao> l = new ArrayList<UserDao>();
		System.out.println("DEBUG A");
		UserCollectionDao ll = new UserCollectionDao();
		ll.addAll(l);
		System.out.println("DEBUG B");
		
		
		
		
		
		
		
		
		
		
		
		User u1 = LocalDatabaseClient.user.getById(1);
		System.out.println( u1.getName() );
		
		u1.setName("marco");
		LocalDatabaseClient.user.put(u1);
		
		
		System.out.println( LocalDatabaseClient.user.getById(1).getName() );
	}
	
}
