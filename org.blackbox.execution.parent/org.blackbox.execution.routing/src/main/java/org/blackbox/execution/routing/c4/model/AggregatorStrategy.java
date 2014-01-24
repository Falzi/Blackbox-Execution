package org.blackbox.execution.routing.c4.model;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.commons.lang.ArrayUtils;

public class AggregatorStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		org.blackbox.execution.routing.c3.model.Output row3 = (org.blackbox.execution.routing.c3.model.Output) newExchange.getIn().getBody();        
        org.blackbox.execution.routing.c4.model.Output output = new org.blackbox.execution.routing.c4.model.Output();
        
        if (oldExchange == null) {
            output = new org.blackbox.execution.routing.c4.model.Output();
            output.output = null;
                    
        } else {
            output = oldExchange.getIn().getBody(org.blackbox.execution.routing.c4.model.Output.class);  
//            oldExchange.getIn().setBody(output);
        }
        output.output = (double[][]) ArrayUtils.add(output.output, row3.array);
        
        newExchange.getIn().setBody(output);
        return newExchange;
	}

}
