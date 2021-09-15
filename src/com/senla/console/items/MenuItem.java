package com.senla.console.items;

import com.senla.console.Menu;
import com.senla.console.actions.IAction;

public class MenuItem {

    private final String title;
    private final IAction action;
    private final Menu nextMenu;


    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }


    public void doAction() {
        getAction().execute();
    }

    public String getTitle() {
        return title;
    }

    public IAction getAction() {
        return action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

}
