package jmsdemo.consumer.service;

import jmsdemo.core.entity.Hello;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @JmsListener(destination = "HelloService.sayHello")
    public void  sayHello(Hello hello) {
        System.out.println("Hello, " + hello.getName());
    }

    @JmsListener(destination = "HelloService.sayHelloAgain")
    public String sayHelloAgain(Hello hello) {
        String reply = "Hello, " + hello.getName();
        System.out.println(reply);
        return reply;
    }

    @JmsListener(destination = "HelloService.sayHelloMutually")
    @SendTo("HelloService.sayHelloMutually#reply")
    public String sayHelloMutually(Hello hello) {
        String reply = "Hello, " + hello.getName() + ". I'm " + "Vanson";
        System.out.println(reply);
        return reply;
    }
}
