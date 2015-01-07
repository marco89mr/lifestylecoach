package lsc.localdatabase;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("lsc")
public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig () {
        packages("lsc.localdatabase");
    }
}
