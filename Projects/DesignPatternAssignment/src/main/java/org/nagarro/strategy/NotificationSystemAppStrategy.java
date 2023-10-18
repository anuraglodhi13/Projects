package org.nagarro.strategy;

import org.nagarro.model.User;
import org.nagarro.strategy.EmailSender;
import org.nagarro.strategy.Notification;
import org.nagarro.strategy.SmsSender;
import org.nagarro.utils.PopulateUsers;

import java.util.List;
import java.util.Scanner;

public class NotificationSystemAppStrategy {
    public void main () {
        System.out.println("Implementing using Strategy Design Pattern");
        System.out.println("Choose the channel to use");
        System.out.println("1 for Email");
        System.out.println("2 for SMS");
        PopulateUsers populateUsers = new PopulateUsers();
        List<User> users = populateUsers.instantiateUser();
        Scanner scanner = new Scanner(System.in);
        Integer channelIndex = scanner.nextInt();
        switch(channelIndex) {
            case 1: { // send Email
                NotificationApp notification = new NotificationApp(new EmailSender());
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter Subject for Email");
                String subject = scanner1.nextLine();
                System.out.println("Enter Body for Email");
                String body = scanner1.nextLine();
                for(User user : users) {
                    if(user.getIsSubscribed()) {
                        notification.executeNotification(subject, body);
                        System.out.println("Sent to user "+ user.getName());
                    }
                }
                break;
            }
            case 2: { // send Sms
                NotificationApp notification = new NotificationApp(new SmsSender());
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter Subject for SMS");
                String subject = scanner1.nextLine();
                System.out.println("Enter Body for SMS");
                String body = scanner1.nextLine();
                for(User user : users) {
                    if(user.getIsSubscribed()) {
                        notification.executeNotification(subject, body);
                        System.out.println("Sent to user "+ user.getName());
                    }
                }
                break;
            }
        }
    }
}
