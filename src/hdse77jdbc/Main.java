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

class Customer {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;

    Customer() {
    }

    Customer(String id, String name, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + '}';
    }
}

// ======================= Item Model ==========================

class Item {
    private String id;
    private String name;
    private double unitPrice;
    private int qty;

    Item() {
    }

    Item(String id, String name, double unitPrice, int qty) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", unitPrice=" + unitPrice + ", qty=" + qty + '}';
    }
}

// ======================= Order Details Model ==========================
class OrderDetials {
       private String orderDetailsId;
       private String itemId;
       private int qty;
       private double totalPrice;

        public String getOrderDetailsId() {
            return orderDetailsId;
        }

        public void setOrderDetailsId(String orderDetailsId) {
            this.orderDetailsId = orderDetailsId;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        @Override
        public String toString() {
            return "OrderDetials{" + "orderDetailsId=" + orderDetailsId + ", itemId=" + itemId + ", qty=" + qty + ", totalPrice=" + totalPrice + '}';
        }
   }


class Order {
    private String orderId;
    private String customerId;
    private Date date;
    private List<OrderDetials> orderDetailsList;

    public Order() {
    }

    public Order(String orderId, String customerId, Date date, List<OrderDetials> orderDetailsList) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = date;
        this.orderDetailsList = orderDetailsList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderDetials> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetials> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", customerId=" + customerId + ", date=" + date + ", orderDetailsList=" + orderDetailsList + '}';
    }
}


public class Main {
   private static final Scanner scanner = new Scanner(System.in);

   // ======================= Order Functionality Handling ==========================
   
   public static void placeOrder(Order order) {
   
       
       
   }
   
   // ======================= Customer Functionality Handling ==========================

   public static void addCustomer(Customer cus) {
       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String query = "INSERT INTO customer VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1 , cus.getId());
            pstmt.setString(2 , cus.getName());
            pstmt.setString(3 , cus.getAddress());
            pstmt.setString(4 , cus.getPhone());
            pstmt.setString(5 , cus.getEmail());

            int result = pstmt.executeUpdate();

            if(result>0) {
                System.out.println("Customer saved successfully!");
            } else {
                System.out.println("Something went wrong!");
            }

            pstmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

   }

   public static void updateCustomer(Customer cus) {
       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String query = "UPDATE customer SET name=?, address=?, phone=?, email=? WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1 , cus.getName());
            pstmt.setString(2 , cus.getAddress());
            pstmt.setString(3 , cus.getPhone());
            pstmt.setString(4 , cus.getEmail());
            pstmt.setString(5 , cus.getId());

            int result = pstmt.executeUpdate();

            if(result>0) {
                System.out.println("Customer updated successfully!");
            } else {
                System.out.println("Something went wrong!");
            }

            pstmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

   }

   public static List<Customer> getAllCustomers() {

       List<Customer> cusList = new ArrayList<Customer>();

       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String query = "SELECT * FROM customer";

            Statement stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String address = result.getString("address");
                String phone = result.getString("phone");
                String email = result.getString("email");

                Customer cus = new Customer(id, name, address, phone, email);
                cusList.add(cus);
            }

            stmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

