package PointOfSale;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jovy
 */
public class Database {

    Connection dbconn = null;
    java.util.Date date = new java.util.Date();
    String[] time = date.toString().split(" ");
//This was public static Connection ConnecttoDB() as of 4/17 8:40PM
//This has been modified for Initializing database in the UI (another class)

    public static Connection ConnecttoDB() {
        try {
            //System.out.println("Connected!");
            Class.forName("org.sqlite.JDBC");
            Connection dbconn = DriverManager.getConnection("jdbc:sqlite:POSConsoleDatabase.sqlite");
            return dbconn;
        } catch (Exception e) {
            System.out.println("Not Connected!");
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
//This method loads the database itemName and itemPrice to an arraylist

    public Object[][] LoadMenu() {
        // ConnecttoDB();
        //Coming from an array of elements
        Object[][] loadedMenu = null;
        ResultSet rs = null;
        Statement dbStatement = null;

        String itemPrice = null;
        try {

            dbStatement = ConnecttoDB().createStatement();
            rs = dbStatement.executeQuery("SELECT COUNT(*) FROM Menu; ");
            //Columns in sql starts from 1
            int numberOfRows = rs.getInt(1);

            loadedMenu = new Object[numberOfRows][2];

            rs = dbStatement.executeQuery("SELECT itemNameDB, itemPriceDB FROM Menu;");
            int i = 0;

            while (rs.next()) {

                loadedMenu[i][0] = rs.getString("itemNameDB");
                loadedMenu[i][1] = rs.getDouble("itemPriceDB");
                // System.out.println(i+" "+loadedMenu[i ][0]+" "+ loadedMenu[i][1]);
                i++;
            }
            return loadedMenu;
        } catch (Exception e) {

            System.out.println(e);
        }

        return loadedMenu;
    }

    /**
     * Pre: You need to enter the employee Id. The employeeId has to to in the
     * database Post: This return a person's name that matches the Id in the
     * database
     */
    public String Login(int EmployeeIdNumber) {
        // ConnecttoDB();
        ResultSet rs = null;
        Statement dbStatement = null;

        String EmployeeFirstName = null;
        try {
            System.out.println(" try");
            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT firstName FROM employeeDB WHERE empId = " + EmployeeIdNumber + ";");

            while (rs.next()) {

                EmployeeFirstName = rs.getString("firstName");

            }

        } catch (Exception e) {

            System.out.println(e);
        } finally {
            try {
                rs.close();

            } catch (Exception e) {

            }
        }
        return EmployeeFirstName;

    }

    public void updateOpenOrderTable() {
        ResultSet rs = null;
        Statement dbStatement = null;
        try {
            String sql = "SELECT OrderNumber from Revenue WHERE PaymentMethod = \"null\"";
            rs = dbStatement.executeQuery(sql);

        } catch (Exception e) {
            System.out.println("updateOpenOrderTable: " + e);
        } finally {
            try {
                rs.close();

            } catch (Exception e) {

            }
        }

    }

    public boolean isEmployee(int empId) {
        ResultSet rs = null;
        Statement dbStatement = null;
        boolean employeeExists = false;
        try {

            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT empId FROM employeeDB WHERE empId = " + empId + ";");

            while (rs.next()) {

                int empIdInput = rs.getInt("empId");
                employeeExists = true;
            }

        } catch (Exception e) {

            System.out.println(e);
        } finally {
            try {
                rs.close();

            } catch (Exception e) {

            }
        }
        return employeeExists;

    }
    // SELECT empId, firstName FROM employeeDB where empId=1
// Query to check if person is clocked in
    // check user ID from database
    // isclock in Method , is clock out method, checkEmp
    //Select ID From EmpId
    //save validated empID input to a global variable

    public void Clockin(int empId) {
        ResultSet rs = null;
        Statement dbStatement = null;
        String intime = null, outtime = null, change = null;
        int timein, timeout, hours, timeid=1;
        double hourpercent;
        try {

            dbStatement = ConnecttoDB().createStatement();
            if (!isClockedIn(empId)) {
                date = new java.util.Date();
                time = date.toString().split(" ");
                dbStatement.executeUpdate("INSERT INTO TimeCard (empId, ClockIn)\n"
                        + "VALUES (" + empId + ",\"" + time[3] + "\");");

            } else {
                rs = dbStatement.executeQuery("SELECT * FROM TimeCard;");            
                while(rs.next()){
                    if(rs.getInt("empId")==empId && rs.getString("ClockOut")== null){
                        timeid= rs.getInt("timeCardId");
                        intime = rs.getString("ClockIn");
                        outtime = rs.getString("ClockOut");
                    }
                }
                System.out.println(timeid+""+empId);
                System.out.println("??");
                date = new java.util.Date();
                time = date.toString().split(" ");
                dbStatement.executeUpdate("UPDATE TimeCard "
                        + "SET ClockOut =\"" + time[3] + "\" WHERE timeCardId in (" + timeid + ")");
                //change = intime.substring(0,2);
                //hours = Integer.parseInt(change);
                change = outtime.substring(0,2);
                hours = Integer.parseInt(change);
                hourpercent = hours;
                dbStatement.executeUpdate("UPDATE TimeCard "
                        + "SET HoursOfWork =\"" + hourpercent + "\" WHERE timeCardId in (" + timeid + ")");
            }
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            try {
                rs.close();

            } catch (Exception e) {

            }
        }

//Select empID from TimeCard where empID =variablename
        //if empID= variable is null, insert empId, clockin = date/time
    }

    public boolean isClockedIn(int empId) {
        ResultSet rs = null;
        Statement dbStatement = null;
        boolean employeeExists = false;
        try {

            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT * FROM TimeCard;");

            while (rs.next()) {
                if(rs.getInt("empId")==empId){
                    if(rs.getString("ClockOut")==null){
                        employeeExists = true;
                    }
                }
            }

        } catch (Exception e) {

            System.out.println("isClockedIn(int empId) "+ e);
        } finally {
            try {
                rs.close();

            } catch (Exception e) {

            }
        }
        return employeeExists;

    }

    public void ClockOut(int empId, String Clockout) {
        ResultSet rs = null;
        Statement dbStatement = null;

        try {

            dbStatement = ConnecttoDB().createStatement();

            //   dbStatement.executeUpdate("INSERT INTO TimeCard (empId, ClockIn)\n"
            //      + "VALUES (" + empId + ",\"" + Clockin + "\");");

            /* while (rs.next()) {

             int empIdInput = rs.getInt("empId");
             employeeExists = true;
             }
             */
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            try {
                rs.close();

            } catch (Exception e) {

            }
        }
    }

    public Object[][] ViewAllOrders() {

        // ConnecttoDB();
        //Coming from an array of elements
        Object[][] AllOrders = null;
        ResultSet rs = null;
        Statement dbStatement = null;

        String itemPrice = null;
        try {

            dbStatement = ConnecttoDB().createStatement();
            rs = dbStatement.executeQuery("SELECT COUNT(*) FROM OrderDB; ");
            //Columns in sql starts from 1
            int numberOfRows = rs.getInt(1);

            AllOrders = new Object[numberOfRows][2];

            rs = dbStatement.executeQuery("SELECT itemNameDB, itemPriceDB FROM OrderDB;");
            int i = 0;

            while (rs.next()) {

                AllOrders[i][0] = rs.getString("itemNameDB");
                AllOrders[i][1] = rs.getDouble("itemPriceDB");
                // System.out.println(i+" "+loadedMenu[i ][0]+" "+ loadedMenu[i][1]);
                i++;
            }
            return AllOrders;
        } catch (Exception e) {

            System.out.println("ViewAllOrders " + e);

        }

        return AllOrders;
    }

    public void saveRevenue(double Subtotal, double TaxAmount, double Total, String PaymentMethod, int OrderNumber, int TableNumber, String ServerName) {
        ResultSet rs = null;
        Statement dbStatement = null;

        try {

            dbStatement = ConnecttoDB().createStatement();

            dbStatement.executeUpdate("INSERT INTO Revenue (Subtotal, TaxAmount, Total, PaymentMethod,  OrderNumber, TableNumber, ServerName)\n"
                    + "VALUES (" + Subtotal + "," + TaxAmount + "," + Total + ",\"" + PaymentMethod + "\"," + OrderNumber + "," + TableNumber + ",\"" + ServerName + "\");");

        } catch (Exception e) {

            System.out.println("saveRevenue " + e);
        }

    }

    public void salesLog(String itemName, double itemPrice, int OrderNumber) {
        ResultSet rs = null;
        Statement dbStatement = null;
        int itemIdFKfromMenu = 0;
        try {

            dbStatement = ConnecttoDB().createStatement();
            rs = dbStatement.executeQuery("SELECT itemId FROM Menu WHERE itemNameDB = \"" + itemName + "\";");

            while (rs.next()) {

                itemIdFKfromMenu = rs.getInt("itemId");
            }
            dbStatement.executeUpdate("INSERT INTO SalesLog (itemId,itemNameSold, itemPriceSold, OrderNumber)\n"
                    + "VALUES (" + itemIdFKfromMenu + ",\"" + itemName + "\"," + itemPrice + "," + OrderNumber + ");");

        } catch (Exception e) {

            System.out.println("salesLog " + e);
        }
    }

    /* METHOD NAME: retrieveOrderFromSalesLog()
     *  Return type: ArrayList Object
     *  Retrieves saved and paid orders/receipt to the POS' table model from the SalesLog and Revenue table from
     *  the database using the OrderNumber as the foreign key. This method allows employee  
     *  to edit an order in the table model. After the order has been modified in the POS table model, once the print or save button even from the 
     *  the transaction panel, changes in the SalesLog and Revenue tables in the database will be updated.
     *  Note that only one value of Order Number will appear on the Revenue table in the database.
     */
    public Object[] retrieveItemPriceFromSalesLog(int OrderNumber) {
        ResultSet rs = null;
        Statement dbStatement = null;
        Object[] savedOrders = null;

        try {

            dbStatement = ConnecttoDB().createStatement();
            rs = dbStatement.executeQuery("SELECT COUNT(*) FROM SalesLog WHERE OrderNumber =" + OrderNumber + "; ");
            //Columns in sql starts from 1
            int numberOfRows = rs.getInt(1);

            savedOrders = new Object[numberOfRows];

            rs = dbStatement.executeQuery("SELECT itemPriceSold FROM SalesLog WHERE OrderNumber = " + OrderNumber + ";");
            int i = 0;

            while (rs.next()) {

                savedOrders[i] = rs.getDouble("itemPriceSold");
                // System.out.println(i+" "+savedOrders[i][0]+" "+ savedOrders[i][1]);
                i++;
            }

        } catch (Exception e) {

            System.out.println("retrieveItemPriceFromSalesLog " + e);
        }

        return savedOrders;

    }

    public Object[] retrieveItemNameFromSalesLog(int OrderNumber) {
        ResultSet rs = null;
        Statement dbStatement = null;
        Object[] savedOrders = null;

        try {

            dbStatement = ConnecttoDB().createStatement();
            rs = dbStatement.executeQuery("SELECT COUNT(*) FROM SalesLog WHERE OrderNumber =" + OrderNumber + "; ");
            //Columns in sql starts from 1
            int numberOfRows = rs.getInt(1);

            savedOrders = new Object[numberOfRows];

            rs = dbStatement.executeQuery("SELECT itemNameSold FROM SalesLog WHERE OrderNumber = " + OrderNumber + ";");
            int i = 0;

            while (rs.next()) {

                savedOrders[i] = rs.getString("itemNameSold");

                // System.out.println(i+" "+savedOrders[i][0]+" "+ savedOrders[i][1]);
                i++;
            }

        } catch (Exception e) {

            System.out.println("retrieveItemNameFromSalesLog " + e);
        }
        return savedOrders;

    }
    /*  Method Name: getOrderNumberFromDB()
     *   Returns an integer value for the Order Number. 
     *   Gets the last value from the order number column from the database (Revenue table). 
     *   Last OrderNumber is incremented by 1 and stored as the newOrderNumber. 
     *   First Order Number is 81234.
     */

    public int getOrderNumberFromDB() {
        ResultSet rs = null;
        Statement dbStatement = null;
        int NewOrderNumber = 0;
        try {

            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT MAX (OrderNumber) AS OrderNumber FROM Revenue");
            while (rs.next()) {
                NewOrderNumber = rs.getInt("OrderNumber");
                NewOrderNumber++;
            }

        } catch (Exception e) {

            System.out.println("getOrderNumberFromDB " + e);

        }

        return NewOrderNumber;
    }

    public String getWaiterNameFromDB(int OrderNumber) {
        ResultSet rs = null;
        Statement dbStatement = null;
        String waiterName = null;
        try {

            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT (ServerName) FROM Revenue WHERE OrderNumber=" + OrderNumber + ";");
            while (rs.next()) {
                waiterName = rs.getString("ServerName");

            }

        } catch (Exception e) {

            System.out.println("getWaiterNameFromDB" + e);
        }

        return waiterName;
    }

    public int getSavedTableNumberFromDB(int OrderNumber) {
        ResultSet rs = null;
        Statement dbStatement = null;
        int SavedTableNumber = 0;
        try {

            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT (TableNumber) FROM Revenue WHERE OrderNumber=" + OrderNumber + ";");
            while (rs.next()) {
                SavedTableNumber = rs.getInt("TableNumber");

            }

        } catch (Exception e) {

            System.out.println("getSavedTableNumberFromDB " + e);
        }

        return SavedTableNumber;
    }

    public int getSavedOrderNumberFromDB(int OrderNumber) {
        ResultSet rs = null;
        Statement dbStatement = null;
        int SavedOrderNumber = 0;
        try {

            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT (OrderNumber) FROM Revenue WHERE OrderNumber=" + OrderNumber + ";");
            while (rs.next()) {
                SavedOrderNumber = rs.getInt("OrderNumber");

            }

        } catch (Exception e) {

            System.out.println("getSavedOrderNumberFromDB " + e);
        }

        return SavedOrderNumber;
    }

    public void updateRevenue(double Subtotal, double TaxAmount, double Total, int OrderNumber) {
        ResultSet rs = null;
        Statement dbStatement = null;

        try {

            dbStatement = ConnecttoDB().createStatement();

            dbStatement.executeUpdate("UPDATE Revenue SET Subtotal = " + Subtotal + ", TaxAmount = " + TaxAmount + ", Total = " + Total + " WHERE OrderNumber = " + OrderNumber + ";");

        } catch (Exception e) {

            System.out.println("updateRevenue" + e);

        }
    }

    //SELECT OrderNumber FROM Revenue WHERE PaymentMethod = "null";
    public int[] viewSavedOrderNumberFromDB() {
        ResultSet rs = null;
        Statement dbStatement = null;
        int[] SavedOrderNumber = null;
        try {

            dbStatement = ConnecttoDB().createStatement();
            rs = dbStatement.executeQuery("SELECT COUNT(*) FROM Revenue WHERE PaymentMethod = \"null\";");
            int numberOfRows = rs.getInt(1);
            SavedOrderNumber = new int[numberOfRows];

            rs = dbStatement.executeQuery("SELECT OrderNumber FROM Revenue WHERE PaymentMethod = \"null\";");
            int i = 0;
            while (rs.next()) {
                SavedOrderNumber[i] = rs.getInt("OrderNumber");
                i++;
            }

        } catch (Exception e) {

            System.out.println("viewSavedOrderNumberFromDB" + e);
        }

        return SavedOrderNumber;
    }

    public void deleteFromSalesLog(int OrderNumber) {
        ResultSet rs = null;
        Statement dbStatement = null;

        try {

            dbStatement = ConnecttoDB().createStatement();

            dbStatement.executeUpdate("DELETE FROM SalesLog WHERE OrderNumber = " + OrderNumber + ";");

        } catch (Exception e) {

            System.out.println("deleteFromSalesLog " + e);

        }
    }

    public void updatePaymentMethodtoDB(String PaymentMethod, double Tendered, int OrderNumber) {
        ResultSet rs = null;
        Statement dbStatement = null;

        try {

            dbStatement = ConnecttoDB().createStatement();

            dbStatement.executeUpdate("UPDATE Revenue SET PaymentMethod = \"" + PaymentMethod + "\", Tendered = " + Tendered + " WHERE OrderNumber= " + OrderNumber + ";");

        } catch (Exception e) {

            System.out.println("insertPaymentMethodtoDB " + e);
        } finally {
            try {
                rs.close();

            } catch (Exception e) {

            }
        }

    }

    public double getChange(String Cash, String Total) {
        BigDecimal BDCash = BigDecimal.valueOf(Double.parseDouble(Cash));
        BigDecimal BDTotal = BigDecimal.valueOf(Double.parseDouble(Total));

        Double change = Double.parseDouble(BDCash.subtract(BDTotal).toString());

        return change;
    }

    public boolean userLogin(String userID) {
        ResultSet rs = null;
        Statement dbStatement = null;
        boolean isCorrectPass = false;
        try {

            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT * FROM employeeDB WHERE empId=?;");
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Correct User ID!");
                UserInterface POS = new UserInterface();
                POS.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect. Please enter valid ID!");
            }

        } catch (Exception e) {

            System.out.println("login from db:" + e);
        } finally {
            try {
                rs.close();

            } catch (Exception e) {

            }
        }

        return isCorrectPass;

    }

    public String loginWaiter(int userPass) {
        ResultSet rs = null;
        Statement dbStatement = null;
        String waiterName = null;
        try {

            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT (firstName) FROM employeeDB WHERE empId= " + userPass + ";");
            while (rs.next()) {
                waiterName = rs.getString("firstName");
                System.out.println("Database " + waiterName);
            }

        } catch (Exception e) {

            System.out.println("waiterLogin" + e);

        }

        return waiterName;
    }

    public double getGiftCardBalance(int giftCardNumberfromKeypad) {
        ResultSet rs = null;
        Statement dbStatement = null;
        double giftCardAmount = 0;
        try {

            dbStatement = ConnecttoDB().createStatement();

            rs = dbStatement.executeQuery("SELECT (Balance) FROM GiftCardTable WHERE CardNumber=" + giftCardNumberfromKeypad + ";");
//            if (!rs.next()) {
//                JOptionPane.showMessageDialog(null, "Card Not Valid! Please enter another number. ");
//
//            } else {
                while (rs.next()) {
                    giftCardAmount = rs.getDouble("Balance");

                }
            
        } catch (Exception e) {

            System.out.println("getSavedTableNumberFromDB " + e);
        }

        return giftCardAmount;
    }

    public void updategiftCardBalance(double giftCardBalance, int cardNum) {
        ResultSet rs = null;
        Statement dbStatement = null;

        try {

            dbStatement = ConnecttoDB().createStatement();

            dbStatement.executeUpdate("UPDATE GiftCardTable SET Balance = " + giftCardBalance + " WHERE CardNumber = "+cardNum+" ;");

        } catch (Exception e) {

            System.out.println("updategiftCardBalance " + e);
        }

    }

//    public double getChange(String Cash, String Total) {
//        BigDecimal BDCash = BigDecimal.valueOf(Double.parseDouble(Cash));
//        BigDecimal BDTotal = BigDecimal.valueOf(Double.parseDouble(Total));
//
//        Double change = Double.parseDouble(BDCash.subtract(BDTotal).toString());
//
//        return change;
//    }
}
