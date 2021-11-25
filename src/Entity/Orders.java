package Entity;


import java.time.LocalDate;
import java.time.LocalTime;

public class Orders {
      private String oid;
      private String customerID;
      private LocalDate date;
      private LocalTime time;
      private double cost;

    public Orders() {
    }

    public Orders(String oid, String customerID, LocalDate date, LocalTime time, double cost) {
        this.setOid(oid);
        this.setCustomerID(customerID);
        this.setDate(date);
        this.setTime(time);
        this.setCost(cost);
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
