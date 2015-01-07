package lsc.webapp;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("webapp")
public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig () {
        packages("lsc.webapp");
    }
}
