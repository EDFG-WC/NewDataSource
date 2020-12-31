package com.laowang.datasource.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ReceiverTopic {
    public static void main(String[] args) throws JMSException {
        // 1. 获取连接工厂
        ActiveMQConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");

        // 2. 获取一个ActiveMQ的连接并启动
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3. 获取session
        // 自动返回ack
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 开启之后, 我们要判断, 当前收到的信息是否符合规定, 然后手动ack.
        // Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        // 4. 找目的地, 获取destination 消费端也会从这个目的地取消息
        Destination topic = session.createTopic("Daily News");
        // 5. 创建消息消费者(一般是一个长连接)
        MessageConsumer consumer = session.createConsumer(topic);
        // 6. 消费信息
        while (true) {
            TextMessage message = (TextMessage)consumer.receive();
            String text = message.getText();
            System.out.println("today's news is: " + text);
        }
    }
}
