package lsc.businesslogic.client;
 
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import lsc.businesslogic.ws.LSCLogic;
import lsc.rest.model.Goal;
import lsc.rest.model.Goal.Function;
import lsc.rest.model.Goal.Perc;
import lsc.rest.model.Goal.Reference;
import lsc.rest.model.StatisticData;
import lsc.rest.model.Statistic;
 
public class TestApp{
	public static void main(String[] args) throws Exception {		
		URL url = new URL("http://localhost:3333/lsc?wsdl");
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.businesslogic.lsc/", "LSCLogic");
        Service service = Service.create(url, qname);
        //End-point
        LSCLogic lscLogic = service.getPort(LSCLogic.class);
		service.setHandlerResolver(new JaxWsHandlerResolver());

		System.out.println("SDE 2014 Final Project lsc Logic Service Client Marco Robol");
		System.out.println("Open endpoint on: "+url.toString());
		System.out.println("");
        
        
		//lscLogic.register_record( new RecordData() );
        
        
        Statistic s = lscLogic.compute_statistic(1, "run", "distance", "2015-01-01", "2015-01-20", Goal.Interval.day, Goal.Function.sum);

        for(StatisticData d : s.getDatas()){
        	System.out.println(d.getFromDate());
        	System.out.println(d.getToDate());
        	System.out.println(d.getValues().get(Function.sum).get(Reference.target).get(Perc.abs));
        }
        
        
	}
	
	
	
}