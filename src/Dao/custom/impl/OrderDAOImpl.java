package Dao.custom.impl;

import Dao.CrudUtil;
import Dao.custom.OrderDAO;
import Entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT orderId FROM rorder WHERE orderId =?",oid);
        return rst.next();
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT orderId FROM rorder ORDER BY orderId DESC LIMIT 1;");
        return rst.next() ? String.format("OD%03d",(Integer.parseInt(rst.getString("orderId").replace("OD",""))+ 1)) :"OD001";
    }

    @Override
    public boolean add(Orders orders) throws SQLException, ClassNotFoundException {
        System.out.println(orders.getCustomerID());
        return CrudUtil.executeUpdate("INSERT INTO rorder (orderid,custId,orderDate,time,cost) VALUES (?,?,?,?,?)",orders.getOid(),orders.getCustomerID(),orders.getDate(),orders.getTime(),orders.getCost());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Orders search(String oid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM rorder WHERE orderId=?",oid);
        rst.next();
        return new Orders(rst.getString("orderId"),rst.getString("custId"), LocalDate.parse(rst.getString("orderDate")), LocalTime.parse(rst.getString("time")),rst.getDouble("cost"));
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> allOrders = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM rorder");
        while (rst.next()){
            allOrders.add(new Orders(rst.getString("orderID"),rst.getString("custId"),LocalDate.parse(rst.getString("orderDate")),LocalTime.parse(rst.getString("time")),rst.getDouble("cost")));
        }
      return allOrders;
    }
}
