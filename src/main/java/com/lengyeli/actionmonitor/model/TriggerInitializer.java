package com.lengyeli.actionmonitor.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * Created by ilengyel on 2017. 10. 08..
 */
@Component
public class TriggerInitializer {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TriggerInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //TODO ezt megnézni hogy lehet jobban
//        createTriggers();
    }

    @PostConstruct
    public void createTriggers() {
        jdbcTemplate.execute("CREATE TRIGGER AFTER_INSERT AFTER INSERT ON SAMPLE_DATA FOR EACH ROW CALL \"com.lengyeli.actionmonitor.trigger.InsertTrigger\"");
        jdbcTemplate.execute("CREATE TRIGGER AFTER_UPDATE AFTER UPDATE ON SAMPLE_DATA FOR EACH ROW CALL \"com.lengyeli.actionmonitor.trigger.UpdateTrigger\"");
        jdbcTemplate.execute("CREATE TRIGGER AFTER_DELETE AFTER DELETE ON SAMPLE_DATA FOR EACH ROW CALL \"com.lengyeli.actionmonitor.trigger.DeleteTrigger\"");
    }
}