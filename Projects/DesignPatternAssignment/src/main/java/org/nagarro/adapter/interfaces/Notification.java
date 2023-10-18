package org.nagarro.adapter.interfaces;

public interface Notification {
    void send(String subject, String message, String channel);
}
