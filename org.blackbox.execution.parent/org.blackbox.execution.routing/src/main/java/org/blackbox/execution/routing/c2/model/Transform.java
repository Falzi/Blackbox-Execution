package org.blackbox.execution.routing.c2.model;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transform {

    Logger log = LoggerFactory.getLogger(Transform.class);
    
    public void process(Exchange exchange) throws Exception {
        Message msg = exchange.getIn();

        org.blackbox.execution.routing.c1.model.Output row1 = (org.blackbox.execution.routing.c1.model.Output) msg.getBody();        
        org.blackbox.execution.routing.c2.model.Output output = new org.blackbox.execution.routing.c2.model.Output();
              
        output.Date = row1.Date;
        output.Hour = row1.Hour;
        output.Longitude = convertDmsToDegre(row1.Longitude);
        output.Latitude = convertDmsToDegre(row1.Latitude);
        output.Altitude = Double.parseDouble(row1.Altitude);
        output.Vx = row1.Vx;
        output.Vy = row1.Vy;
        output.Vz = row1.Vz;
        
        exchange.getIn().setBody(output);      
    }

    double convertDmsToDegre(String dms) {
    	String[] longDms = dms.split(" ");
    	return (Double.parseDouble(longDms[0]) + Double.parseDouble(longDms[1])/60 + Double.parseDouble(longDms[2])/3600);
    }

}
