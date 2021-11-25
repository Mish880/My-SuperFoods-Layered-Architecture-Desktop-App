package Dao.custom.impl;

import Dao.CrudUtil;
import Dao.custom.QueryDAO;
import Dto.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomDTO> getOrderDetailsFormOrderID(String oid) throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> allData = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select ro.orderId,ro.custId,ro.orderDate,ro.time,ro.cost,od.itemCode,od.orderId,od.qty,od.price from rorder ro inner join `order detail` od on ro.orderId=od.orderId where ro.orderId=?;",oid);
        while (rst.next()) {
            allData.add(new CustomDTO(rst.getString("orderId"), LocalDate.parse(rst.getString("orderDate")), LocalTime.parse(rst.getString("time")),rst.getString("custId"),rst.getString("itemCode"),rst.getInt("qty"),rst.getDouble("price")));
        }
       return allData;
    }
}
