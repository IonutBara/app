package com.mycompany.myapp.service.jms.pointToPoint;

import com.mycompany.myapp.service.jms.JmsServiceInterface;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Created by ibara on 2/3/2017.
 */
@Service
public class ServiceJMS  implements JmsServiceInterface {

    static final private String brokerURL = "tcp://IBARA-PC0G84LL:61616";
    static final private String queue = "TestQueue";
    private final Logger logger = LoggerFactory.getLogger(ServiceJMS.class);

    private ActiveMQConnectionFactory connectionFactory;

    @Autowired
    public ServiceJMS(ActiveMQConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ServiceJMS(){}

    @Override
    public Session initJmsTemplate() {
        Session session = null;
        try {
            //create ConnectionFactory
            connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            //create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException | JMSRuntimeException jms) {
            logger.error("Exception while initialize session in class {}", jms.getClass());
            logger.error("with Message: {}", jms.getMessage());
            jms.printStackTrace();
        }
        return session;
    }

    @Override
    public void sendMessage(Session session, String text) throws JMSException {
        if (session == null) {
            logger.error("Session should not be null");
            throw new NullPointerException("Session should not be null");
        }
        Queue destination = session.createQueue(queue);
        MessageProducer producer = session.createProducer(destination);
        //producer.setDeliveryDelay(DeliveryMode.NON_PERSISTENT);
        logger.debug(text);
        TextMessage message = session.createTextMessage(text);
        producer.send(message);
    }

    @Override
    public void receiveMsgSynchronously(Session session) throws JMSException {
        if (session == null) {
            logger.error("Session should not be null");
            throw new NullPointerException("Session should not be null");
        }
        Queue destination = session.createQueue(queue);
        MessageConsumer consumer = session.createConsumer(destination);
        //wait for a message
        Message message = consumer.receive(1000);

        if (message instanceof TextMessage) {
            TextMessage message1 = (TextMessage) message;
            String text = message1.getText();
            logger.debug("Received: {}", text);
        } else {
            logger.debug("Received message: {}", message);
        }
        try {
            consumer.close();
        } catch (JMSException | JMSRuntimeException e) {
            logger.error("Exception while close MessageConsumer in class:: {}", e.getClass());
            logger.warn("With message: {} {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void close() {
/*        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                logger.error("Exception while close JMS Session in class:: {}", e.getClass());
                logger.warn("With message: {} {}", e.getMessage());
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error("Exception while close JMS Connection in class:: {}", e.getClass());
                logger.warn("With message: {} {}", e.getMessage());
                e.printStackTrace();
            }
        }*/

    }
}
