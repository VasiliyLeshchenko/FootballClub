package com.footballclubapplication.www.event;

public class SendStatisticsEvent {
    private String message;
    public SendStatisticsEvent(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
}
