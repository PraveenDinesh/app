package com.restcall.demo;

import java.net.HttpURLConnection;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;

public class EmployeeRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		onException(BadRequestException.class).handled(true)
		.log(LoggingLevel.ERROR, ">>>>${property." + Exchange.EXCEPTION_CAUGHT + "}")
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpURLConnection.HTTP_BAD_REQUEST))
		.process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				Exception exe = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
				exchange.getIn().setBody(exe.getMessage());	
			}
		})
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"));
		
		restConfiguration().host("localhost").port("8080")
		.component("servlet").bindingMode(RestBindingMode.json).jsonDataFormat("json-jackson")
		.endpointProperty("servletName", "CamelServlet");
		
		 rest("/empservice/")  //--------->relative path not base path
		.get().to("direct:getAll")
		.get("{empNo}").to("direct:getEmpById")
		.post().type(Employee.class).to("direct:saveEmp");
		
		from("direct:getAll").bean("employeDAO", "getAllEmps");  
		from("direct:getAll").bean("employeDAO", "getAllEmps").marshal().json(JsonLibrary.Jackson);
		from("direct:getEmpById").bean("employeDAO","getEmpById");
		from("direct:saveEmp").bean("employeDAO", "saveEmployee");
		
		
	}

}
