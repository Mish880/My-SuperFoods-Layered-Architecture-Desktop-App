package Controller;

import Dto.OderDTO;
import Views.tdm.OrderTM;
import bo.BoFactory;
import bo.custom.OrderBO;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoadAllOrderDetailscontroller {
    public TableView<OrderTM> tblOrderForm;
    private final OrderBO orderBO = (OrderBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.ORDER);

    public void initialize(){
        tblOrderForm.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("oid"));
        tblOrderForm.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblOrderForm.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblOrderForm.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("time"));
        tblOrderForm.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("cost"));
        loadAllOrders();
    }
    private void loadAllOrders() {
        tblOrderForm.getItems().clear();
        try{
            ArrayList<OderDTO> allOrders = orderBO.getAllOrder();
            for (OderDTO oder :allOrders){
                tblOrderForm.getItems().add(new OrderTM(oder.getOid(),oder.getCustomerID(),oder.getDate(),oder.getTime(),oder.getCost()));
            }


        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR,throwables.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
