package com.lengyeli.actionmonitor.service;

import com.lengyeli.actionmonitor.model.JmsMessage;
import com.lengyeli.actionmonitor.model.WebsocketMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Receiving jms message and forward it to websocket
 * Created by ilengyel on 2017. 10. 09..
 */
@Component
public class JmsReceiver {

    private static Logger logger = LoggerFactory.getLogger(JmsReceiver.class);

    private final SimpMessagingTemplate websocket;

    @Autowired
    public JmsReceiver(SimpMessagingTemplate websocket) {
        this.websocket = websocket;
    }

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(JmsMessage jmsMessage) {
        logger.info("Received jms message <" + jmsMessage + ">");

        websocket.convertAndSend("/topic/greetings", new WebsocketMessage(jmsMessage.getBody()));
        logger.info("Send websocket message <" + jmsMessage.getBody() + ">");
    }

}