package com.mycompany.myapp.service.jms;

import com.mycompany.myapp.service.jms.publisherSubscriber.ServiceJmsPubSub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.TopicSession;

public class JMSmainClass {
    private final static Logger logger = LoggerFactory.getLogger(JMSmainClass.class);

    //Queue test
/*    public static void main(String[] args) throws JMSException, InterruptedException {
        ServiceJMS jmsTemplate = new ServiceJMS();
        try {
            Session session = jmsTemplate.initJmsTemplate();
            //Queue send and receive asynchronously
            while (session != null) {
                jmsTemplate.sendMessage(session, "message to send: " + Thread.currentThread().getName());
                logger.debug("Message sent successfully");
                Thread.sleep(5000);
                jmsTemplate.receiveMsgSynchronously(session);
            }
        } catch (JMSException e) {
            logger.error("Exception while sending/receiving message via JMS in class: {}", e.getClass());
            logger.error("Message: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            jmsTemplate.close();
        }
    }*/

/*    public static void main(String[] args) throws JMSException, InterruptedException {
        //Topic test
        ServiceJmsPubSub serviceJmsPubSub = new ServiceJmsPubSub();
        try {
            TopicSession session = serviceJmsPubSub.initJmsTemplate();
            serviceJmsPubSub.subscribe(session);
            serviceJmsPubSub.publish(session);
        } catch (JMSException e) {
            logger.error("Exception while sending/receiving message via JMS in class: {}", e.getClass());
            logger.error("Message: {}", e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            logger.error("InterruptedException while sending/receiving message via JMS in class: {}", e.getClass());
            logger.error("Message: {}", e.getMessage());
        } finally {
            serviceJmsPubSub.close();
        }
    }*/
}
