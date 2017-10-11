package com.lengyeli.actionmonitor.trigger;

import com.lengyeli.actionmonitor.model.JmsMessage;
import org.h2.tools.TriggerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database update event handler
 * <p>
 * Created by ilengyel on 2017. 10. 09..
 */
public class UpdateTrigger extends TriggerAdapter {
    private static Logger logger = LoggerFactory.getLogger(UpdateTrigger.class);

    @Override
    public void fire(Connection connection, ResultSet oldResultSet, ResultSet newResultSet) throws SQLException {
        String message = ResultSetUtil.createChangeResultMessage(newResultSet) + " was updated";
        logger.info(message);

        BaseActionTrigger.jmsTemplate.convertAndSend("mailbox", new JmsMessage(message));
    }
}
