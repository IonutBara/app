package com.mycompany.myapp.web.rest.controllers;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.dto.DateTimeDao;
import com.mycompany.myapp.service.jms.JmsQualifier;
import com.mycompany.myapp.service.jms.JmsServiceInterface;
import com.mycompany.myapp.service.util.DateTimeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by ibara on 2/17/2017.
 */
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
@RequestMapping("/api")
class JmsServiceResource {

    private final Logger logger = LoggerFactory.getLogger(JmsServiceResource.class);

    @Inject
    @JmsQualifier(value = "serviceJMS")
    private JmsServiceInterface jmsServiceInterface;

    private DateTimeProcessor dateTimeProcessor;

    //setter based dependency injection
    @Inject
    public void setDateTimeProcessor(DateTimeProcessor dateTimeProcessor) {
        this.dateTimeProcessor = dateTimeProcessor;
    }

    @GetMapping("/jms/queue")
    @Timed
    public ResponseEntity<?> jmsQueueSendReceive() throws JMSException {
        DateTimeDao date = dateTimeProcessor.getDateTime();
        Session session = jmsServiceInterface.initJmsTemplate();
        jmsServiceInterface.sendMessage(session, date.getDateTime());
        jmsServiceInterface.receiveMsgSynchronously(session);
        jmsServiceInterface.close();
        logger.debug("JMS Queue Send Receive complete.");
        return new ResponseEntity<>(date, HttpStatus.OK);
    }
}
