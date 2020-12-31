package com.laowang.datasource.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SenderQueue {
    public static void main(String[] args) throws JMSException, InterruptedException {
        // 1. 获取连接工厂
        ActiveMQConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");

        // 2. 获取一个ActiveMQ的连接
        Connection connection = connectionFactory.createConnection();
        // 3. 获取session 这两个参数跟事务有关.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 开启事务, 参数固定:
        // Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
        // 4. 找目的地, 获取destination 消费端也会从这个目的地取消息
        Queue createQueue = session.createQueue("News Today");
        // 5. 创建消息创建者
        MessageProducer producer = session.createProducer(createQueue);
        // 51. 从代码层面不采取持久化策略
        // producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // 6. 创建一个消息
        TextMessage textMessage = null;
        for (int i = 0; i < 100; i++) {
            textMessage = session.createTextMessage("Come on. " + i);
            textMessage.setStringProperty("ClarkKent", "Big S");
            // 7. 向目的地写入消息
            producer.send(textMessage);
            // // 开启事务一定要提交/回滚.
            // if (i % 4 == 0) {
            // session.rollback();
            // } else {
            // session.commit();
            // }
            // TimeUnit.SECONDS.sleep(1);
        }
        // 8. 关闭连接
        connection.close();
    }
}
