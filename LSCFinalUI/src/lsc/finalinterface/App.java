package lsc.finalinterface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class App
{
    private static final URI BASE_URI = URI.create("http://localhost:8080/lsc/");	
    public static void main(String[] args) throws Exception, IllegalArgumentException, IOException, URISyntaxException
    {
    	System.out.println("Starting lifestylecoach final UI standalone HTTP server...");
        JdkHttpServerFactory.createHttpServer(BASE_URI, createApp());
        System.out.println("Server started on " + BASE_URI + "\n[kill the process to exit]");
    }

    public static ResourceConfig createApp() {
    	System.out.println("Starting lifestylecoach final UI REST services...");
        return new MyApplicationConfig();
    }
}
