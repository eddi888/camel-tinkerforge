package org.apache.camel.component.tinkerforge;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class TinkerforgeComponentTest extends CamelTestSupport {

    @Test
    public void testTinkerforge() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);       
        
        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("tinkerforge://foo")
                  .to("tinkerforge://bar")
                  .to("mock:result");
            }
        };
    }
}
