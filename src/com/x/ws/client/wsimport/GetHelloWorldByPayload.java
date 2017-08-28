package com.x.ws.client.wsimport;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.soap.SOAPBinding;

public class GetHelloWorldByPayload {
	
	public static QName ServiceQName = new QName("http://rpcstyle.ws.x.com/","HelloWorldImplService");
	public static QName PortQName = new QName("http://rpcstyle.ws.x.com/", "HelloWorldImplPort");//wsdl定义的port

	public static void main(String[] args) throws MalformedURLException, JAXBException {
		URL url = new URL("http://localhost:9999/ws/hello?wsdl");
		String endpointAddress = "http://localhost:8888/ws/hello";//新 port 地址
		Service service = Service.create(url, ServiceQName);
		QName portName = new QName("http://rpcstyle.ws.x.com/", "HelloWorldImplPort11");//定义新 port
		service.addPort(portName , SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);//添加新 port
		JAXBContext jaxbContext = JAXBContext.newInstance(GetHelloWorldAsString.class,GetHelloWorldAsStringResponse.class);
		Dispatch<Object> dispatch = service.createDispatch(portName, jaxbContext, Mode.PAYLOAD);
//		Dispatch<Object> dispatch = service.createDispatch(PortQName, jaxbContext, Mode.PAYLOAD);//wsdl定义的port
		GetHelloWorldAsString request = new GetHelloWorldAsString();
		request.setArg0("message");
		GetHelloWorldAsStringResponse resp = (GetHelloWorldAsStringResponse)dispatch.invoke(request);
		System.out.println(resp.getReturn());

	}

}
