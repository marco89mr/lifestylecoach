package lsc.businesslogic.endpoint;

import javax.persistence.EntityManager;
import javax.xml.ws.Endpoint;

import lsc.businesslogic.ws.LSCLogicImpl;

public class LSCPublisher {
	public static String SERVER_URL = "http://localhost";
	public static String PORT = "3333";
	public static String BASE_URL = "/lsc";
	
	public static String getEndpointURL() {
		return SERVER_URL+":"+PORT+BASE_URL;
	}
 
	public static void main(String[] args) {

		System.out.println("Starting Business Logic Service...");
		String endpointUrl = getEndpointURL();
		Endpoint.publish(endpointUrl, new LSCLogicImpl());
		System.out.println("--> Published at = "+endpointUrl);
    }
}

