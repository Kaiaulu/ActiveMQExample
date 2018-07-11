package be.kaiaulu.activemq.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SimpleBean implements Producer<SimpleObject> {

    @Override
    public SimpleObject apply() {
        return SimpleObject.Builder.aSimpleObject()
                .withFirstName(UUID.randomUUID().toString())
                .withLastName(UUID.randomUUID().toString())
                .build();
    }
}
