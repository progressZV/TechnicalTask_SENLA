package com.senla.console;

import java.util.Scanner;

public class MenuController {
    private final Builder builder;
    private final Navigator navigator;

    public MenuController(Builder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
    }

    public void run() {
        builder.buildMenu(); // builds main menu and sub-menus, creates actions
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        try {
            Scanner in = new Scanner(System.in);
            int choice;
            while (true) { // waits for user input, never stops until exit action is called or error occurs
                int menuLength = navigator.getCurrentMenu().getMenuItems().length;
                do {
                    while(!in.hasNextInt()) {
                        in.next();
                        System.out.println("Invalid input.");
                    }
                    choice = in.nextInt();
                    if(choice <= 0 || choice > menuLength){
                        System.out.println("Invalid input.");
                    }
                } while(choice <= 0 || choice > menuLength);
                navigator.navigate(choice);
            }
        } // solve it
        catch (Exception e) { /// you can use try-catch inside the 'while' loop, not to terminate process in case of error
            System.out.println("Please choose correctly");
            e.printStackTrace();
        }

    }
}
