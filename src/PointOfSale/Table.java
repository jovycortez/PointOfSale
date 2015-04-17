/**
 * Make changes to fit the project
 */
package PointOfSale;

/**
 *
 * @author Jovy
 */
import java.math.BigDecimal;
public class Table extends Order {

    private String tableId;
    private String partyNumber;
    private boolean occupied;

    //  Order order;
    public Table(String tableId, String partyNumber, boolean occupied, String itemName, BigDecimal itemPrice, int OrderNumber) {
        super(itemName, itemPrice);
        this.tableId = tableId;
        this.partyNumber = partyNumber;
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "Table:\t"
                + tableId + "\npartyNumber:\t"
                + partyNumber + "\noccupied:\t"
                + occupied + "\norder:\t";//+ order +
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getPartyNumber() {
        return partyNumber;
    }

    public void setPartyNumber(String partyNumber) {
        this.partyNumber = partyNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
   

}
