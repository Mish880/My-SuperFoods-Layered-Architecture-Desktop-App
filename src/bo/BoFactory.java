package bo;

import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.ItemBOImpl;
import bo.custom.impl.OrderBOImpl;
import bo.custom.impl.PurchaseOrderBOImpl;

public class BoFactory {
   private static BoFactory boFactory;

   private BoFactory() {
   }

    public static BoFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
     return boFactory;
   }
  public SuperBO getBO(BoTypes types){
       switch (types){
           case ITEM: return new ItemBOImpl();
           case CUSTOMER: return new CustomerBOImpl();
           case PURCHASE_ORDER: return new PurchaseOrderBOImpl();
           case ORDER: return new OrderBOImpl();
           default: return null;
       }
  }
  public enum BoTypes{
       CUSTOMER,ITEM,PURCHASE_ORDER,ORDER
  }
}
