package org.nagarro.factory;

public class EmailSender implements Notification{
    @Override
    public void send(String subject, String message) {
        System.out.println("Email Subject " + subject + ". Email Body " + message);
    }
}
