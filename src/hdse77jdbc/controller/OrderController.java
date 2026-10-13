package hdse77jdbc.controller;

import hdse77jdbc.model.Order;
import hdse77jdbc.model.OrderDetials;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kavinduakash
 */
public class OrderController {
    public void addOrderDetails(Connection conn, Order order) throws Exception {
            for (OrderDetials orderDetials : order.getOrderDetailsList()) {
                // INSERT INTO orders_detail VALUES(1, 'O001', 'I001', 10, 100.00);
                String query = "INSERT INTO order_detail (order_id, item_id, qty, unit_price)VALUESX(?, ?, ?, ?)";
                
                PreparedStatement pstmt = conn.prepareStatement(query);
                
                pstmt.setString(1, order.getOrderId());
                pstmt.setString(2, orderDetials.getItemId());
                pstmt.setInt(3, orderDetials.getQty());
                
                String itemQuery = "SELECT * FROM item WHERE id=?";
                PreparedStatement itemPstmt = conn.prepareStatement(itemQuery);
                itemPstmt.setString(1, orderDetials.getItemId());
                
                ResultSet rs = itemPstmt.executeQuery();
                
                if(rs.next()) {
                    pstmt.setDouble(4, rs.getDouble("unitPrice"));
                } else {
                    throw new SQLException("Invalid Item!");
                }
                
                int result = pstmt.executeUpdate();
                
                if(!(result>0)) {
                   throw new SQLException("Something went wrong!");
                }
            }
   }
   
   public void placeOrder(Order order) {
       Connection conn = null;
       
       try {
       
           final String DB_URL = "jdbc:mysql://localhost:3306/pos";
           final String DB_USERNAME = "root";
           final String DB_PASSWORD = "ijse";
           
           conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
           
           conn.setAutoCommit(false);
           
           // INSERT INTO orders VALUES('O001', 'C001', '2026-10-06');
           String query = "INSERT INTO orders VALUES(?, ?, ?)";
           
           PreparedStatement pstmt = conn.prepareStatement(query);
           
           pstmt.setString(1, order.getOrderId());
           /*
           order.getDate() -> java.util.Date
           java.sql.Date date = new java.sql.Date(order.getDate().getTime());
           */
           pstmt.setDate(2, new java.sql.Date(order.getDate().getTime()));         
           pstmt.setString(3, order.getCustomerId());
           
           int result = pstmt.executeUpdate();
           
           if(result > 0) {
            
               addOrderDetails(conn, order);            
            
           } else {
               System.out.println("Something went wrong!");
           }
           
           conn.commit();
           
       } catch(Exception e) {
            
           try {
               conn.rollback();
           } catch(SQLException ex) {
               ex.printStackTrace();
               System.out.println(ex.getMessage());
           }
           e.printStackTrace();
           System.out.println("Something went wrong!");
       
       } finally {
           try {
               conn.setAutoCommit(true);
           } catch(SQLException ex) {
               ex.printStackTrace();
               System.out.println(ex.getMessage());
           }
       }
       
   }
}
