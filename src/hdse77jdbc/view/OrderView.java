package hdse77jdbc.view;

import hdse77jdbc.controller.OrderController;
import hdse77jdbc.model.Order;
import hdse77jdbc.model.OrderDetials;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    
    private static final Scanner scanner = new Scanner(System.in);
    static OrderController orderController = new OrderController();
    
    public void placeOrderForm() {
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
   
    public void viewAllOrdersUI() {
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
    
    public void loadOrderMenu() {
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
}
