package org.apache.camel.component.tinkerforge;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Tinkerforge producer.
 */
public class TinkerforgeProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(TinkerforgeProducer.class);
    private TinkerforgeEndpoint endpoint;

    public TinkerforgeProducer(TinkerforgeEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody());    
    }

}
