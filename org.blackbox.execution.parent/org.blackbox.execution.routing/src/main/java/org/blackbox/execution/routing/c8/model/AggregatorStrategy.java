package org.blackbox.execution.routing.c8.model;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class AggregatorStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		org.blackbox.execution.routing.c7.model.Output row8 = newExchange.getIn().getBody(org.blackbox.execution.routing.c7.model.Output.class);
        System.out.println("row8 = " + row8);
		Output output;
        
        if (oldExchange == null) {
            output = new Output();
            output.denormalize = "";
        
        } else {
            output = oldExchange.getIn().getBody(Output.class);           
        }
        if (row8.line != null) {
        	output.denormalize = output.denormalize.concat(String.valueOf(row8.line[0])).concat(",");
        	output.denormalize = output.denormalize.concat(String.valueOf(row8.line[1])).concat(",");
        	output.denormalize = output.denormalize.concat(String.valueOf(row8.line[2])).concat(",");
        }
        
        newExchange.getIn().setBody(output);
        return newExchange;
	}

}
