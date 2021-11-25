package Entity;

public class Item {

     private String code;
     private String description;
     private String packsize;
     private int qtyOnHand;
     private double unitprice;

    public Item() {
    }

    public Item(String code, String description, String packsize, int qtyOnHand, double unitprice) {
        this.setCode(code);
        this.setDescription(description);
        this.setPacksize(packsize);
        this.setQtyOnHand(qtyOnHand);
        this.setUnitprice(unitprice);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPacksize() {
        return packsize;
    }

    public void setPacksize(String packsize) {
        this.packsize = packsize;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }
}
