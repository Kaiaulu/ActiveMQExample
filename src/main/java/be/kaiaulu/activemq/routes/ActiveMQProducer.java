package be.kaiaulu.activemq.routes;

import be.kaiaulu.activemq.model.Producer;
import be.kaiaulu.activemq.model.SimpleObject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQProducer extends RouteBuilder {

    @Autowired
    private Producer<SimpleObject> simpleBean;

    @Override
    public void configure() throws Exception {

        from("timer:foobar?period=2000")
                .log(LoggingLevel.INFO, "Sending to ActiveMQ")
                .bean(simpleBean)
                .setProperty("test", constant("the property"))
                .setHeader("test", constant("the header"))
                .marshal().json(JsonLibrary.Jackson, true)
                .to("activemq:task-queue-A")
                .to("activemq:task-queue-B")
                .end();
    }
}
