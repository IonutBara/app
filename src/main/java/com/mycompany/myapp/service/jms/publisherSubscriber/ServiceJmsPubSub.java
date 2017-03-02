package com.mycompany.myapp.service.jms.publisherSubscriber;

import com.mycompany.myapp.service.jms.JmsQualifier;
import com.mycompany.myapp.service.jms.JmsServiceInterface;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Created by ibara on 2/6/2017.
 */
@Service
@JmsQualifier(value = "serviceJmsPubSub")
@Scope(value = "singleton")
public class ServiceJmsPubSub implements JmsServiceInterface {

    private static final String BROKER_URL = "tcp://IBARA-PC0G84LL:61616";
    private static final String TOPIC_NAME = "TestTopic";
    private static final String CLIENT_ID = "JMSTOPIC_id";
    private final Logger LOGGER = LoggerFactory.getLogger(ServiceJmsPubSub.class);

    private TopicConnectionFactory connectionFactory;

    @Autowired
    public ServiceJmsPubSub(TopicConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ServiceJmsPubSub() {}

    @Override
    public TopicSession initJmsTemplate() {
        TopicSession session = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            TopicConnection connection = connectionFactory.createTopicConnection();
            connection.setClientID(CLIENT_ID);
            connection.start();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            //Topic topic = session.createTopic(TOPIC_NAME);
        } catch (JMSException | JMSRuntimeException e) {
            LOGGER.error("Exception while init JMS template in class:: {}", e.getClass());
            LOGGER.warn("With message: {} {}", e.getMessage());
        }
        LOGGER.debug("Starting connection successfully");
        return session;
    }

    private void publish(TopicSession session) throws JMSException {
        if (session == null) {
            LOGGER.error("Session should not be null");
            throw new NullPointerException("Session should not be null");
        }
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicPublisher producer = session.createPublisher(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        while (session != null) {
            TextMessage message = session.createTextMessage(Thread.currentThread().getName()
                + " : " + System.currentTimeMillis());
            producer.publish(message);
            LOGGER.debug("Message: {} sent!", message.getText());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                LOGGER.error("Exception  while publish a message to a Topic in class: {}, with message: {}",
                    e, getClass(), e.getMessage());
            }
        }
    }

    private void subscribe(TopicSession session) throws JMSException, InterruptedException {
        if (session == null) {
            LOGGER.error("Session should not be null");
            throw new NullPointerException("Session should not be null");
        }
        Topic topic = session.createTopic(TOPIC_NAME);
        // set an asynchronous message listener
        TopicSubscriber receiver = session.createSubscriber(topic);
        SubscriberListener listener = new SubscriberListener();
        receiver.setMessageListener(listener);
        LOGGER.debug("Subscriber is ready, waiting for messages...");
    }

    @Override
    public void close() {
/*        if (session != null) {
            try {
                session.close();
            } catch (JMSException e) {
                LOGGER.error("Exception while close JMS Session in class:: {}", e.getClass());
                LOGGER.warn("With message: {} {}", e.getMessage());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                LOGGER.error("Exception while close JMS Connection in class:: {}", e.getClass());
                LOGGER.warn("With message: {} {}", e.getMessage());
            }
        }*/
    }


    @Override
    public void sendMessage(Session session, String text) throws JMSException {
        publish((TopicSession) session);
    }

    @Override
    public void receiveMsgSynchronously(Session session) throws JMSException {
        try {
            subscribe((TopicSession) session);
        } catch (InterruptedException e) {
            LOGGER.error("Exception  while subscribe to a Topic in class: {}, with message: {}",
                e, getClass(), e.getMessage());
        }
    }
}
