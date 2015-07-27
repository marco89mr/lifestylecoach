package lsc.finalinterface;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import lsc.businesslogic.client.JaxWsHandlerResolver;
import lsc.businesslogic.ws.LSCLogic;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.UserCollection;
import lsc.storage.rest.client.StorageClient;


public class DoubleConnectionProblemApp {
	
	public static void main(String[] args) throws IllegalArgumentException
	{
		
		
		/*
		// check deadlines
		System.out.println("DEBUG NotificationLogic.getAllUnderUser id:" + 1);
		NotificationCollection nn = BusinessLogicClient.check_today(1);
		System.out.println("DEBUG NotificationLogic.getAllUnderUser 2");
		for(Notification n : nn) {
			System.out.println(n.getId());
			System.out.println(n.getMessage());
			System.out.println(n.getStatus());
		}
		*/
		
		
		{
			String requestUrl = StorageClient.user.resource_url();
			Client client = ClientBuilder.newClient();
			WebTarget service = client.target( requestUrl );
			//Builder builder = service.request().accept(MediaType.APPLICATION_XML);//PROBLEM with XML
			Builder builder = service.request().accept(MediaType.APPLICATION_JSON);//PROBLEM with XML
			Response response = builder.get();

			response.bufferEntity();
			UserCollection uu = response.readEntity( UserCollection.class );
			//response.notify();
			
			response.close();
		}
		
		
		
		
		LSCLogic lscLogic = null;
		URL url;
		try {
			System.out.println("DEBUG 1");
			url = new URL("http://localhost:3333/lsc?wsdl");
	        //1st argument service URI, refer to wsdl document above
			//2nd argument is service name, refer to wsdl document above
			System.out.println("DEBUG 2");
	        QName qname = new QName("http://ws.businesslogic.lsc/", "LSCLogic");
			System.out.println("DEBUG 3");
	        Service service = Service.create(url, qname);
	        //End-point
			System.out.println("DEBUG 4");
	        lscLogic = service.getPort(LSCLogic.class);
			System.out.println("DEBUG 5");
			service.setHandlerResolver(new JaxWsHandlerResolver());
			System.out.println("DEBUG 6");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lscLogic.checkExpiredDedline(1);
		/*
		for(Notification n : nn) {
			System.out.println(n.getId());
			System.out.println(n.getMessage());
			System.out.println(n.getStatus());
		}
		*/
		
		
		RecordComplex r = StorageClient.record.getById(53);
		try {
			System.out.println("DEBUG 1");
			url = new URL("http://localhost:3333/lsc?wsdl");
	        //1st argument service URI, refer to wsdl document above
			//2nd argument is service name, refer to wsdl document above
			System.out.println("DEBUG 2");
	        QName qname = new QName("http://ws.businesslogic.lsc/", "LSCLogic");
			System.out.println("DEBUG 3");
	        Service service = Service.create(url, qname);
	        //End-point
			System.out.println("DEBUG 4");
	        lscLogic = service.getPort(LSCLogic.class);
			System.out.println("DEBUG 5");
			service.setHandlerResolver(new JaxWsHandlerResolver());
			System.out.println("DEBUG 6");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lscLogic.checkRecordsUnder(r.getUserId(), r.getType(), r.getDate());
		
		
	}
	
}
