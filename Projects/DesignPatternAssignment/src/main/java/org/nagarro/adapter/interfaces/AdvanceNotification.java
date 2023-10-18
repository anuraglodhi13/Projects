package org.nagarro.adapter.interfaces;

public interface AdvanceNotification {
    void sendSms(String subject, String message);
    void sendEmail(String subject, String message);

}
