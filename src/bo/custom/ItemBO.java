package bo.custom;

import Dto.CustomerDTO;
import Dto.ItemDTO;
import bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
ArrayList<ItemDTO> getAllItem() throws SQLException,ClassNotFoundException;

    boolean addItem(ItemDTO itemDTO) throws SQLException,ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException,ClassNotFoundException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException,ClassNotFoundException;

    boolean ifItemExist(String code) throws SQLException,ClassNotFoundException;

    String generateNewID() throws SQLException,ClassNotFoundException;

}
