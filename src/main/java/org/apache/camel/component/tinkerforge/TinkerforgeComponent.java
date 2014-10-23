package org.apache.camel.component.tinkerforge;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * Represents the component that manages {@link TinkerforgeEndpoint}.
 */
public class TinkerforgeComponent extends DefaultComponent {

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new TinkerforgeEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
