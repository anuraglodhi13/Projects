package org.nagarro.adapter;

import org.nagarro.adapter.concreteclasses.EmailSender;
import org.nagarro.adapter.concreteclasses.SmsSender;
import org.nagarro.adapter.interfaces.AdvanceNotification;
import org.nagarro.adapter.interfaces.Notification;

//creating adapter class implementing notification
public class NotificationAdapter implements Notification {
    AdvanceNotification advanceNotification;
    public NotificationAdapter(String channel) {
        if(channel.equals("Email")) {
            advanceNotification = new EmailSender();
        }
        else if(channel.equals("SMS")) {
            advanceNotification = new SmsSender();
        }
    }
    @Override
    public void send(String subject, String message, String channel) {
        if(channel.equals("Email")) {
            advanceNotification.sendEmail(subject,message);
        }
        else if(channel.equals("SMS")) {
            advanceNotification.sendSms(subject,message);
        }
    }
}
