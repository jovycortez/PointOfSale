package PointOfSale;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Jovy
 */
public class TimeCard {

    private Employee employeeTime = new Employee("Simmigon", "Flagg", "sf0906");
    private boolean isClockedIn;
    private int EmployeeIdNumber;
    private long clockIn;
    private long clockOut;
    private Date date;

    @Override
    public String toString() {
        return "TimeCard{" + "employeeTime=" + employeeTime + ", isClockedIn=" + isClockedIn + ", EmployeeIdNumber=" + EmployeeIdNumber + ", clockIn=" + clockIn + ", clockOut=" + clockOut + '}';
    }

    public Employee getEmployeeTime() {
        return employeeTime;
    }

    public void setEmployeeTime(Employee employeeTime) {
        this.employeeTime = employeeTime;
    }

    public boolean isClockedIn() {
        return isClockedIn;
    }

    public void setIsClockedIn(boolean isClockedIn) {
        this.isClockedIn = isClockedIn;
    }

    public int getEmployeeIdNumber() {
        return EmployeeIdNumber;
    }

    public void setEmployeeIdNumber(int EmployeeIdNumber) {
        this.EmployeeIdNumber = EmployeeIdNumber;
    }

    public long getClockIn() {
        return clockIn;
    }

    public void setClockIn(long clockIn) {
        
        if (!isClockedIn) {
            //This might be a database search
            isClockedIn = true;
            //Verify from the database if userID is matched and if not clocked in
            this.clockIn = clockIn;
        }else{
            JOptionPane.showMessageDialog(null,"Already Clocked in");
        }
        
    }

    public long getClockOut() {
        return clockOut;
    }

    public void setClockOut(long clockOut) {
        this.clockOut = clockOut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

}
