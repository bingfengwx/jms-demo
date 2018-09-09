package jmsdemo.provider.service;

import jmsdemo.core.entity.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /**
     * 简单消息
     * @param name
     */
    public void sayHello(String name) {
        Hello hello = new Hello();
        hello.setName(name);
        jmsTemplate.convertAndSend("HelloService.sayHello", hello);

    }

    /**
     * 模拟同步消息
     * @param name
     * @return
     */
    public String sayHelloAgain(String name) {
        Hello hello = new Hello();
        hello.setName(name);

        String replay = jmsTemplate.convertSendAndReceive("HelloService.sayHelloAgain", hello, String.class);
        return replay;
    }

    /**
     * 双向消息
     * @param name
     */
    public void sayHelloMutually(String name) {
        Hello hello = new Hello();
        hello.setName(name);
        System.out.println("Hello, I'm " + name);
        jmsTemplate.convertAndSend("HelloService.sayHelloMutually", hello);
    }

    /**
     * 接受回复消息
     * @param msg
     */
    @JmsListener(destination = "HelloService.sayHelloMutually#reply")
    public void receiveMsg(String msg) {
        System.out.println(msg);
    }
}
