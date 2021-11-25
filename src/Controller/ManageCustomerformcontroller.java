package Controller;

import Dto.CustomerDTO;
import Views.tdm.CustomerTM;
import bo.BoFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageCustomerformcontroller {
    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    public AnchorPane Context3;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCusttitle;
    public JFXTextField txtCustName;
    public JFXTextField txtCustaddress;
    public JFXTextField txtcity;
    public JFXTextField txtcustprovince;
    public JFXTextField txtcustpostalcode;
    public TableView<CustomerTM> tblCustomers;
    public JFXButton btnAddCustomer;
    public JFXButton btnSave;
    public JFXButton btnDelete;

    public void initialize() {
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblCustomers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblCustomers.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Province"));
        tblCustomers.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        initUI();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);
            if (newValue != null) {
                txtCustomerId.setText(newValue.getId());
                txtCusttitle.setText(newValue.getTitle());
                txtCustName.setText(newValue.getName());
                txtCustaddress.setText(newValue.getAddress());
                txtcity.setText(newValue.getCity());
                txtcustprovince.setText(newValue.getProvince());
                txtcustpostalcode.setText(newValue.getPostalCode());
                txtCustomerId.setDisable(false);
                txtCusttitle.setDisable(false);
                txtCustName.setDisable(false);
                txtCustaddress.setDisable(false);
                txtcity.setDisable(false);
                txtcustprovince.setDisable(false);
                txtcustpostalcode.setDisable(false);

            }
        });
        txtcustpostalcode.setOnAction(event -> btnSave.fire());
        loadAllCustomers();
    }

    private void loadAllCustomers() {

        tblCustomers.getItems().clear();

        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomer();
            for (CustomerDTO customer : allCustomers) {
                tblCustomers.getItems().add(new CustomerTM(customer.getId(), customer.getTitle(), customer.getName(), customer.getAddress(), customer.getCity(), customer.getProvince(), customer.getPostalcode()));
            }
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR, throwables.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void openCashierFormOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../Views/CashierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Context3.getChildren().clear();
        Context3.getChildren().add(load);

    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {

        txtCustomerId.setDisable(false);
        txtCusttitle.setDisable(false);
        txtCustName.setDisable(false);
        txtCustaddress.setDisable(false);
        txtcity.setDisable(false);
        txtcustprovince.setDisable(false);
        txtcustpostalcode.setDisable(false);
        txtCustomerId.clear();
        txtCustomerId.setText(generateNewId());
        txtCusttitle.clear();
        txtCustName.clear();
        txtCustaddress.clear();
        txtcity.clear();
        txtcustprovince.clear();
        txtcustpostalcode.clear();
        txtCustName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblCustomers.getSelectionModel().clearSelection();


    }

    private String generateNewId() {

        try {
            return customerBO.generateNewID();
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR, "Can't Create a new Customer Id" + throwables.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (tblCustomers.getItems().isEmpty()) {
            return "C001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C%3d", newCustomerId);
        }

    }

    private String getLastCustomerId() {
        List<CustomerTM> tempCustomerList = new ArrayList<>(tblCustomers.getItems());
        Collections.sort(tempCustomerList);
        return tempCustomerList.get(tempCustomerList.size() - 1).getId();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String id = txtCustomerId.getText();
        String title = txtCusttitle.getText();
        String name = txtCustName.getText();
        String address = txtCustaddress.getText();
        String city = txtcity.getText();
        String province = txtcustprovince.getText();
        String postalcode = txtcustpostalcode.getText();
        if (!name.matches("[A-Za-z]+")) {
            new Alert(Alert.AlertType.ERROR, "Invaild name").show();
            txtCustName.requestFocus();
            return;
        } else if (!title.matches("[A-Za-z]+")) {
            new Alert(Alert.AlertType.ERROR, "Invaild title").show();
            txtCusttitle.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 charactors long").show();
            txtCustaddress.requestFocus();
            return;
        } else if (!city.matches("[A-Za-z]+")) {
            new Alert(Alert.AlertType.ERROR, "Invaild city").show();
            txtcity.requestFocus();
            return;
        } else if (!province.matches("[A-Za-z]+")) {
            new Alert(Alert.AlertType.ERROR, "Invaild city").show();
            txtcustprovince.requestFocus();
            return;
        } else if (!postalcode.matches("[A-Za-z]+")) {
            new Alert(Alert.AlertType.ERROR, "Invaild city").show();
            txtcustpostalcode.requestFocus();
            return;
        }

        /*Save Customer*/
        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (exitCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + "already Exists").show();
                }
                CustomerDTO customerDTO = new CustomerDTO(id, title, name, address, city, province, postalcode);
                customerBO.addCustomer(customerDTO);
                tblCustomers.getItems().add(new CustomerTM(id, title, name, address, city, province, postalcode));
            } catch (SQLException throwables) {
                new Alert(Alert.AlertType.ERROR, "Failed to save Customer" + throwables.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {


            /* Update Customer */
            try {
                if (!exitCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no customer with the id" + id).show();
                }
                CustomerDTO customerDTO = new CustomerDTO(id, title, name, address, city, province, postalcode);
                customerBO.updateCustomer(customerDTO);
            } catch (SQLException throwables) {
                new Alert(Alert.AlertType.ERROR, "Can't update Customer" + id + throwables.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            CustomerTM selectedCustomer = (CustomerTM) tblCustomers.getSelectionModel().getSelectedItems();
            selectedCustomer.setName(name);
            selectedCustomer.setAddress(address);
            tblCustomers.refresh();
        }
        btnAddCustomer.fire();
    }

    private boolean exitCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.ifCustomerExist(id);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

         String id = tblCustomers.getSelectionModel().getSelectedItem().getId();

         try{
             if(!exitCustomer(id)) {
                 new Alert(Alert.AlertType.ERROR,"There is no such customer associated with the id" +id).show();
             }
             customerBO.deleteCustomer(id);
             tblCustomers.getItems().remove(tblCustomers.getSelectionModel().getSelectedItem());
             tblCustomers.getSelectionModel().clearSelection();
             initUI();
         } catch (SQLException throwables) {
             new Alert(Alert.AlertType.ERROR,"Faild to delete customer"+ id).show();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }


    }

    private void initUI() {
        txtCustomerId.clear();
        txtCusttitle.clear();
        txtCustName.clear();
        txtCustaddress.clear();
        txtcustprovince.clear();
        txtcustpostalcode.clear();
        txtCustomerId.setDisable(false);
        txtCusttitle.setDisable(false);
        txtCustName.setDisable(false);
        txtCustaddress.setDisable(false);
        txtcustprovince.setDisable(false);
        txtcustpostalcode.setDisable(false);
        btnSave.setDisable(true);
        btnSave.setDisable(true);
    }
}
