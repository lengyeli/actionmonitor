package com.lengyeli.actionmonitor.trigger;

import org.h2.tools.TriggerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ilengyel on 2017. 10. 08..
 */
@Component
public class BaseActionTrigger {

    //    static String websocketTopic = "/topic/triggerEvents";
    static String websocketTopic = "jms.topic.test";

    static JmsTemplate jmsTemplate;

    @Autowired
    public BaseActionTrigger(JmsTemplate webSocket) {
        BaseActionTrigger.jmsTemplate = webSocket;
    }
}
