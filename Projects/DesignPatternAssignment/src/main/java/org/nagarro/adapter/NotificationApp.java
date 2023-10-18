package org.nagarro.adapter;

import org.nagarro.adapter.interfaces.Notification;

public class NotificationApp implements Notification {
    NotificationAdapter notificationAdapter;
    @Override
    public void send(String subject, String message, String channel) {
            notificationAdapter = new NotificationAdapter(channel);
            notificationAdapter.send(subject,message,channel);
    }
}
