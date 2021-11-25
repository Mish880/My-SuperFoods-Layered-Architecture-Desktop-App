package bo.custom.impl;

import Dao.DAOFactory;
import Dao.custom.*;
import Db.DBConnection;
import Dto.CustomerDTO;
import Dto.ItemDTO;
import Dto.OderDTO;
import Dto.OrderdetailDTO;
import Entity.Customer;
import Entity.Item;
import Entity.OrderDetails;
import Entity.Orders;
import bo.custom.PurchaseOrderBO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);



    @Override
    public boolean purchaseOrder(OderDTO dto) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        connection = DBConnection.getDbConnection().getConnection();
        boolean orderAvailable = orderDAO.ifOrderExist(dto.getOid());
        /*if order id is allready to exist*/
       if (orderAvailable){
           return false;
       }
       connection.setAutoCommit(false);
       /*Add Order*/
        Orders order = new Orders(dto.getOid(),dto.getCustomerID(),dto.getDate(),dto.getTime(),dto.getCost());
        boolean orderAdded = orderDAO.add(order);
        if (!orderAdded){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
      for(OrderdetailDTO detail : dto.getOrderDetail()) {
          OrderDetails orderDetailDTO = new OrderDetails(detail.getItemCode(),detail.getOid(),detail.getQty(),detail.getUnitPrice());
          boolean orderDetailsAdded = orderDetailDAO.add(orderDetailDTO);
          if (!orderDetailsAdded) {
              connection.rollback();
              connection.setAutoCommit(true);
              return false;
          }
          //Search & Update Item
          Item search = itemDAO.search(detail.getItemCode());
          search.setQtyOnHand(search.getQtyOnHand() - detail.getQty());
          boolean update = itemDAO.update(search);
          if (!update) {
              connection.rollback();
              connection.setAutoCommit(true);
              return false;
          }
      }
          //Exception is ok Taransction is ok
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for(Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getId(),c.getTitle(),c.getName(),c.getAddress(),c.getCity(),c.getProvince(),c.getPostalcode()));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item : all) {
            allItems.add(new ItemDTO(item.getCode(),item.getDescription(),item.getPacksize(),item.getQtyOnHand(),item.getUnitprice()));
        }
        return allItems;
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(code);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getPacksize(),item.getQtyOnHand(),item.getUnitprice());
    }

    @Override
    public boolean ifitemExist(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.ifItemExist(code);
    }

    @Override
    public boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.ifCustomerExist(id);
    }

    @Override
    public CustomerDTO searchCustomer(String s) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(s);
        return new CustomerDTO(c.getId(),c.getTitle(),c.getName(),c.getAddress(),c.getCity(),c.getProvince(),c.getPostalcode());
    }
}
