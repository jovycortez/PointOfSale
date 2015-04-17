package PointOfSale;

import java.sql.*;
import java.util.Arrays;

/**
 *
 * @author Jovy
 */
public class TestDatabase {

    Connection conn = null;

    public static void main(String[] args) {
        Database dbconn = new Database();
        //Connection ConnecttoDB = dbconn.ConnecttoDB();  
        //This is the waiter Id which returns the waiter name
//        System.out.println("Employee id: " + dbconn.Login(1));
//        Object[][] Orders = dbconn.ViewAllOrders();
//        //This is All orders in the database.
//        for (int i = 0; i < Orders.length; i++) {
//            for (int j = 0; j < Orders[i].length; j++) {
//                System.out.print(Orders[i][j] + "\t");
//            }
//            System.out.println(" ");
//        }
//        System.out.println(dbconn.isEmployee(1));
//        System.out.println(dbconn.isEmployee(9));
        System.out.println(dbconn.getOrderNumberFromDB());
    }

    

}
