package PointOfSale;

import java.math.BigDecimal;

/**
 *
 * @author Jovy
 */
public class TestTransaction {
    public static void main(String[] args) {
        Transaction testTransaction = new Transaction();
        System.out.println("Change:$"+ testTransaction.payByCash(BigDecimal.valueOf(10.00), BigDecimal.valueOf(4.01)));
        
        System.out.println(testTransaction.payByGiftCard(BigDecimal.valueOf(2000.00), BigDecimal.valueOf(1205.01)));
        
    }
}
