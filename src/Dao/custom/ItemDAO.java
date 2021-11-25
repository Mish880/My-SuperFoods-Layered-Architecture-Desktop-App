package Dao.custom;


import Dao.CrudDAO;
import Entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO <Item,String> {

    boolean ifItemExist(String code) throws SQLException , ClassNotFoundException;
    String generateNewID() throws SQLException , ClassNotFoundException;
}
