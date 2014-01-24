package org.blackbox.execution.routing.c6.model;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transform {

    Logger log = LoggerFactory.getLogger(Transform.class);
    
    @SuppressWarnings("unchecked")
	public void process(Exchange exchange) throws Exception {
    	
    	Message msg = exchange.getIn();
        List<Object> inputs = msg.getBody(List.class);
     
        org.blackbox.execution.routing.c4.model.Output row5 = null;
        org.blackbox.execution.routing.c5.model.Output row6 = null;
        
        for (Object input : inputs) {
            if (org.blackbox.execution.routing.c4.model.Output.class.isAssignableFrom(input.getClass())) {
                row5 = (org.blackbox.execution.routing.c4.model.Output) input;       
            }
            if (org.blackbox.execution.routing.c5.model.Output.class.isAssignableFrom(input.getClass())) {
                row6 = (org.blackbox.execution.routing.c5.model.Output) input;       
            }            
        }
        
        org.blackbox.execution.routing.c6.model.Output output = new org.blackbox.execution.routing.c6.model.Output();
        output.points = com.tsp.LocaleProjection.projectionSimple(row6.center, row5.output);
         
        exchange.getIn().setBody(output);      
    }

}
