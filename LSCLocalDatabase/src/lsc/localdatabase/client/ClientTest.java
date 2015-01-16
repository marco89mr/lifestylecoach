package lsc.localdatabase.client;

import java.io.IOException;
import java.net.URISyntaxException;

import lsc.localdatabase.model.User;


public class ClientTest {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
	{
		User u1 = ClientApp.user().getById(1);
		System.out.println( u1.getName() );
		u1.setMail("@g");
		ClientApp.user().put(u1);
	}
	
}
