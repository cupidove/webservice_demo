package com.x.ws.client.wsimport;

public class GetHelloWorld {

	public static void main(String[] args) {
		HelloWorldImplService service = new HelloWorldImplService();
		HelloWorld port = service.getHelloWorldImplPort();
		String string = port.getHelloWorldAsString("acdd");
		System.out.println(string);

	}

}
