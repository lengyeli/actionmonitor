package com.lengyeli.actionmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * On Application startup initialize db Triggers and Corresponding Java handlers.
 * Created by ilengyel on 2017. 10. 08..
 */
@Component
public class TriggerInitializer {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TriggerInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void createTriggers() {
        jdbcTemplate.execute("CREATE TRIGGER AFTER_INSERT AFTER INSERT ON SAMPLE_DATA FOR EACH ROW CALL \"com.lengyeli.actionmonitor.trigger.InsertTrigger\"");
        jdbcTemplate.execute("CREATE TRIGGER AFTER_UPDATE AFTER UPDATE ON SAMPLE_DATA FOR EACH ROW CALL \"com.lengyeli.actionmonitor.trigger.UpdateTrigger\"");
        jdbcTemplate.execute("CREATE TRIGGER AFTER_DELETE AFTER DELETE ON SAMPLE_DATA FOR EACH ROW CALL \"com.lengyeli.actionmonitor.trigger.DeleteTrigger\"");
    }
}