package lsc.localdatabase.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import lsc.localdatabase.dao.model.User;
import lsc.localdatabase.dao.model.UserCollection;
import lsc.localdatabase.rest.model.UserRest;


public class Test {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
	{
		
		
		List<User> l = new ArrayList<User>();
		System.out.println("DEBUG A");
		UserCollection ll = new UserCollection();
		ll.addAll(l);
		System.out.println("DEBUG B");
		
		
		
		
		
		
		
		
		
		
		
		UserRest u1 = ClientFactory.user.getById(1);
		System.out.println( u1.getName() );
		
		u1.setName("marco");
		ClientFactory.user.put(u1);
		
		
		System.out.println( ClientFactory.user.getById(1).getName() );
	}
	
}
