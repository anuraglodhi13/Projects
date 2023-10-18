package org.nagarro.adapter.concreteclasses;

import org.nagarro.adapter.interfaces.AdvanceNotification;

// concrete class to implement sms sender
public class SmsSender implements AdvanceNotification {
    @Override
    public void sendSms(String subject, String message) {
        System.out.println("SMS Subject " + subject + ". SMS Body " + message);

    }

    @Override
    public void sendEmail(String subject, String message) {

    }
}
