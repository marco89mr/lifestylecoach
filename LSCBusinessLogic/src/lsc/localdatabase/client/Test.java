package lsc.localdatabase.client;

import java.io.IOException;
import java.net.URISyntaxException;
import lsc.localdatabase.rest.model.UserRest;


public class Test {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
	{
		
		
		
		
		
		
		UserRest u1 = ClientFactory.user.getById(1);
		System.out.println( u1.getName() );
		
		u1.setName("marco");
		ClientFactory.user.put(u1);
		
		
		System.out.println( ClientFactory.user.getById(1).getName() );
	}
	
}
