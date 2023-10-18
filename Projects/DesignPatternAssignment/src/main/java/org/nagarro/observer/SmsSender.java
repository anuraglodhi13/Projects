package org.nagarro.observer;

public class SmsSender implements Notification{
    @Override
    public void send(String subject, String message) {
        System.out.println("SMS Subject " + subject + ". SMS Body " + message);
    }
}
