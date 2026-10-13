package hdse77jdbc;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;

// ========== Models ==============
import hdse77jdbc.model.Customer;
import hdse77jdbc.model.Item;
import hdse77jdbc.model.OrderDetials;
import hdse77jdbc.model.Order;

// ========== Controller ==============
import hdse77jdbc.controller.CustomerController;
import hdse77jdbc.controller.ItemController;
import hdse77jdbc.controller.OrderController;


import hdse77jdbc.view.CustomerView;

public class Main {
   private static final Scanner scanner = new Scanner(System.in);

   static CustomerController customerController = new CustomerController();
   static ItemController itemController = new ItemController();
   static OrderController orderController = new OrderController();
   
   static CustomerView customerView = new CustomerView();
   
   

   // ======================= Item UI Handling ==========================

    public static void addItemForm() {
        System.out.println("\n======================================================================");
        System.out.println("                           ADD NEW ITEM FORM");
        System.out.println("======================================================================");

        System.out.print("  Enter Item ID        : ");
        String id = scanner.nextLine().trim();

        System.out.print("  Enter Item Name      : ");
        String name = scanner.nextLine().trim();

        System.out.print("  Enter Unit Price     : ");
        String unitPriceText = scanner.nextLine().trim();

        System.out.print("  Enter Quantity       : ");
        String qtyText = scanner.nextLine().trim();

        System.out.println("======================================================================");

        try {
            double unitPrice = Double.parseDouble(unitPriceText);
            int qty = Integer.parseInt(qtyText);

            itemController.addItem(new Item(id, name, unitPrice, qty));
        } catch (NumberFormatException e) {
            System.out.println("Invalid Unit Price or Quantity!");
        }
    }

    public static void updateItemForm() {
        System.out.println("\n======================================================================");
        System.out.println("                           UPDATE ITEM FORM");
        System.out.println("======================================================================");

        System.out.print("  Enter Item ID to Update : ");
        String id = scanner.nextLine().trim();

        System.out.print("  Enter New Item Name     : ");
        String name = scanner.nextLine().trim();

        System.out.print("  Enter New Unit Price    : ");
        String unitPriceText = scanner.nextLine().trim();

        System.out.print("  Enter New Quantity      : ");
        String qtyText = scanner.nextLine().trim();

        System.out.println("======================================================================");

        try {
            double unitPrice = Double.parseDouble(unitPriceText);
            int qty = Integer.parseInt(qtyText);

            itemController.updateItem(new Item(id, name, unitPrice, qty));
        } catch (NumberFormatException e) {
            System.out.println("Invalid Unit Price or Quantity!");
        }
    }

    public static void searchItemForm() {
        System.out.println("\n======================================================================");
        System.out.println("                           SEARCH ITEM FORM");
        System.out.println("======================================================================");

        System.out.print("  Enter Item ID to Search : ");
        String id = scanner.nextLine().trim();

        System.out.println("======================================================================");
        itemController.searchItem(id);
    }

    public static void viewAllItemsUI() {

        List<Item> itemList = itemController.getAllItems();

        System.out.println("\n======================================================================");
        System.out.println("                           VIEW ALL ITEMS");
        System.out.println("======================================================================");

        System.out.println(
                "┌────────┬────────────────────────────┬────────────────┬────────────┐"
        );

        System.out.printf(
                "│ %-6s │ %-26s │ %-14s │ %-10s │%n",
                "ID", "Name", "Unit Price", "Quantity"
        );

        System.out.println(
                "├────────┼────────────────────────────┼────────────────┼────────────┤"
        );

        for (Item item : itemList) {
            System.out.printf(
                    "│ %-6s │ %-26s │ %-14.2f │ %-10d │%n",
                    item.getId(), item.getName(), item.getUnitPrice(), item.getQty()
            );
        }

        System.out.println(
                "└────────┴────────────────────────────┴────────────────┴────────────┘"
        );

        System.out.println("======================================================================");
    }

    public static void deleteItemForm() {
        System.out.println("\n======================================================================");
        System.out.println("                           DELETE ITEM FORM");
        System.out.println("======================================================================");

        System.out.print("  Enter Item ID to Delete : ");
        String id = scanner.nextLine().trim();

        System.out.print("  Are you sure you want to delete? (Y/N): ");
        String confirm = scanner.nextLine().trim();

        System.out.println("======================================================================");
        if(confirm.equals("Y")) {
            itemController.deleteItem(id);
        } else {
            System.out.println("Invalid Input!");
        }
    }

    // ======================= Order UI Handling ========================= 
    
