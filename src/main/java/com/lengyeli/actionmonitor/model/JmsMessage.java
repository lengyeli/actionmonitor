package com.lengyeli.actionmonitor.model;

/**
 * JMS message holder
 * Created by ilengyel on 2017. 10. 09..
 */
public class JmsMessage {

    private String body;

    public JmsMessage() {
    }

    public JmsMessage(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("JmsMessage{body=%s}", getBody());
    }

}
