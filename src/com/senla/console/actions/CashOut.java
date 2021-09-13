package com.senla.console.actions;

import com.senla.facade.ControlService;

public class CashOut implements IAction {
    @Override
    public void execute() {
        ControlService controlService = new ControlService();
        System.out.println("Cash out.");
        controlService.cashOut();
    }
}
