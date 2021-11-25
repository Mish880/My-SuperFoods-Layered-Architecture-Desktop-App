package bo.custom.impl;

import Dao.DAOFactory;
import Dao.custom.OrderDAO;
import Dto.OderDTO;
import Entity.Orders;
import bo.custom.OrderBO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ArrayList<OderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<OderDTO> allOrders = new ArrayList<>();
        ArrayList<Orders> all = orderDAO.getAll();
        for (Orders O : all) {
            allOrders.add(new OderDTO(O.getOid(),O.getCustomerID(),O.getDate(),O.getTime(),O.getCost()));
        }
      return allOrders;
    }
}
