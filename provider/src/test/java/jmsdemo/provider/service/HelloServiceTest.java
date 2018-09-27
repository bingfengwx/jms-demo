package jmsdemo.provider.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    private HelloService helloService;

    /**
     * 简单消息
     */
    @Test
    public void sayHello() {
        for (int i = 0; i < 100; i++) {
            String name = "Lily_"  + i;
            helloService.sayHello(name);
        }
    }

    /**
     * 模拟同步请求
     */
    @Test
    public void sayHelloAgain() {

        long start = System.currentTimeMillis();

        String name = "Lily";
        String res = helloService.sayHelloAgain(name);
        long end = System.currentTimeMillis();
        System.out.println(res + "。用时" + (end - start) + "毫秒");
    }

    /**
     * 多线程模拟同步请求测试
     * @throws IOException
     */
    @Test
    public void sayHelloAgainThread() throws IOException {
        for (int i = 0; i < 100; i ++) {
            String name = "Lily_" + i;
            HelloServiceThread thread = new HelloServiceThread(name);
            thread.start();
        }
        System.in.read();
    }

    /**
     * 测试双向消息
     * @throws IOException
     */
    @Test
    public void sayHelloMutually() throws IOException {
        String name = "Lily";
        helloService.sayHelloMutually(name);
        System.in.read();
    }

    class HelloServiceThread implements Runnable {

        private String name;
        private Thread t;

        public HelloServiceThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            String res = helloService.sayHelloAgain(name);
            long end = System.currentTimeMillis();
            System.out.println(String.format("收到%s的回复：%s, 用时%d毫秒", name, res, end - start));

        }

        public void start() {
            if (t == null) {
                t = new Thread(this);
            }
            t.start();
        }
    }
}