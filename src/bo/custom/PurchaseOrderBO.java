package bo.custom;

import Dto.CustomerDTO;
import Dto.ItemDTO;
import Dto.OderDTO;
import bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {
    boolean purchaseOrder(OderDTO dto) throws SQLException,ClassNotFoundException;

    String generateNewOrderId() throws SQLException,ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomers() throws SQLException,ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems() throws SQLException,ClassNotFoundException;

    ItemDTO searchItem(String code) throws SQLException,ClassNotFoundException;

    boolean ifitemExist(String code) throws SQLException,ClassNotFoundException;

    boolean ifCustomerExist(String id) throws SQLException,ClassNotFoundException;

    CustomerDTO searchCustomer(String s) throws SQLException,ClassNotFoundException;
}
