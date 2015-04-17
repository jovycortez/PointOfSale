package PointOfSale;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author Jovy
 */
public class Receipt extends Order {

    // Declare variable
    @Override
    public String toString() {
        return "Receipt{" + "OrderNumber=" + OrderNumber + ", subtotal=" + subtotal + ", tax=" + tax + ", stateTax=" + stateTax + ", total=" + total + ", orderList=" + orderList + '}';
    }
    private int OrderNumber;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal stateTax;
    private BigDecimal total;
    private ArrayList<Order> orderList;
    private String PaymentMethod;

    //Pre:
    //Pro:
    public Receipt(String itemName, BigDecimal itemPrice, String PaymentMethod, int OrderNumber) {
        super(itemName, itemPrice);
        this.PaymentMethod = PaymentMethod;
        
    }

    //Pre:
    //Pro:
    public Receipt() {
        super();
    }

    // This is the toString we made to see the tax.
    //Pre:
    //Pro:
    public String getReceipt() {
        return "Receipt\n"
                + "\n" + getOrderList()
                + "\nSubtotal\t$" + getSubtotal()
                + "\ntax\t" + getTax() + "%"
                + "\nTax Amount\t$" + getTaxAmount()
                + "\ntotal\t$" + getTotal()
                + "\n\nTIP:\t$______________"
                + "\nTOTAL:\t$______________";
    }

    //Pre:
    //Pro:
    public BigDecimal getStateTax() {
        return this.stateTax;
    }

    //Pre:
    //Pro:
    public int getOrderNumber() {
        
        return OrderNumber;
    }

    //Pre:
    //Pro:
    public void setOrderNumber(int OrderNumber) {
        this.OrderNumber =OrderNumber;
        
    }

    //Pre:
    //Pro:
    //Pre:
    //Pro:
    public void setTotal() {

        this.total = getStateTax().multiply(getSubtotal());
    }

    //Pre:
    //Pro:
    public StringBuilder getOrderList() {
        StringBuilder listOfOrder = new StringBuilder();
        for (int i = 0; i < orderList.size(); i++) {
            listOfOrder.append(orderList.get(i)).append("\n");

        }
        return listOfOrder;

    }

    //Pre:
    //Pro:
    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    //Pre:
    //Pro:
    public BigDecimal getSubtotal() {
        subtotal = BigDecimal.valueOf(0.0);
        for (int i = 0; i < orderList.size(); i++) {
            subtotal = subtotal.add(orderList.get(i).getItemPrice());
        }
        return subtotal;
    }
    public BigDecimal getSubtotalLabel() {
        subtotal = BigDecimal.valueOf(0.0);
        for (int i = 0; i < orderList.size(); i++) {
            subtotal = subtotal.add(orderList.get(i).getItemPrice());
        }
        return subtotal;
    }

    //Pre:
    //Pro:
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {

        return BigDecimal.valueOf(0.070).multiply(BigDecimal.valueOf(100).stripTrailingZeros());
    }

    public BigDecimal getTotal() {
        //return getSubtotal().add(getSubtotal().multiply(BigDecimal.valueOf(0.070)));
        return getSubtotal().add(getSubtotal().multiply(BigDecimal.valueOf(0.070)).setScale(2, RoundingMode.FLOOR));
    }

    public BigDecimal getTaxAmount() {
        return getSubtotal().multiply(BigDecimal.valueOf(0.070)).setScale(2, RoundingMode.FLOOR);
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    public String getPaymentMethod() {

        return PaymentMethod;
    }

}
