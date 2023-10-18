package org.nagarro.strategy;
// this is the context class that will ask from Notification(strategy)
// interface to ask type of notification
public class NotificationApp {
    private Notification notification;
    public NotificationApp(Notification notification) {
        this.notification = notification;
    }
    public void executeNotification(String subject, String body) {
         notification.send(subject,body);
    }
}
