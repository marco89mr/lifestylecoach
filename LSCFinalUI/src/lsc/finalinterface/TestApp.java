package lsc.finalinterface;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import lsc.businesslogic.client.BusinessLogicClient;
import lsc.businesslogic.client.JaxWsHandlerResolver;
import lsc.businesslogic.ws.LSCLogic;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;


public class TestApp {
	
	public static void main(String[] args) throws IllegalArgumentException, MalformedURLException
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
		
		
		
		

		LSCLogic lscLogic = null;
		URL url;
		url = new URL("http://localhost:3333/lsc?wsdl");
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.businesslogic.lsc/", "LSCLogic");
        Service service = Service.create(url, qname);
        //End-point
        lscLogic = service.getPort(LSCLogic.class);
		service.setHandlerResolver(new JaxWsHandlerResolver());

		NotificationCollection nn = lscLogic.checkToday(1);
		for(Notification n : nn) {
			System.out.println(n.getId());
			System.out.println(n.getMessage());
			System.out.println(n.getStatus());
		}
		
		
		
	}
	
}