   public static void placeOrderForm() {
        System.out.println("\n======================================================================");
        System.out.println("                           PLACE ORDER FORM");
        System.out.println("======================================================================");
 
        System.out.print("  Enter Order ID      : ");
        String orderId = scanner.nextLine().trim();
 
        System.out.print("  Enter Customer ID   : ");
        String customerId = scanner.nextLine().trim();
 
        System.out.println("----------------------------------------------------------------------");
 
        List<OrderDetials> orderDetialList = new ArrayList<OrderDetials>();
        
        while (true) {
            System.out.print("  Enter Item ID (press Enter to finish) : ");
            String itemId = scanner.nextLine().trim();
 
            if (itemId.isEmpty()) {
                break;
            }
 
            System.out.print("  Enter Quantity                        : ");
            String qty = scanner.nextLine().trim();
            
            OrderDetials od = new OrderDetials();
            od.setItemId(itemId);
            od.setQty(Integer.parseInt(qty));
            
            orderDetialList.add(od);
        }
 
        System.out.println("----------------------------------------------------------------------");
        System.out.println("  Order ID    : " + orderId);
        System.out.println("  Customer ID : " + customerId);
 
        System.out.println("┌────────┬────────────┐");
        System.out.printf("│ %-6s │ %-10s │%n", "Item", "Quantity");
        System.out.println("├────────┼────────────┤");
 
        for (int i = 0; i < orderDetialList.size(); i++) {
            System.out.printf("│ %-6s │ %-10s │%n", orderDetialList.get(i).getItemId(), orderDetialList.get(i).getQty());
        }
 
        System.out.println("└────────┴────────────┘");
 
        System.out.print("  Confirm order? (Y/N): ");
        String confirm = scanner.nextLine().trim();
 
        System.out.println("======================================================================");
        
        if(confirm.equals("Y")) {
            
            
            Order order = new Order();
            
            order.setOrderId(orderId);
            order.setDate(new Date());
            order.setCustomerId(customerId);
            order.setOrderDetailsList(orderDetialList);
            
            orderController.placeOrder(order);
            
        }
        
    }
   
    public static void viewAllOrdersUI() {
        System.out.println("\n======================================================================");
        System.out.println("                           VIEW ALL ORDERS");
        System.out.println("======================================================================");
 
        System.out.println(
                "┌────────┬──────────────┬─────────────┬────────────────┐"
        );
 
        System.out.printf(
                "│ %-6s │ %-12s │ %-11s │ %-14s │%n",
                "ID", "Date", "Customer ID", "Total"
        );
 
        System.out.println(
                "├────────┼──────────────┼─────────────┼────────────────┤"
        );
 
        System.out.println(
                "└────────┴──────────────┴─────────────┴────────────────┘"
        );
 
        System.out.println("======================================================================");
    }
 
    // ======================= ORDER MENU ==========================
 
    public static void loadOrderMenu() {
        boolean running = true;
 
        while (running) {
            System.out.println("\n======================================================================");
            System.out.println("                           ORDER MANAGEMENT MENU");
            System.out.println("======================================================================");
 
            System.out.println("  1. Place New Order");
            System.out.println("  2. View All Orders");
            System.out.println("  0. Back");
 
            System.out.println("======================================================================");
            System.out.print("  Select Option [0-2]: ");
 
            String option = scanner.nextLine().trim();
 
            switch (option) {
                case "1":
                    placeOrderForm();
                    break;
 
                case "2":
                    viewAllOrdersUI();
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

    // ======================= ITEM MENU ==========================

    public static void loadItemMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n======================================================================");
            System.out.println("                           ITEM MANAGEMENT MENU");
            System.out.println("======================================================================");

            System.out.println("  1. Add Item");
            System.out.println("  2. Update Item");
            System.out.println("  3. Search Item");
            System.out.println("  4. View All Items");
            System.out.println("  5. Delete Item");
            System.out.println("  0. Back");

            System.out.println("======================================================================");
            System.out.print("  Select Option [0-5]: ");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    addItemForm();
                    break;

                case "2":
                    updateItemForm();
                    break;

                case "3":
                    searchItemForm();
                    break;

                case "4":
                    viewAllItemsUI();
                    break;

                case "5":
                    deleteItemForm();
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

    // ======================= CUSTOMER MENU ==========================

    

    // ======================= MAIN MENU ==========================

    public static void loadMainMenu() {
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
                    loadItemMenu();
                    break;

                case "3":
                    loadOrderMenu();
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


    public static void main(String[] args)  {
        loadMainMenu();
    }
}