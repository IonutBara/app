package com.mycompany.myapp.service.jms.publisherSubscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by ibara on 2/8/2017.
 */
public class SubscriberListener implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(SubscriberListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                logger.debug("Received message: {}", textMessage.getText());
            }
        } catch (JMSException e) {
            logger.error("Exception while receiving messages in class:: {}", e.getClass());
            logger.warn("With message: {} {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
