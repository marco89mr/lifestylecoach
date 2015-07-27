package lsc.andrux.rest.client;

import java.net.URI;
import java.net.URISyntaxException;

import lsc.andrux.rest.model.Quote;
import lsc.rest.client.ClientUtils;


public class AndruxClient {
	

	
	public static FamousClient famous = new FamousClient();
	
	
	
	public static abstract class BaseClient<M> {

		protected static final String protocol = "http";
		protected static final String host = "//localhost";
		protected static final String port = ":1000";
		protected static final String base_path = "/lsc";
		public static final String main_url() {
			URI uri = null;
			try {
				uri = new URI(protocol, host	+port	+base_path	, null);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return uri.toASCIIString();
		}
		
		public abstract String resource_url();
		
		protected abstract Class<M> model_class();
		
		
		public M getRandom() {
			return ClientUtils.<M>http_get(model_class(), resource_url() );
		}
		
	}
	
	
	
	public static class FamousClient extends BaseClient<Quote> {		
		@Override
		protected Class<Quote> model_class() { return Quote.class; }
		@Override
		public String resource_url() { return main_url() + "/famous"; }
	}
	
	
	
}


