package Dao.custom.impl;

import Dao.CrudUtil;
import Dao.custom.ItemDAO;
import Entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean ifItemExist(String code) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeQuery("SELECT Code FROM item WHERE Code=?",code).next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT Code FROM item ORDER BY Code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("Code");
            int newItemId = Integer.parseInt(id.replace("I","")) +1;
            return String.format("I%03d",newItemId);

        }else{
            return "I001";
        }
    }

    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO item (Code,Description,PackSize,QtyOnHand,UnitPrice) VALUES (?,?,?,?,?)",item.getCode(),item.getDescription(),item.getPacksize(),item.getQtyOnHand(),item.getUnitprice());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM item WHERE Code=?",code);
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE item SET description=? , PackSize=? , QtyOnHand=? ,UnitPrice=? WHERE Code=?",item.getDescription(),item.getPacksize(),item.getQtyOnHand(),item.getUnitprice(),item.getCode());
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM item WHERE Code=?",code);
        rst.next();
        return new Item(code,rst.getString("description"),rst.getString("PackSize"),rst.getInt("QtyOnHand"),rst.getDouble("UnitPrice"));
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM item");
        while (rst.next()) {
            allItems.add(new Item(rst.getString("Code"),rst.getString("description"),rst.getString("PackSize"),rst.getInt("QtyOnHand"),rst.getDouble("UnitPrice")));
        }
        return allItems;
    }
}
