/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hdse77jdbc.view;

import hdse77jdbc.controller.ItemController;
import hdse77jdbc.model.Item;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kavinduakash
 */
public class ItemView {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    static ItemController itemController = new ItemController();
    
    public void addItemForm() {
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

    public void updateItemForm() {
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

    public void searchItemForm() {
        System.out.println("\n======================================================================");
        System.out.println("                           SEARCH ITEM FORM");
        System.out.println("======================================================================");

        System.out.print("  Enter Item ID to Search : ");
        String id = scanner.nextLine().trim();

        System.out.println("======================================================================");
        itemController.searchItem(id);
    }

    public void viewAllItemsUI() {

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

    public void deleteItemForm() {
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
    
    public void loadItemMenu() {
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

}
