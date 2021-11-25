package Dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OderDTO implements Serializable {

       private String oid;
       private String customerID;
       private LocalDate date;
       private LocalTime time;
       private double cost;
       private List<OrderdetailDTO> orderDetail;

    public OderDTO(String oid, String customerID, LocalDate date, LocalTime time, double cost) {
        this.oid = oid;
        this.customerID = customerID;
        this.date = date;
        this.time = time;
        this.cost = cost;
    }

    public OderDTO(String oid, String customerID, LocalDate date, LocalTime time, double cost, List<OrderdetailDTO> orderDetail) {
        this.oid = oid;
        this.customerID = customerID;
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.orderDetail = orderDetail;
    }

    public OderDTO(String oid, String customerID, LocalDate date, LocalTime time) {
        this.setOid(oid);
        this.setCustomerID(customerID);
        this.setDate(date);
        this.setTime(time);
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

    public List<OrderdetailDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderdetailDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "OderDTO{" +
                "oid='" + oid + '\'' +
                ", customerID='" + customerID + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", cost=" + cost +
                '}';
    }
}
