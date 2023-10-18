package org.nagarro;

import org.nagarro.adapter.NotificationSystemAppAdapter;
import org.nagarro.facade.NotificationSystemAppFacade;
import org.nagarro.factory.NotificationSystemAppFactory;
import org.nagarro.observer.NotificationSystemAppObserver;
import org.nagarro.singleton.NotificationSystemAppSingleton;
import org.nagarro.strategy.NotificationSystemAppStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To Design Pattern Assignment !!!");
        System.out.println("Choose the number for which the design needed to be implemented");
        System.out.println("1 for Adapter");
        System.out.println("2 for Facade");
        System.out.println("3 for Factory");
        System.out.println("4 for Observer");
        System.out.println("5 for Singleton");
        System.out.println("6 for Strategy");
        Scanner scanner = new Scanner(System.in);
        Integer designPatternIndex = scanner.nextInt();
        switch (designPatternIndex) {
            case 1 : {
                NotificationSystemAppAdapter applicationAdapter = new NotificationSystemAppAdapter();
                applicationAdapter.main();
                break;
            }
            case 2 : {
                NotificationSystemAppFacade notificationSystemAppFacade = new NotificationSystemAppFacade();
                notificationSystemAppFacade.main();
                break;
            }
            case 3 : {
                NotificationSystemAppFactory notificationSystemAppFactory = new NotificationSystemAppFactory();
                notificationSystemAppFactory.main();
                break;
            }
            case 4 : {
                NotificationSystemAppObserver notificationSystemAppObserver = new NotificationSystemAppObserver();
                notificationSystemAppObserver.main();
                break;
            }
            case 5 : {
                NotificationSystemAppSingleton notificationSystemAppSingleton = new NotificationSystemAppSingleton();
                notificationSystemAppSingleton.main();
                break;
            }
            case 6 : {
                NotificationSystemAppStrategy notificationSystemAppStrategy = new NotificationSystemAppStrategy();
                notificationSystemAppStrategy.main();
                break;
            }
        }

    }
}