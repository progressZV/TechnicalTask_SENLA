package com.senla.application;

import com.senla.console.Builder;
import com.senla.console.Menu;
import com.senla.console.MenuController;
import com.senla.console.Navigator;
import com.senla.console.items.MenuItem;

public class Application {
    public static void main(String[] args) {
        MenuController menu = new MenuController(new Builder(), new Navigator());
        menu.run();

    }
}
