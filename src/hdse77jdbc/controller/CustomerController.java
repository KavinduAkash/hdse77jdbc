package hdse77jdbc.controller;

import hdse77jdbc.model.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import hdse77jdbc.db.DBConnection;

public class CustomerController {
    public void addCustomer(Customer cus) {
       try {

            Connection conn = DBConnection.getInstance().getConnection();

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

   public void updateCustomer(Customer cus) {
       try {

            Connection conn = DBConnection.getInstance().getConnection();

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

   public List<Customer> getAllCustomers() {

       List<Customer> cusList = new ArrayList<Customer>();

       try {

            Connection conn = DBConnection.getInstance().getConnection();

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

   public void deleteCustomer(String id) {
       try {

            Connection conn = DBConnection.getInstance().getConnection();

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

   public void searchCustomer(String id) {
       try {

            Connection conn = DBConnection.getInstance().getConnection();

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
}
