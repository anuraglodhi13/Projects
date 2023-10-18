package org.nagarro.singleton;

import org.nagarro.singleton.NotificationApp;
import org.nagarro.singleton.EmailSender;
import org.nagarro.singleton.Notification;
import org.nagarro.model.User;
import org.nagarro.utils.PopulateUsers;

import java.util.List;
import java.util.Scanner;

public class NotificationSystemAppSingleton {
    public void main () {
        System.out.println("Implementing using Singleton Design Pattern");
        System.out.println("Choose the channel to use");
        System.out.println("1 for Email");
        System.out.println("2 for SMS");
        NotificationApp notification = NotificationApp.getInstance(); // getting singleton instance for this object
        PopulateUsers populateUsers = new PopulateUsers();
        List<User> users = populateUsers.instantiateUser();
        Scanner scanner = new Scanner(System.in);
        Integer channelIndex = scanner.nextInt();
        switch(channelIndex) {
            case 1: { // send Email
                Notification emailSender = new EmailSender();
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter Subject for Email");
                String subject = scanner1.nextLine();
                System.out.println("Enter Body for Email");
                String body = scanner1.nextLine();
                for(User user : users) {
                    if(user.getIsSubscribed()) {
                        notification.sendNotification(emailSender,subject, body);
                        System.out.println("Sent to user "+ user.getName());
                    }
                }
                break;
            }
            case 2: { // send Sms
                Notification smsSender = new SmsSender();
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter Subject for SMS");
                String subject = scanner1.nextLine();
                System.out.println("Enter Body for SMS");
                String body = scanner1.nextLine();
                for(User user : users) {
                    if(user.getIsSubscribed()) {
                        notification.sendNotification(smsSender,subject, body);
                        System.out.println("Sent to user "+ user.getName());
                    }
                }
                break;
            }
        }
    }
}
