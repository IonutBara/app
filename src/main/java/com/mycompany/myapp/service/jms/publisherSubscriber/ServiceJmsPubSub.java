package com.mycompany.myapp.service.jms.publisherSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Created by ibara on 2/6/2017.
 */
@Service
public class ServiceJmsPubSub {

    static final private String brokerURL = "tcp://IBARA-PC0G84LL:61616";
    static final private String topicName = "TestTopic";
    private final Logger logger = LoggerFactory.getLogger(ServiceJmsPubSub.class);

    private TopicConnectionFactory connectionFactory;

    @Autowired
    public ServiceJmsPubSub(TopicConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ServiceJmsPubSub() {
    }

    public TopicSession initJmsTemplatePubSub() {
        TopicSession session = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            TopicConnection connection = connectionFactory.createTopicConnection();
            connection.setClientID("JMSTOPIC");
            connection.start();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            //Topic topic = session.createTopic(topicName);
        } catch (JMSException | JMSRuntimeException e) {
            logger.error("Exception while init JMS template in class:: {}", e.getClass());
            logger.warn("With message: {} {}", e.getMessage());
        }
        logger.debug("Starting connection successfully");
        return session;
    }

    public void publish(TopicSession session) throws JMSException {
        if (session == null) {
            logger.error("Session should not be null");
            throw new NullPointerException("Session should not be null");
        }
        Topic topic = session.createTopic(topicName);
        TopicPublisher producer = session.createPublisher(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        while (session != null) {
            TextMessage message = session.createTextMessage(Thread.currentThread().getName()
                + " : " + System.currentTimeMillis());
            producer.publish(message);
            logger.debug("Message: {} sent!", message.getText());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void subscribe(TopicSession session) throws JMSException, InterruptedException {
        if (session == null) {
            logger.error("Session should not be null");
            throw new NullPointerException("Session should not be null");
        }
        Topic topic = session.createTopic(topicName);
        // set an asynchronous message listener
        TopicSubscriber receiver = session.createSubscriber(topic);
        SubscriberListener listener = new SubscriberListener();
        receiver.setMessageListener(listener);
        logger.debug("Subscriber is ready, waiting for messages...");
    }

    public void close() {
/*        if (session != null) {
            try {
                session.close();
            } catch (JMSException e) {
                logger.error("Exception while close JMS Session in class:: {}", e.getClass());
                logger.warn("With message: {} {}", e.getMessage());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                logger.error("Exception while close JMS Connection in class:: {}", e.getClass());
                logger.warn("With message: {} {}", e.getMessage());
            }
        }*/
    }

}
