package com.senla.console;


import java.util.InputMismatchException;
import java.util.Scanner;

import com.senla.console.items.MenuItem;

public class Navigator {
    private Menu currentMenu;

    public void printMenu() {
        System.out.println(System.lineSeparator() + getCurrentMenu().getName());
        int itemOrdinalNumber = 0;
        for (MenuItem item : getCurrentMenu().getMenuItems()) {
            itemOrdinalNumber++; // array index + 1, not to confuse user with 0
            System.out.println(itemOrdinalNumber + " " + item.getTitle());
        }
    }

    public void navigate(Integer index) {
        // validateUserInput method can be added
        try {
            Scanner scanner = new Scanner(System.in);
            MenuItem menuItem = currentMenu.getMenuItems()[index - 1];
            if (menuItem.getAction() != null) {
                menuItem.doAction();
                System.out.println("Press any button(s) to continue");
                scanner.nextLine();
            }
            clearConsole();
            setCurrentMenu(menuItem.getNextMenu());
        } catch (InputMismatchException e){
            System.out.println("Press enter to continue");
        }
        printMenu();
    }

    private void clearConsole() { // optional
        // here you can try to clean previous console output and print new menu from the
        // start
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
