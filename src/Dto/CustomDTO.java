package Dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class CustomDTO implements Serializable {

      private String orderId;
      private LocalDate date;
      private LocalTime time;
      private String customerID;
      private String itemCode;
      private int qty;
      private double unitPrice;

    public CustomDTO() {
    }

    public CustomDTO(String orderId, LocalDate date, LocalTime time, String customerID, String itemCode, int qty, double unitPrice) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setTime(time);
        this.setCustomerID(customerID);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomDTO{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", orderDate=").append(date);
        sb.append(", orderTime=").append(time);
        sb.append(", customerId='").append(customerID).append('\'');
        sb.append(", itemCode='").append(itemCode).append('\'');
        sb.append(", qty=").append(qty);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append('}');
        return sb.toString();

    }
}
