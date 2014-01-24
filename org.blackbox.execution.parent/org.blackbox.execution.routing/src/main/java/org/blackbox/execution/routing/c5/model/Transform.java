package org.blackbox.execution.routing.c5.model;

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
     
        org.blackbox.execution.routing.c4.model.Output row4 = null;
        
        for (Object input : inputs) {
            if (org.blackbox.execution.routing.c4.model.Output.class.isAssignableFrom(input.getClass())) {
                row4 = (org.blackbox.execution.routing.c4.model.Output) input;       
            }
        }
        
        org.blackbox.execution.routing.c5.model.Output output = new org.blackbox.execution.routing.c5.model.Output();
        output.center = com.tsp.LocaleProjection.getProjectionCenter(row4.output);
        
//        System.out.println("center = (" + output.center[0] + "," + output.center[1] +"," + output.center[2] + ")");
        exchange.getIn().setBody(output);      
    }

}
