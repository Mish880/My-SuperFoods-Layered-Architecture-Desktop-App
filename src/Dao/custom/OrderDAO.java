package Dao.custom;

import Dao.CrudDAO;
import Entity.Orders;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Orders, String> {
    boolean ifOrderExist(String oid) throws SQLException,ClassNotFoundException;
    String generateNewOrderId() throws SQLException,ClassNotFoundException;
}
