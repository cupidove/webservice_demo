package com.x.ws.client.wsimport;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class GetHelloWorldByPayload {
	
	public static QName ServiceQName = new QName("http://rpcstyle.ws.x.com/","HelloWorldImplService");
	public static QName PortQName = new QName("http://rpcstyle.ws.x.com/", "HelloWorldImplPort");

	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://localhost:9999/ws/hello?wsdl");
		Service service = Service.create(url, ServiceQName);
		//TODO

	}

}
