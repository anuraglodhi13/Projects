package org.nagarro.adapter.concreteclasses;

import org.nagarro.adapter.interfaces.AdvanceNotification;

// concrete class to implement email sender
public class EmailSender implements AdvanceNotification {
    @Override
    public void sendSms(String subject, String message) {

    }

    @Override
    public void sendEmail(String subject, String message) {
        System.out.println("Email Subject " + subject + ". Email Body " + message);
    }
}
