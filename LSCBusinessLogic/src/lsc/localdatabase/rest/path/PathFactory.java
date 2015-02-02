package lsc.localdatabase.rest.path;

import java.net.URI;
import java.net.URISyntaxException;




public class PathFactory {
	
	protected static final String protocol = "http";
	protected static final String host = "//localhost";
	protected static final String port = ":5900";
	protected static final String base_path = "/lsc";
	
	public static final String getCompletePath() {
		URI uri = null;
		try {
			uri = new URI(protocol, host	+port	+base_path	, null);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(uri.toASCIIString());
		String complete_path = uri.toASCIIString();
		return complete_path;
	}
	
	
	
	
	

	
	public static MainPath.User user() { return new MainPath.User().setCompletePath(getCompletePath() + "/user"); }
	
	public static MainPath.Record record() { return new MainPath.Record().setCompletePath(getCompletePath() + "/record"); }
	
	public static MainPath.Data data() { return new MainPath.Data().setCompletePath(getCompletePath() + "/data"); }
	
	public static MainPath.Goal goal() { return new MainPath.Goal().setCompletePath(getCompletePath() + "/goal"); }
	
	public static MainPath.Deadline deadline() { return new MainPath.Deadline().setCompletePath(getCompletePath() + "/deadline"); }
	
	public static MainPath.ToDo todo() { return new MainPath.ToDo().setCompletePath(getCompletePath()); }
	
	public static MainPath.Notification notification() { return new MainPath.Notification().setCompletePath(getCompletePath() + "/notification"); }
	
	
	
	
	
}
