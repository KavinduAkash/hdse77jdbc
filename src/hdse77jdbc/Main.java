package hdse77jdbc;

import hdse77jdbc.view.MainMenuView;

public class Main {   
    static MainMenuView mainMenuView = new MainMenuView(); 

    public static void main(String[] args)  {
        mainMenuView.loadMainMenu();
    }
}