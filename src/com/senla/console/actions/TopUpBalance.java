package com.senla.console.actions;

import com.senla.facade.ControlService;

public class TopUpBalance implements IAction {
    @Override
    public void execute() {
        ControlService controlService = new ControlService();
        System.out.println("Checking balance.");
        controlService.topUpBalance();
    }
}