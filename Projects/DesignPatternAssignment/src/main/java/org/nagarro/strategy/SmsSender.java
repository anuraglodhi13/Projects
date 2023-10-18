package org.nagarro.strategy;

public class SmsSender implements Notification{
    @Override
    public void send(String subject, String message) {
        System.out.println("Email Subject " + subject + ". Email Body " + message);
    }
}
