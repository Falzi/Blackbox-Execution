package org.blackbox.execution.routing.c3.model;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transform {

    Logger log = LoggerFactory.getLogger(Transform.class);
    
    public void process(Exchange exchange) throws Exception {
        Message msg = exchange.getIn();

        org.blackbox.execution.routing.c2.model.Output row2 = msg.getBody(org.blackbox.execution.routing.c2.model.Output.class);        
        
        double[] array = new double[]{row2.Latitude, row2.Longitude, row2.Altitude};
        
        org.blackbox.execution.routing.c3.model.Output output = new org.blackbox.execution.routing.c3.model.Output();
        output.array = array;
         
        exchange.getIn().setBody(output);      
    }

}
