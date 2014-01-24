package org.blackbox.execution.routing.c1.model;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class SplitProcessor {

	@SuppressWarnings("unchecked")
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
        Map<String, Object> modelMap = (Map<String, Object>) in.getBody();
        in.setBody(modelMap.get(Output.class.getCanonicalName()));
	}

}
