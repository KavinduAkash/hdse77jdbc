package hdse77jdbc.view;

import java.util.Scanner;

public class MainMenuView {
    private static final Scanner scanner = new Scanner(System.in);
    static CustomerView customerView = new CustomerView();
    static ItemView itemView = new ItemView();
    static OrderView orderView = new OrderView();
    
    public void loadMainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n======================================================================");
            System.out.println("                              MAIN MENU");
            System.out.println("======================================================================");

            System.out.println("  1. Customer Management");
            System.out.println("  2. Item Management");
            System.out.println("  3. Place Order");
            System.out.println("  0. Exit");

            System.out.println("======================================================================");
            System.out.print("  Select Option [0-3]: ");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    customerView.loadCustomerMenu();
                    break;

                case "2":
                    itemView.loadItemMenu();
                    break;

                case "3":
                    orderView.loadOrderMenu();
                    break;

                case "0":
                    running = false;
                    System.out.println("\nApplication Exited Successfully.");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
