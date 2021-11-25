package Views.tdm;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrderTM {

    private String oid;
    private String customerId;
    private LocalDate date;
    private LocalTime time;
    private double cost;

    public OrderTM() {
    }

    public OrderTM(String oid, String customerId, LocalDate date, LocalTime time, double cost) {
        this.setOid(oid);
        this.setCustomerId(customerId);
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "OrderTM{" +
                "oid='" + oid + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", cost=" + cost +
                '}';
    }
}
