
package control;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Repair.java
 * @author Team007
 * @version 1.0
 *
 * Handles everything related to repairs.
 */
public class Repair {
    private int repair_id;
    private LocalDate date_sent;
    private String before_desc;
    private LocalDate date_received;
    private String after_desc;
    private double price;
    private int bike_id;

    protected Repair(String dateSent, String beforeDesc, int bikeId){
            this.repair_id = -1; //use the database to set repairId

            this.date_sent = toDate(dateSent);
            this.before_desc = beforeDesc;
            this.date_received = null;
            this.after_desc = null;
            this.price = 0;
            this.bike_id = bikeId;
    }//end constructor

    protected Repair(String dateReceived, String afterDesc, double price, int bikeId){
            this.repair_id = -1; //use the database to set repairId
            this.date_received = toDate(dateReceived);
            this.after_desc = afterDesc;
            this.price = price;
            this.bike_id = bikeId;
    }//end constructor

    protected Repair(String dateSent, String beforeDesc, String dateReceived, String afterDesc, double price, int bikeId){
            this.repair_id = -1; //use the database to set repairId
            this.date_sent = toDate(dateSent);
            this.before_desc = beforeDesc;
            this.date_received = toDate(dateReceived);
            this.after_desc = afterDesc;
            this.price = price;
            this.bike_id = bikeId;
    }//end constructor


    public int getRepair_id(){
        return repair_id;
    }

    public LocalDate getDateReceived() {
        return date_received;
    }

    public LocalDate getDateSent() {
        return date_sent;
    }

    public String getBeforeDesc() {
        return before_desc;
    }

    public String getAfterDesc() {
        return after_desc;
    }

    public double getPrice() {
        return price;
    }

    public int getBikeId() {
        return bike_id;
    }

    public void setRepairId(int id){
        this.repair_id = id;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setAfterDesc(String after_desc) {
        this.after_desc = after_desc;
    }

    public void setBeforeDesc(String before_desc) {
        this.before_desc = before_desc;
    }

    public void setBikeId(int bike_id) {
        this.bike_id = bike_id;
    }

    public void setDateSent(String dateSent) {
        this.date_sent = toDate(dateSent);
    }

    public void setDateReceived(String dateReceived) {
        this.date_received = toDate(dateReceived);
    }

    /**
     * Formats a date in string format to LocalDate.
     * @param date This is the String that will get parsed LocalDate.
     * @return LocalDate This is the formatted date.
     */
    public LocalDate toDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }



    /**
     * Override of the standard equals()-method to compare the information about two Repair-objects.
     * @param o Object.java object.
     * @return boolean Based on whether the object is equal or not.
     */
    @Override
    public boolean equals(Object o){
        if (o == null) { throw new IllegalArgumentException("The object you are comparing cannot be null"); }
        if (!(o instanceof Repair)) {
            return false;
        }

        Repair r = (Repair) o;

        return (((Repair) o).getBikeId() == r.getBikeId() && (((Repair) o).getAfterDesc().equals(r.getAfterDesc())
                && (((Repair)o).getBeforeDesc()).equals(r.getBeforeDesc()) && (((Repair)o).getDateSent()).equals(r.getDateSent())
                && (((Repair)o).getDateReceived()).equals(r.getDateReceived()) &&(((Repair)o).getPrice()) == r.getPrice()));

    }
    /**
     * Override of the standard toString()-method to make it list out the information about a repair
     * @return String of the repair
     */
    @Override
    public String toString(){
        return "Repair ID: " + repair_id
                +"\nBike ID: " + bike_id
                + "\nDate sent: " + date_sent
                + "\nDescription before: " + before_desc
                + "\nDate received: " + date_received
                + "\nDescrifton after: " + after_desc
                + "\nPrice: " + price;
    }//end method
}//end class