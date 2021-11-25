package Dao.custom.impl;

import Dao.CrudUtil;
import Dao.custom.OrderDetailDAO;
import Entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean add(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        System.out.println(orderDetails.getItemCode());
        return CrudUtil.executeUpdate("INSERT INTO `order detail`(itemCode,orderId,qty,price) VALUES (?,?,?,?)",orderDetails.getItemCode(),orderDetails.getOid(),orderDetails.getQty(),orderDetails.getUnitPrice());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }
}
