package lsc.localdatabase;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("lsc")
public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig () {
        packages("lsc.localdatabase");
        
        register(LoggingFilter.class);
        register("org.glassfish.jersey.filter.LoggingFilter");
        
        property("jersey.config.server.tracing.type", "ALL");
        property("jersey.config.server.tracing.threshold", "VERBOSE");
        
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        property(ServerProperties.PROCESSING_RESPONSE_ERRORS_ENABLED, true);
    }
}
