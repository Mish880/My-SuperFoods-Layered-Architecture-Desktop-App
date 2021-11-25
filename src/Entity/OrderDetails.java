package Entity;

public class OrderDetails {

      private String itemCode;
      private String oid;
      private int qty;
      private double unitPrice;

    public OrderDetails() {
    }

    public OrderDetails(String itemCode, String oid, int qty, double unitPrice) {
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
}
