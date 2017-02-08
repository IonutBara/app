package com.mycompany.myapp.service.jms.pointToPoint;

/**
 * Created by ibara on 2/1/2017.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class AsyncConsumerQueue implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(AsyncConsumerQueue.class);

/*    public static void main(String[] args) throws Exception {
        ServiceJMS serviceJMS = new ServiceJMS();
        Session session = serviceJMS.initJmsTemplate();
        Queue destination = session.createQueue("TestQueue");
        MessageConsumer consumer = session.createConsumer(destination);
        // set an asynchronous message listener
        AsyncConsumerQueue asyncConsumerQueue = new AsyncConsumerQueue();
        consumer.setMessageListener(asyncConsumerQueue);
    }*/

    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            logger.debug("Received message : {}", msg.getText());
        } catch (JMSException ex) {
            logger.error("Exception while receiving messages in class:: {}", ex.getClass());
            logger.warn("With message: {} {}", ex.getMessage());
            ex.printStackTrace();
        }
    }
}
