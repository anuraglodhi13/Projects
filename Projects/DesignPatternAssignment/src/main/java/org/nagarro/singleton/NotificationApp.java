package org.nagarro.singleton;

public class NotificationApp {
    private static NotificationApp instance;
    public static synchronized NotificationApp getInstance() {
        if (instance == null) {
            instance = new NotificationApp();
        }
        return instance;
    }


    public void sendNotification(Notification channel, String subject, String message) {
        channel.send(subject, message);
    }
}
