package com.lengyeli.actionmonitor.trigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Base Trigger for injecting jmsTempalte for Triggers
 * Created by ilengyel on 2017. 10. 08..
 */
@Component
public class BaseActionTrigger {

    static JmsTemplate jmsTemplate;

    @Autowired
    public BaseActionTrigger(JmsTemplate webSocket) {
        BaseActionTrigger.jmsTemplate = webSocket;
    }
}
