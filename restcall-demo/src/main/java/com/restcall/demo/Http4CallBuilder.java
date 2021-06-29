package com.restcall.demo;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;

public class Http4CallBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("direct:callEmployeeService")
		.setHeader(Exchange.HTTP_METHOD).simple("GET")
		.setHeader("Accept").simple("application/json")
		.setHeader("content-type").simple("application/json")
		.setHeader(Exchange.HTTP_URI, constant("http://localhost:8080/restcall1-demo/camel/empservice"))
		
		.to("http4://dummyHost")
		.convertBodyTo(String.class)
		.marshal().json(JsonLibrary.Jackson)
		
		.log(">>>>>>>>>>>>> ${body}");
		
	}
	
	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new Http4CallBuilder());
		context.start();
		
		ProducerTemplate template = context.createProducerTemplate();
        String response  =template.requestBody("direct:callEmployeeService","", String.class);
        
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> in main"+response);
        
		Thread.sleep(1000);
		context.start();
	}

}
