package org.blackbox.execution.routing.c9.model;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transform {

    Logger log = LoggerFactory.getLogger(Transform.class);
    
    public void process(Exchange exchange) throws Exception {
    	
    	Message msg = exchange.getIn();
        
        org.blackbox.execution.routing.c8.model.Output row9 = msg.getBody(org.blackbox.execution.routing.c8.model.Output.class);
        
        StringBuilder builder = new StringBuilder();
        
        builder.append("{");
		builder.append("'version' : 2, ");   
		builder.append("'scale' : 1.000000,  ");  
		builder.append("'materials': [],    ");
		builder.append("'morphTargets': [],");
		builder.append("'morphColors': [],");
		builder.append("'normals': [],");
		builder.append("'colors': [],");
		builder.append("'uvs': [[]],    ");
		builder.append("'vertices' : [ ").append(row9.denormalize).append("],");   	
		builder.append("'faces' : []");
		builder.append("}");
        
//        System.out.println("center = (" + output.center[0] + "," + output.center[1] +"," + output.center[2] + ")");
        exchange.getIn().setBody(builder.toString());      
    }

}
