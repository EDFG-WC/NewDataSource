package com.laowang.datasource.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ReceiverDLQ {
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
        // 4. 找目的地, 获取destination 消费端也会从这个目的地取消息
        Queue dlq = session.createQueue("ActiveMQ.DLQ");
        Queue normalQueue = session.createQueue("price news");
        // 5. 创建消息消费者(一般是一个长连接)
        /**
         * 获取死信队列的消息
         */
        MessageConsumer consumer = session.createConsumer(dlq);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof MapMessage) {
                    MapMessage MapMsg = (MapMessage)message;
                    System.out.println("MapMessage is: " + MapMsg);
                    try {
                        System.out.println("name: " + MapMsg.getString("name"));
                        System.out.println("age: " + MapMsg.getInt("age"));
                        System.out.println("price: " + MapMsg.getDouble("price"));
                    } catch (JMSException jmsException) {
                        jmsException.printStackTrace();
                    }
                }
            }
        });
    }
}
