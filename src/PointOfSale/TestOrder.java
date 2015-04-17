package PointOfSale;

import java.math.BigDecimal;

public class TestOrder {
    public static void main(String[] args) {
        
        Database dbMenuLoad = new Database();
        Object[][] menu =dbMenuLoad.LoadMenu();
       
       //To access each item for each button
//        for (int i = 0; i < menu.length; i++) {
//            for (int j = 0; j < menu[i].length; j++) {
//               System.out.print(menu[i][j]+"\t");
//                
//            }
//            System.out.println("");
//        }
//        
       Order myOrder1 = new Order(menu[0][0].toString(), BigDecimal.valueOf((double)menu[0][1]));
        System.out.println(myOrder1);
    }   
}
