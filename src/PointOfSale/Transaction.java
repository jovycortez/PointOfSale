package PointOfSale;

import java.math.BigDecimal;

/**
 *
 * @author Jovy
 */
public class Transaction {

    /**
     * I Don't know how to format BigDecimals maybe we can watch a video on it
     * later.
     */

    public Object payByCash(BigDecimal cashPaid, BigDecimal totalAmount) {
        // change = Cash paid - total from the receipt
        BigDecimal change;
        return "Cash: $" + cashPaid.subtract(totalAmount);
    }
    public double getChange(String Cash, String Total){
        BigDecimal BDCash = BigDecimal.valueOf(Double.parseDouble(Cash));
        BigDecimal BDTotal = BigDecimal.valueOf(Double.parseDouble(Total));
        
        //Insert an if statement 
        //If the cash paid is less than the amount due, show message dialog box with the
        //amount due
        Double change = Math.abs(Double.parseDouble(BDCash.subtract(BDTotal).toString()));
        
        return change;
    }

    public Object payByCreditCard(BigDecimal totalAmount) {

        return "Credit Card: $" + totalAmount;
    }
    
    public Object payByGiftCard(BigDecimal cashPaid, BigDecimal totalAmount) {
        return "Gift Card remining Balance: $" + cashPaid.subtract(totalAmount);
    }
    
}
