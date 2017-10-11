package com.lengyeli.actionmonitor.model;

/**
 * WebSocket message holder
 * Created by ilengyel on 2017. 10. 09..
 */
public class WebsocketMessage {

    private String content;

    public WebsocketMessage() {
    }

    public WebsocketMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
