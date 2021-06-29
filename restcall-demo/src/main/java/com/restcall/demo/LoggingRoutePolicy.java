package com.restcall.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.camel.support.RoutePolicySupport;
import org.apache.log4j.Logger;

public class LoggingRoutePolicy extends RoutePolicySupport {
	
	private static final Logger LOG = Logger.getLogger(LoggingRoutePolicy.class);
	
	private String serviceId="";
	private String transId="";
	
	public LoggingRoutePolicy(String serviceId, String transId) {
		super();
		this.serviceId = serviceId;
		this.transId = transId;
	}

	@Override
	public void onInit(Route route) {
		// TODO Auto-generated method stub
		System.err.println("######## public void onInit(Route routeExchangeID-> " + route.getId() +"_"+ serviceId
				+ "_" +transId);
		
	}

	@Override
	public void onStart(Route route) {
		// TODO Auto-generated method stub
		System.err.println("######## public void onStart(Route routeExchangeID-> " + route.getId() +"_"+ serviceId
				+ "_" +transId);
	}

	@Override
	public void onStop(Route route) {
		// TODO Auto-generated method stub
		System.err.println("######## public void onStop(Route routeExchangeID-> " + route.getId() +"_"+ serviceId
				+ "_" +transId);
	}

	@Override
	public void onExchangeBegin(Route route, Exchange exchange) {
	/*	System.err.println("####### public void onExchangeBegin(Route route, Exchange exchangeExchangeID->"
				+ exchange.getExchangeId() +"-->"
				+route.getId() +"_"+ serviceId + "_" + transId); */
	}

	@Override
	public void onExchangeDone(Route route, Exchange exchange) {
	System.err.println("####### public void onExchangeDone(Route route, Exchange exchangeExchangeID->"
			+ exchange.getExchangeId() +"-->"
			+route.getId() +"_"+ serviceId + "_" + transId);
	}

	@Override
	public String toString() {
		return serviceId + "_" + transId;
	}
	
	
	

}
