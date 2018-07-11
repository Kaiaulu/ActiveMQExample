package be.kaiaulu.config;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;


@SpringBootApplication
@ComponentScan("be.kaiaulu.activemq")
@EnableAdminServer
public class ActiveMqExample {

    @Value("${app.activemq.host}")
    private String host;

    @Value("${app.activemq.port}")
    private Integer port;

    @Value("${app.activemq.username:#{null}}")
    private Optional<String> username;

    @Value("${app.activemq.password:#{null}}")
    private Optional<String> password;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://" + host + ":" + port);
        username.ifPresent(connectionFactory::setUserName);
        password.ifPresent(connectionFactory::setPassword);
        return connectionFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqExample.class, args);
    }
}
