package com.senla.application;

import com.senla.console.Builder;
import com.senla.console.MenuController;
import com.senla.console.Navigator;

public class Application {
    public static void main(String[] args) {
        MenuController menu = new MenuController(new Builder(), new Navigator());
        menu.run();

    }
}
