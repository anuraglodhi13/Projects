package org.nagarro.adapter;

import org.nagarro.model.User;
import org.nagarro.utils.PopulateUsers;

import java.util.List;
import java.util.Scanner;

public class NotificationSystemAppAdapter {
    public void main () {
        System.out.println("Implementing using Adapter Design Pattern");
        System.out.println("Choose the channel to use");
        System.out.println("1 for Email");
        System.out.println("2 for SMS");
        NotificationApp notification = new NotificationApp();
        PopulateUsers populateUsers = new PopulateUsers();
        List <User> users = populateUsers.instantiateUser();
        Scanner scanner = new Scanner(System.in);
        Integer channelIndex = scanner.nextInt();
        switch(channelIndex) {
            case 1: { // send Email
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter Subject for Email");
                String subject = scanner1.nextLine();
                System.out.println("Enter Body for Email");
                String body = scanner1.nextLine();
                for(User user : users) {
                    if(user.getIsSubscribed()) {
                        notification.send(subject, body,"Email");
                        System.out.println("Sent to user "+ user.getName());
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
                for(User user : users) {
                    if(user.getIsSubscribed()) {
                        notification.send(subject, body,"SMS");
                        System.out.println("Sent to user "+ user.getName());
                    }
                }
                break;
            }
        }
    }
}
