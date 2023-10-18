package org.nagarro.observer;

import org.nagarro.observer.EmailSender;
import org.nagarro.observer.Notification;
import org.nagarro.observer.SmsSender;
import org.nagarro.model.User;
import org.nagarro.utils.PopulateUsers;

import java.util.List;
import java.util.Scanner;

public class NotificationSystemAppObserver {
    public void main () {

        System.out.println("Implementing using Observer Design Pattern");
        System.out.println("Choose the channel to use");
        System.out.println("1 for Email");
        System.out.println("2 for SMS");
        PopulateUsers populateUsers = new PopulateUsers();
        List<User> users = populateUsers.instantiateUser();
        // creating object for notification app class
        NotificationApp notificationApp = new NotificationApp();

        // creating objects of observers
        Notification emailObserver = new EmailSender();
        Notification smsObserver = new SmsSender();

        // adding observer
        notificationApp.addObservers("Email",emailObserver);
        notificationApp.addObservers("Sms",smsObserver);

        Scanner scanner = new Scanner(System.in);
        Integer channelIndex = scanner.nextInt();
        switch(channelIndex) {
            case 1: { // send Email
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter Subject for Email");
                String subject = scanner1.nextLine();
                System.out.println("Enter Body for Email");
                String body = scanner1.nextLine();
                for (User user : users) {
                    if (user.getIsSubscribed()) {
                        notificationApp.notifyObservers(subject, body, "Email");
                        System.out.println("Sent to user " + user.getName());
                    }
                }
                break;
            }
            case 2: { // send Sms
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter Subject for SMS");
                String subject = scanner1.nextLine();
                System.out.println("Enter Body for SMS");
                String body = scanner1.nextLine();
                for (User user : users) {
                    if (user.getIsSubscribed()) {
                        notificationApp.notifyObservers(subject, body, "Sms");
                        System.out.println("Sent to user " + user.getName());
                    }
                }
                break;
            }
        }
    }
}
