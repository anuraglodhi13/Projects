package org.nagarro.factory;

public class NotificationFactory {
    public Notification createNotification(String channel){
        if(channel.equals("SMS"))
            return new SmsSender();
        else if(channel.equals("Email"))
            return new EmailSender();
        throw new IllegalArgumentException("Unknown channel "+channel);
    }
}
