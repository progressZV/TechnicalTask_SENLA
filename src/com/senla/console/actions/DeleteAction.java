package com.senla.console.actions;

import java.util.Scanner;

public class DeleteAction implements IAction {
    @Override
    public void execute() {
        System.out.println("Enter object name to delete.");
        String objectName = new Scanner(System.in).nextLine();

        System.out.printf("Executed delete action for object %s" + System.lineSeparator(), objectName);
    }
}
