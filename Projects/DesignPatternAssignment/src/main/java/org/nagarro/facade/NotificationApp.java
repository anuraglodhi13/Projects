package org.nagarro.facade;

public class NotificationApp {
    private final Notification email;
    private final Notification sms;

    NotificationApp() {
        email = new EmailSender();
        sms = new SmsSender();
    }

    public void sendSms (String subject, String body) {
        sms.send(subject,body);
    }

    public void sendEmail (String subject, String body) {
        email.send(subject,body);
    }
}
