package com.senla.console;

import com.senla.console.actions.*;
import com.senla.console.items.*;

public class Builder {
    private  Menu rootMenu;

    public void buildMenu() {

        Menu rootMenu = new Menu();
        Menu newMenu = new Menu();
        rootMenu.setName("Root Menu Options:");
        newMenu.setName("New menu");

        MenuItem checkInput = new MenuItem("Entering card data.", new CheckCardDataInput(), newMenu);
        MenuItem checkBalance = new MenuItem("Check balance.", new CheckBalance(), newMenu);
        MenuItem cashOut = new MenuItem("Cash out.", new CashOut(), newMenu);
        MenuItem topUpBalance = new MenuItem("Put a balance.", new TopUpBalance(), newMenu);
        MenuItem exit = new MenuItem("Exit", new ExitAction(), newMenu);

        rootMenu.setMenuItems(new MenuItem[] { checkInput, exit });
        newMenu.setMenuItems(new MenuItem[] { checkBalance, cashOut, topUpBalance, exit });
        setRootMenu(rootMenu);
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void setRootMenu(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

}
