package lsc.localdatabase;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.EntityManager;

import lsc.localdatabase.dao.LifeCoachDao;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class App
{
    private static final URI BASE_URI = URI.create("http://localhost:5900/lsc/");
    
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
    {
    	System.out.println("Initializing DAO...");
    	EntityManager em = LifeCoachDao.instance.createEntityManager();
    	System.out.println("Starting lifestylecoach local database standalone HTTP server...");
        JdkHttpServerFactory.createHttpServer(BASE_URI, createApp());
        System.out.println("Server started on " + BASE_URI + "\n[kill the process to exit]");
    }

    public static ResourceConfig createApp() {
    	System.out.println("Starting lifestylecoach local database REST services...");
        return new MyApplicationConfig();
    }
    
    public static final URI getBASE_URI() { return BASE_URI;};
}
