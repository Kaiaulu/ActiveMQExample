package be.kaiaulu.activemq.routes;

import be.kaiaulu.activemq.model.SimpleObject;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQListener extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(RuntimeException.class)
                .routeId("simple-object-exception-route-A")
                .handled(true)
                .log(LoggingLevel.INFO, "Exception on processing")
                .marshal().json(JsonLibrary.Jackson, SimpleObject.class)
                .convertBodyTo(String.class)
                .setHeader(Exchange.EXCEPTION_CAUGHT, simple("${exception.stacktrace}"))
                .inOnly("activemq:deadLetterQueue-A")
                .end();

        from("activemq:task-queue-A")
                .routeId("simple-object-route-A")
                .log(LoggingLevel.INFO, "Reading from ActiveMQ")
                .unmarshal().json(JsonLibrary.Jackson, SimpleObject.class)
                .log(LoggingLevel.INFO, "Received ${body.firstName} - ${body.lastName} with property : `${exchangeProperty.test}` and header : `${headers.test}`")
                .end();
    }
}
