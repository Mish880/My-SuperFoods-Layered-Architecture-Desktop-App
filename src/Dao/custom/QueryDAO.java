package Dao.custom;

import Dao.SuperDAO;
import Dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomDTO> getOrderDetailsFormOrderID(String oid) throws SQLException,ClassNotFoundException;
}
