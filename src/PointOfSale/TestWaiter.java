package PointOfSale;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Jovy
 */
public class TestWaiter {

    public static void main(String[] args) {
        Waiter testWaiter = new Waiter("Jovy", "Cortez", "1221");
        /**
         * Since Table are a button Click I don't think a table Object is needed
         * Table testTable = new Table(null, null, true, null, BigDecimal.ZERO,
         * null)
         */
        ArrayList<Order> orderList = new ArrayList<>();
        Database dbMenuLoad = new Database();
        Object[][] menu = dbMenuLoad.LoadMenu();
        /**
         * This is how a waiter adds orders to a tables You can store this in an
         * array or in the Database
         */

//        orderList.add(new Order(menu[0][0].toString(), BigDecimal.valueOf((double) menu[0][1])));
//        orderList.add(new Order(menu[4][0].toString(), BigDecimal.valueOf((double) menu[4][1])));
//        orderList.add(new Order(menu[9][0].toString(), BigDecimal.valueOf((double) menu[9][1])));
        testWaiter.setWaiterId("2323");
        System.out.println("Hello my name is: " + testWaiter.getFirstName() + " and I'll be your waitress today.");
        System.out.println("Your Order:\n");
        for (int i = 0; i < orderList.size(); i++) {
            Order get = orderList.get(i);
            System.out.println(++i + " " + get);
            --i;
        }
        /**
         * Maybe Storing in a database will be easier Because we can use
         * W3School with the SELECT CREATE READ UPDATE and DELETE
         */

    }

}
