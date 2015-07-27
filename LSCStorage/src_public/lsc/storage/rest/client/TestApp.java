package lsc.storage.rest.client;

import java.io.IOException;
import java.net.URISyntaxException;

import lsc.rest.model.Goal;
import lsc.rest.model.User;


public class TestApp {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
	{
		
				
		
		
		
		User u1 = StorageClient.user.getById(1);
		System.out.println( u1.getName() );
		
		u1.setName("marco");
		
		StorageClient.user.put(u1);
		
		
		System.out.println( StorageClient.user.getById(1).getName() );
		
		
		
		
		
		
		Goal g = StorageClient.goal.getById(102);
		g.setId(0);
		
		StorageClient.goal.post(g);
		
		System.out.println( g.getId() );
		
		
	}
	
}
