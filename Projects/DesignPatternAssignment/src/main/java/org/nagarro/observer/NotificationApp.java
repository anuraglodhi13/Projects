package org.nagarro.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationApp {
    private Map<String, Notification> observers = new HashMap<>();
    public void addObservers(String channel, Notification notification) {
        observers.put(channel, notification);
    }

    public void removeObservers(String channel) {
        observers.remove(channel);
    }

    public void notifyObservers(String subject, String message,String channel) {
        Notification notification = observers.get(channel);
        if (notification != null) {
            notification.send(subject, message);
        } else {
            System.out.println("Invalid channel: " + channel);
        }
    }
}
