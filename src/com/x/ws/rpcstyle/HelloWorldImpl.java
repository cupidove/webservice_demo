package com.x.ws.rpcstyle;

import javax.jws.WebService;

@WebService(endpointInterface = "com.x.ws.rpcstyle.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}

}
