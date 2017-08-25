package com.x.ws.rpcstyle.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;


public class HelloWorldClient {

	public static void main(String[] args) throws SOAPException, IOException {
		URL url = null;
		try {
//			url = new URL("http://localhost:9999/ws/hello?wsdl");
			url = new URL("http://localhost:8888/ws/hello?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QName qname = new QName("http://rpcstyle.ws.x.com/","HelloWorldImplService");
		Service service = Service.create(url , qname );
		
		QName portName = new QName("http://rpcstyle.ws.x.com/", "HelloWorldImplPort");
//		String endpointAddress = "http://localhost:9999/ws/hello";
//		service.addPort(portName , SOAPBinding.SOAP11HTTP_BINDING, endpointAddress );
		
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage soapRequest = factory.createMessage();		
		QName getHelloWorldAsStringQName = new QName("http://rpcstyle.ws.x.com", "getHelloWorldAsString");
		SOAPElement getHelloWorldAsStringResponse = soapRequest.getSOAPBody().addChildElement(getHelloWorldAsStringQName);
		SOAPElement element = getHelloWorldAsStringResponse.addChildElement("arg0").addTextNode("abcd");
		
		DOMSource domRequestMsg = new DOMSource(soapRequest.getSOAPPart());		
		Dispatch<DOMSource> dispatch = service.createDispatch(portName, DOMSource.class, Mode.MESSAGE);
		DOMSource domResponseMsg = dispatch.invoke(domRequestMsg);
		
		SOAPMessage soapReq = factory.createMessage();
		soapReq.getSOAPPart().setContent(domRequestMsg);
		
		System.out.println("Client Request as a DOMSource data in MESSAGE Mode");  
        soapReq.writeTo(System.out);  
        System.out.println("\n");
        
        
        System.out.println("Response from server: " + domResponseMsg.getNode().getLastChild().getTextContent()); 
        

	}

}
