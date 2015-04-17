
package PointOfSale;

import java.util.Date;

/**
 *
 * @author Jovy
 */
public class TestTimeCard {

    public static void main(String[] args) {
        Date date = new Date();
        /**
         * All of this is Database INSERT Into Get the employee name and id
         * using the empId from the database. Then, check to see if that
         * employee isclockin That means check the database to see if that have
         * a clock in time if not set the time The Time from the system makes it
         * easier to count hours and minutes System.currentTimeMillis(); This
         * would be in the Database This can be part of the Display alone with a
         * running clock Date getDate = new Date();
         *
         */
        TimeCard empTimeCard = new TimeCard();
        System.out.println("Is this employee clocked in: " + empTimeCard.isClockedIn());
        empTimeCard.setIsClockedIn(false);
        System.out.println("Is this employee clocked in now: " + empTimeCard.isClockedIn());
        empTimeCard.setClockIn(System.currentTimeMillis());
        empTimeCard.setDate();
        System.out.println("Clocked in at: " + empTimeCard.getClockIn());
        System.out.println("Date Today's date: " + empTimeCard.getDate());
        System.out.println("These two times are the same. "
                + "\none is System.currentTimeMillis() and the other one is a Date Object.");

        System.out.println("How about now?");
        System.out.println("Is this employee clocked in now: " + empTimeCard.isClockedIn());

        
                    
        
        /**
         * Use A SELECT Statement to get the empID INSERT INTO Clockin Then
         * INSERT Clockout HoursOfWork At the Same Time.
         */
    }
}
