package com.senla.console.actions;

import com.senla.facade.ControlService;

public class ExitAction implements IAction {
    @Override
    public void execute(){
        ControlService controlService = new ControlService();
        controlService.exitApp();
    }
}
