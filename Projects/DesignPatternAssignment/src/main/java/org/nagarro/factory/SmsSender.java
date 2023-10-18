package org.nagarro.factory;

public class SmsSender implements Notification{
    @Override
    public void send(String subject, String message) {
        System.out.println("SMS Subject " + subject + ". SMS Body " + message);
    }
}
