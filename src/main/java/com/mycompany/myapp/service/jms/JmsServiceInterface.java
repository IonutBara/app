package com.mycompany.myapp.service.jms;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by ibara on 2/17/2017.
 */
public interface JmsServiceInterface {

    Session initJmsTemplate();

    void sendMessage(Session session, String text) throws JMSException;

    void receiveMsgSynchronously(Session session) throws JMSException;

    void close();
}
