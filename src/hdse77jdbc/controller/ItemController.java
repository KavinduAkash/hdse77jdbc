package hdse77jdbc.controller;

import hdse77jdbc.model.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kavinduakash
 */
public class ItemController {
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
                double unitPrice = result.getDouble("unitPrice");
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
                double unitPrice = result.getDouble("unitPrice");
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
}
