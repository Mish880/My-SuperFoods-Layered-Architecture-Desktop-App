package Dao.custom.impl;

import Db.DBConnection;
import Dto.LoginDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOimpl {

    public String Login(LoginDTO login) throws SQLException,ClassNotFoundException{
        PreparedStatement stm = DBConnection.getDbConnection().getConnection().prepareStatement("SELECT * FROM  Login WHERE id=? AND password=?");
        stm.setObject(1,login.getId());
        stm.setObject(2,login.getPassword());
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            String role = rst.getString(3);
            return role;
        }else{
            return"";
        }
    }
}

