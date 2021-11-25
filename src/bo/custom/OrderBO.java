package bo.custom;

import Dto.ItemDTO;
import Dto.OderDTO;
import bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    ArrayList<OderDTO> getAllOrder() throws SQLException,ClassNotFoundException;
}
