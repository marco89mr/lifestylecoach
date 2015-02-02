package lsc.businesslogic.client;
 
import java.io.IOException;
import java.util.Set;
 
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
 
public class JaxWsLoggingHandler implements SOAPHandler<SOAPMessageContext> {
 
    @Override
    public void close(MessageContext arg0) {
    }
 
    @Override
    public boolean handleFault(SOAPMessageContext arg0) {
        SOAPMessage message = arg0.getMessage();
        try {
            message.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
 
    @Override
    public boolean handleMessage(SOAPMessageContext arg0) {
        SOAPMessage message = arg0.getMessage();
        boolean isOutboundMessage = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isOutboundMessage) {
            System.out.println("OUTBOUND MESSAGE\n");
 
        } else {
            System.out.println("INBOUND MESSAGE\n");
        }
        try {
            message.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
 
    @Override
    public Set<QName> getHeaders() {
        return null;
    }
 
}