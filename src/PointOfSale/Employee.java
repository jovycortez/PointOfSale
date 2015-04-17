/**Make changes to fit the project
 */
package PointOfSale;

/**
 *
 * @author Jovy
 */
public class Employee {

    public Employee(String firstName, String LastName, String empId) {
        this.firstName = firstName;
        this.LastName = LastName;
        this.empId = empId;
    }

  
    private String firstName;
    private String LastName;
    private String empId;    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
      @Override
    public String toString() {
        return "Employee{" +" Name=" + firstName +" " + LastName + ", empId=" + empId + '}';
    }
}
