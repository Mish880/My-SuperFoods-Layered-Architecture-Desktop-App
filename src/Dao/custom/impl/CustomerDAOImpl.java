package Dao.custom.impl;


import Dao.CrudUtil;
import Dao.custom.CustomerDAO;
import Entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT CustId FROM customer WHERE CustId=?",id).next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT CustId FROM customer ORDER BY CustId DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("CustId");
            int newCustomerId = Integer.parseInt(id.replace("C","")) + 1;
            return String.format("C%03d",newCustomerId);
        }else {
            return "C001";
        }
    }

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO customer(CustId,CustTitle,CustName,CustAddress,City,Province,PostalCode)VALUES(?,?,?,?,?,?,?)",customer.getId(),customer.getTitle(),customer.getName(),customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalcode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM customer WHERE CustId=?",id);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE customer SET CustTitle=?, CustName=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustId=?",customer.getTitle(),customer.getName(),customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalcode(),customer.getId());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE CustId=?",id);
        rst.next();
        return new Customer(id,rst.getString("CustTitle"),rst.getString("CustName"),rst.getString("CustAddress"),rst.getString("City"),rst.getString("Province"),rst.getString("PostalCode"));
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
       ArrayList<Customer> allCustomers = new ArrayList<>();
       ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer");
       while(rst.next()) {
           allCustomers.add(new Customer(rst.getString("CustId"),rst.getString("CustTitle"),rst.getString("CustName"),rst.getString("CustAddress"),rst.getString("City"),rst.getString("Province"),rst.getString("PostalCode")));
       }
       return allCustomers;
    }
}
