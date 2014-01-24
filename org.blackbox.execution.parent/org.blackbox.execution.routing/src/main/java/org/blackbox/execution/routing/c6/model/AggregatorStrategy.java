package org.blackbox.execution.routing.c6.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class AggregatorStrategy implements AggregationStrategy {

	@SuppressWarnings("unchecked")
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		List<Object> output = null;
        
//		System.out.println("aggregating " + newExchange.getIn().getBody().getClass());
        
        if (oldExchange == null) {
        	output = new ArrayList<Object>();
        	output.add(newExchange.getIn().getBody());
        	
        	newExchange.getIn().setBody(output);
            return newExchange;
        
        } else {
        	output = oldExchange.getIn().getBody(List.class);           
        	output.add(newExchange.getIn().getBody());
        	
        	newExchange.getIn().setBody(output);
            return newExchange; 
        }
	}

}