       return cusList;

   }

   public static void deleteCustomer(String id) {
       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String query = "DELETE FROM customer WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, id);

            int result = pstmt.executeUpdate();

            if(result>0) {
                System.out.println("Customer deleted successfully!");
            } else {
                System.out.println("Something went wrong!");
            }

            pstmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

   }

   public static void searchCustomer(String id) {
       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String query = "SELECT * FROM customer WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, id);

            ResultSet result = pstmt.executeQuery();

            if(result.next()) {

                String cid = result.getString("id");
                String name = result.getString("name");
                String address = result.getString("address");
                String phone = result.getString("phone");
                String email = result.getString("email");

                System.out.println(cid + " - " + name + " - " + address + " - " + phone + " - " + email);

            } else {
                System.out.println("Customer not found!");
            }

            pstmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

   }

   // ======================= Item Functionality Handling ==========================

   public static void addItem(Item item) {
       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // I001, Pen, 50.00, 100
            // INSERT INTO item VALUES('I001', 'Pen', 50.00, 100)

            String query = "INSERT INTO item VALUES(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, item.getId());
            pstmt.setString(2, item.getName());
            pstmt.setDouble(3, item.getUnitPrice());
            pstmt.setInt(4, item.getQty());

            int result = pstmt.executeUpdate();

            if(result>0) {
                System.out.println("Item saved successfully!");
            } else {
                System.out.println("Something went wrong!");
            }

            pstmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

   }

   public static void updateItem(Item item) {
       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // UPDATE item SET name='Pen', unit_price=55.00, qty=120 WHERE id='I001'

            String query = "UPDATE item SET name=?, unit_price=?, qty=? WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getUnitPrice());
            pstmt.setInt(3, item.getQty());
            pstmt.setString(4, item.getId());

            int result = pstmt.executeUpdate();

            if(result>0) {
                System.out.println("Item updated successfully!");
            } else {
                System.out.println("Something went wrong!");
            }

            pstmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

   }

   public static List<Item> getAllItems() {

       List<Item> itemList = new ArrayList<Item>();

       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // SELECT * FROM item

            String query = "SELECT * FROM item";

            Statement stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                double unitPrice = result.getDouble("unit_price");
                int qty = result.getInt("qty");

                Item item = new Item(id, name, unitPrice, qty);
                itemList.add(item);
            }

            stmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

       return itemList;

   }

   public static void deleteItem(String id) {
       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // DELETE FROM item WHERE id='I001'

            String query = "DELETE FROM item WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, id);

            int result = pstmt.executeUpdate();

            if(result>0) {
                System.out.println("Item deleted successfully!");
            } else {
                System.out.println("Something went wrong!");
            }

            pstmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

   }

   public static void searchItem(String id) {
       try {

            final String DB_URL = "jdbc:mysql://localhost:3306/pos";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "ijse";

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // SELECT * FROM item WHERE id='I001'

            String query = "SELECT * FROM item WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, id);

            ResultSet result = pstmt.executeQuery();

            if(result.next()) {

                String iid = result.getString("id");
                String name = result.getString("name");
                double unitPrice = result.getDouble("unit_price");
                int qty = result.getInt("qty");

                System.out.println(iid + " - " + name + " - " + unitPrice + " - " + qty);

            } else {
                System.out.println("Item not found!");
            }

            pstmt.close();
            conn.close();

       } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
       }

   }

   // ======================= Customer UI Handling ==========================

   public static void addCustomerForm() {
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

       addCustomer(new Customer(id, name, address, phone, email));
   }

   public static void updateCustomerForm() {
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
       updateCustomer(new Customer(id, name, address, phone, email));
   }

   public static void searchCustomerForm() {
       System.out.println("\n======================================================================");
       System.out.println("                        SEARCH CUSTOMER FORM");
       System.out.println("======================================================================");
       System.out.print("  Enter Customer ID to Search : ");
       String id = scanner.nextLine().trim();

       System.out.println("======================================================================");
       searchCustomer(id);
   }

   public static void viewAllCustomersUI() {

       List<Customer> customerList = getAllCustomers();

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

   public static void deleteCustomerForm() {
       System.out.println("\n======================================================================");
       System.out.println("                        DELETE CUSTOMER FORM");
       System.out.println("======================================================================");
       System.out.print("  Enter Customer ID to Delete : ");
       String id = scanner.nextLine().trim();

       System.out.print("  Are you sure you want to delete? (Y/N): ");
       String confirm = scanner.nextLine().trim();

       System.out.println("======================================================================");
       if(confirm.equals("Y")) {
           deleteCustomer(id);
       } else {
           System.out.println("Invalid Input!");
       }
   }

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

            addItem(new Item(id, name, unitPrice, qty));
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

            updateItem(new Item(id, name, unitPrice, qty));
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
        searchItem(id);
    }

    public static void viewAllItemsUI() {

        List<Item> itemList = getAllItems();

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
            deleteItem(id);
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
            
            placeOrder(order);
            
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

    public static void loadCustomerMenu() {
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
                    loadCustomerMenu();
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


    public static void main(String[] args) {
        loadMainMenu();
    }
}