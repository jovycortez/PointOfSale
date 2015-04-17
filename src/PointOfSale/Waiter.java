/**
 * Make changes to fit the project
 */
package PointOfSale;

public class Waiter extends Employee {

    @Override
    public String toString() {
        return "Waiter:" + super.toString();
    }

    private String waiterId;

    public Waiter(String firstName, String LastName, String empId) {
        super(firstName, LastName, empId);
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public Waiter() {
        super(null, null, null);
    }

    void Waiter() {
        
    }

}
