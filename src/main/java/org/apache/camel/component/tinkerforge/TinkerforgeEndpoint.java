package org.apache.camel.component.tinkerforge;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * Represents a Tinkerforge endpoint.
 */
public class TinkerforgeEndpoint extends DefaultEndpoint {

    public TinkerforgeEndpoint() {
    }

    public TinkerforgeEndpoint(String uri, TinkerforgeComponent component) {
        super(uri, component);
    }

    public TinkerforgeEndpoint(String endpointUri) {
        super(endpointUri);
    }

    public Producer createProducer() throws Exception {
        return new TinkerforgeProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new TinkerforgeConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }
}
