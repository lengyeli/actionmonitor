package com.lengyeli.actionmonitor.trigger;

import com.lengyeli.actionmonitor.model.JmsMessage;
import org.h2.tools.TriggerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database createSampleData event handler
 * <p>
 * Created by ilengyel on 2017. 10. 08..
 */
@Controller
public class InsertTrigger extends TriggerAdapter {
    private static Logger logger = LoggerFactory.getLogger(InsertTrigger.class);

    @Override
    public void fire(Connection connection, ResultSet oldResultSet, ResultSet newResultSet) throws SQLException {
        String message = ResultSetUtil.createChangeResultMessage(newResultSet) + " was inserted";
        logger.info(message);

        BaseActionTrigger.jmsTemplate.convertAndSend("mailbox", new JmsMessage(message));
    }
}