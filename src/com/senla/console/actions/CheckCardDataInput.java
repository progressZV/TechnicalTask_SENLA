package com.senla.console.actions;

import com.senla.facade.ControlService;

import java.util.Scanner;

public class CheckCardDataInput implements IAction {
    @Override
    public void execute() {
        ControlService controlService = new ControlService();
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter a valid card number " +
                "that matches the template.");
        String validNumber = in.nextLine();

        System.out.println("Please enter the correct pin code to access your account.");
        String pinCode = in.nextLine();

        controlService.checkCard(validNumber, pinCode);
    }

}
