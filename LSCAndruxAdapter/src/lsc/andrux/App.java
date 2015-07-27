package lsc.andrux;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class App
{
    private static final URI BASE_URI = URI.create("http://localhost:1000/lsc/");
    
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
    {
    	System.out.println("Starting lifestylecoach andrux service standalone HTTP server...");
        JdkHttpServerFactory.createHttpServer(BASE_URI, createApp());
        System.out.println("Server started on " + BASE_URI + "\n[kill the process to exit]");
    }

    public static ResourceConfig createApp() {
    	System.out.println("Starting lifestylecoach andrux service REST services...");
        return new MyApplicationConfig();
    }
    
    public static final URI getBASE_URI() { return BASE_URI;};
}
