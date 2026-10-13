package hdse77jdbc.view;

import hdse77jdbc.controller.CustomerController;
import hdse77jdbc.model.Customer;
import java.util.List;
import java.util.Scanner;

public class CustomerView {
    
    private static final Scanner scanner = new Scanner(System.in);
    static CustomerController customerController = new CustomerController();
    
    public void addCustomerForm() {
       System.out.println("\n======================================================================");
       System.out.println("                        ADD NEW CUSTOMER FORM");
       System.out.println("======================================================================");
       System.out.print("  Enter Customer ID      : ");
       String id = scanner.nextLine().trim();

       System.out.print("  Enter Customer Name    : ");
       String name = scanner.nextLine().trim();

       System.out.print("  Enter Customer Address : ");
       String address = scanner.nextLine().trim();

       System.out.print("  Enter Customer Phone   : ");
       String phone = scanner.nextLine().trim();

       System.out.print("  Enter Customer Email   : ");
       String email = scanner.nextLine().trim();

       System.out.println("======================================================================");

       customerController.addCustomer(new Customer(id, name, address, phone, email));
   }

   public void updateCustomerForm() {
       System.out.println("\n======================================================================");
       System.out.println("                        UPDATE CUSTOMER FORM");
       System.out.println("======================================================================");
       System.out.print("  Enter Customer ID to Update : ");
       String id = scanner.nextLine().trim();

       System.out.print("  Enter New Customer Name    : ");
       String name = scanner.nextLine().trim();

       System.out.print("  Enter New Customer Address : ");
       String address = scanner.nextLine().trim();

       System.out.print("  Enter New Customer Phone   : ");
       String phone = scanner.nextLine().trim();

       System.out.print("  Enter New Customer Email   : ");
       String email = scanner.nextLine().trim();

       System.out.println("======================================================================");
       customerController.updateCustomer(new Customer(id, name, address, phone, email));
   }

   public static void searchCustomerForm() {
       System.out.println("\n======================================================================");
       System.out.println("                        SEARCH CUSTOMER FORM");
       System.out.println("======================================================================");
       System.out.print("  Enter Customer ID to Search : ");
       String id = scanner.nextLine().trim();

       System.out.println("======================================================================");
       customerController.searchCustomer(id);
   }

   public void viewAllCustomersUI() {

       List<Customer> customerList = customerController.getAllCustomers();

       System.out.println("\n======================================================================");
       System.out.println("                        VIEW ALL CUSTOMERS");
       System.out.println("======================================================================");
       System.out.println(
               "┌────────┬────────────────────┬─────────────────────────────┬─────────────┬───────────────────────────┐");
       System.out.printf("│ %-6s │ %-18s │ %-27s │ %-11s │ %-25s │%n", "ID", "Name", "Address", "Phone", "Email");
       System.out.println(
               "├────────┼────────────────────┼─────────────────────────────┼─────────────┼───────────────────────────┤");

       for (Customer customer : customerList) {
           System.out.printf("│ %-6s │ %-18s │ %-27s │ %-11s │ %-25s │%n", customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone(), customer.getEmail());
       }

       System.out.println(
               "└────────┴────────────────────┴─────────────────────────────┴─────────────┴───────────────────────────┘");
       System.out.println("======================================================================");
   }

   public void deleteCustomerForm() {
       System.out.println("\n======================================================================");
       System.out.println("                        DELETE CUSTOMER FORM");
       System.out.println("======================================================================");
       System.out.print("  Enter Customer ID to Delete : ");
       String id = scanner.nextLine().trim();

       System.out.print("  Are you sure you want to delete? (Y/N): ");
       String confirm = scanner.nextLine().trim();

       System.out.println("======================================================================");
       if(confirm.equals("Y")) {
           customerController.deleteCustomer(id);
       } else {
           System.out.println("Invalid Input!");
       }
   }
   
   public void loadCustomerMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n======================================================================");
            System.out.println("                        CUSTOMER MANAGEMENT MENU");
            System.out.println("======================================================================");

            System.out.println("  1. Add Customer");
            System.out.println("  2. Update Customer");
            System.out.println("  3. Search Customer");
            System.out.println("  4. View All Customers");
            System.out.println("  5. Delete Customer");
            System.out.println("  0. Back");

            System.out.println("======================================================================");
            System.out.print("  Select Option [0-5]: ");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    addCustomerForm();
                    break;

                case "2":
                    updateCustomerForm();
                    break;

                case "3":
                    searchCustomerForm();
                    break;

                case "4":
                    viewAllCustomersUI();
                    break;

                case "5":
                    deleteCustomerForm();
                    break;

                case "0":
                    running = false;
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
