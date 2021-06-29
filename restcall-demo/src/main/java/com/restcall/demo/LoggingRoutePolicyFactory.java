package com.restcall.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.spi.RoutePolicy;
import org.apache.camel.spi.RoutePolicyFactory;
import org.apache.log4j.Logger;



public class LoggingRoutePolicyFactory implements RoutePolicyFactory {
	
	private final String serviceId;
	
	private final String transId;

	public LoggingRoutePolicyFactory(String serviceId, String transId) {
		super();
		this.serviceId = serviceId;
		this.transId = transId;
	}
	
	public static void addToContext(final CamelContext context, final String serviceId, final String transId) 
	throws IllegalArgumentException{
		final LoggingRoutePolicyFactory factory = new LoggingRoutePolicyFactory(serviceId, transId);
		context.addRoutePolicyFactory(factory);
	}

	public RoutePolicy createRoutePolicy(CamelContext camelContext, String routeId, RouteDefinition route) {
	 
		Logger.getLogger(LoggingRoutePolicyFactory.class)
		.debug(String.format("----creating logging route policy for routeId %s---", routeId));
		return new LoggingRoutePolicy(serviceId, transId);
		  
	}

}
