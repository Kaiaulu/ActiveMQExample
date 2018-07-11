package be.kaiaulu.activemq.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = SimpleObject.Builder.class)
public class SimpleObject {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public static final class Builder {

        private String firstName;
        private String lastName;

        private Builder() {
        }

        public static Builder aSimpleObject() {
            return new Builder();
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public SimpleObject build() {
            SimpleObject simpleObject = new SimpleObject();
            simpleObject.setFirstName(firstName);
            simpleObject.setLastName(lastName);
            return simpleObject;
        }
    }
}
