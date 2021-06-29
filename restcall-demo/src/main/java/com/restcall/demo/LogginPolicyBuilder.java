package com.restcall.demo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class LogginPolicyBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		LoggingRoutePolicyFactory.addToContext(getContext(), "TestServiceId_1001", "TestTransId_000001");
		
		from("file:src/data_Filter?noop=true").routeId("TEST_ROUTE_ID_11111111")
		.filter(xpath("/order[not(@test)]"))
		.to("jms:incomingOrders");
		
		from("jms:incomingOrders").routeId("TEST_ROUTE_ID_222222").log("${body}");
		
	}
	
	public static void main(String[] args) {
		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		try {
			context.addRoutes(new LogginPolicyBuilder());
			context.start();
			Thread.sleep(1000 * 5);
			context.stop();
		} 
		catch (Exception e) {
	        e.printStackTrace();
		}
	}

}
