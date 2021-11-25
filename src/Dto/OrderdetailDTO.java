package Dto;


import java.io.Serializable;

public class OrderdetailDTO implements Serializable {

       private String itemCode;
       private String oid;
       private int qty;
       private double unitPrice;

    public OrderdetailDTO() {
    }

    public OrderdetailDTO(String itemCode, String oid, int qty, double unitPrice) {
        this.setItemCode(itemCode);
        this.setOid(oid);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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
        final StringBuffer sb = new StringBuffer("OrderDetailDTO{");
        sb.append(", itemCode='").append(itemCode).append('\'');
        sb.append("oid='").append(oid).append('\'');
        sb.append(", qty=").append(qty);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append('}');
        return sb.toString();
    }
}



 /*"itemCode='" + itemCode + '\'' +
          ", oid='" + oid + '\'' +
          ", qty=" + qty +
          ", unitPrice=" + unitPrice +
          '}';*/