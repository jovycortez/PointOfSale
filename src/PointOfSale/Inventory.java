package PointOfSale;
import java.math.BigDecimal;

/**
 *
 * @author Jovy
 */
public class Inventory {
    private String categoryName;
    private String categoryId;
    private String itemName;
    private String itemID;
    private int itemQty;
    private boolean inStock;
    private String expiryDate;
    private BigDecimal itemPrice;

    public Inventory(String categoryName, String categoryId, String itemName, String itemID, int itemQty, boolean inStock, String expiryDate, BigDecimal itemPrice) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemQty = itemQty;
        this.inStock = inStock;
        this.expiryDate = expiryDate;
        this.itemPrice = itemPrice;
    }

   

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
    public void deleteCategoryName() {
        /** 
        *
        *
        */
        
    }
     @Override
    public String toString() {
        return "Inventory{" + "categoryName=" + categoryName + ", categoryId=" + categoryId + ", itemName=" + itemName + ", itemID=" + itemID + ", itemQty=" + itemQty + ", inStock=" + inStock + ", expiryDate=" + expiryDate + ", itemPrice=" + itemPrice + '}';
    }
}
