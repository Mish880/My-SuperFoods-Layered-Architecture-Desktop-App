package bo.custom;

import Dto.CustomerDTO;
import bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean addCustomer(CustomerDTO customerDTO) throws SQLException,ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException,ClassNotFoundException;

    boolean ifCustomerExist(String id) throws SQLException,ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException,ClassNotFoundException;

    String generateNewID() throws SQLException,ClassNotFoundException;



}
