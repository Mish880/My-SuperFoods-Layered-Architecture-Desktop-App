package Controller;

import Dto.CustomerDTO;
import Dto.ItemDTO;
import Dto.OderDTO;
import Dto.OrderdetailDTO;
import Views.tdm.OrderDetailsTM;
import bo.BoFactory;
import bo.custom.PurchaseOrderBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Placeorderformcontroller {
    private final PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.PURCHASE_ORDER);

    public AnchorPane Context5;
    public Label lblOId;
    public Label lblDate;
    public Label lblTime;
    public ComboBox <String>cmbCustomerId;
    public ComboBox <String>cmbItemCode;
    public JFXTextField txtCustomerName;
    public JFXTextField txtItemDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public Label lblTotal;
    public JFXButton btnPlaceOrder;
    public TableView<OrderDetailsTM> tblOrderDetails;
    private String orderId;

    public void initialize() throws SQLException,ClassNotFoundException{
        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Code"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<OrderDetailsTM,Button>lastCol = (TableColumn<OrderDetailsTM,Button>) tblOrderDetails.getColumns().get(5);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(event -> {
                tblOrderDetails.getItems().remove(param.getValue());
                tblOrderDetails.getSelectionModel().clearSelection();
                calculateTotal();
                enableOrDisablePlaceOrderButten();
            });
             return new ReadOnlyObjectWrapper<>(btnDelete);
        });
        orderId = generateNewOrderId();
        lblOId.setText("Order ID :" +orderId);
        lblDate.setText(LocalDate.now().toString());
        lblTime.setText(LocalTime.now().toString());
        btnPlaceOrder.setDisable(true);
        txtCustomerName.setFocusTraversable(false);
        txtCustomerName.setEditable(false);
        txtItemDescription.setFocusTraversable(false);
        txtItemDescription.setEditable(false);
        txtUnitPrice.setFocusTraversable(false);
        txtUnitPrice.setEditable(false);
        txtQtyOnHand.setFocusTraversable(false);
        txtQtyOnHand.setEditable(false);
        txtQty.setOnAction(event -> btnSave.fire());
        txtQty.setEditable(false);
        btnSave.setDisable(true);

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisablePlaceOrderButten();

            if (newValue != null) {
                try {
                    try {
                        if (!existCustomer(newValue + "")) {
                            /*There no Such as Customer*/
                            new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id" + newValue + "").show();
                        }
                        /*Search Customer*/
                        CustomerDTO customerDTO = purchaseOrderBO.searchCustomer(newValue + "");
                        txtCustomerName.setText(customerDTO.getName());
                    } catch (SQLException throwables) {
                        new Alert(Alert.AlertType.ERROR, "I Can't find the customer" + newValue + "" + throwables).show();
                    }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                txtCustomerName.clear();
            }
        });
           cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
               txtQty.setEditable(newItemCode !=null);
               btnSave.setDisable(newItemCode == null);
             if (newItemCode !=null){
                 try{
                     if (!existItem(newItemCode+"")){
                         /*throw new NotFoundException*/
                     }
                     /*Find Item*/
                     ItemDTO item = purchaseOrderBO.searchItem(newItemCode+"");
                     txtItemDescription.setText(item.getDescription());
                     txtUnitPrice.setText(item.getUnitprice() +"");
                     Optional<OrderDetailsTM> optionalOrderDetail = tblOrderDetails.getItems().stream().filter(detail ->detail.getCode().equals(newItemCode)).findFirst();
                     txtQtyOnHand.setText((optionalOrderDetail.isPresent() ? item.getQtyOnHand() - optionalOrderDetail.get().getQty() : item.getQtyOnHand()) + "");


                 } catch (SQLException throwables) {
                     throwables.printStackTrace();
                 } catch (ClassNotFoundException e) {
                     e.printStackTrace();
                 }
             }else{
                 txtItemDescription.clear();
                 txtQty.clear();
                 txtQtyOnHand.clear();
                 txtUnitPrice.clear();
             }
           });
          tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) ->{
              if(selectedOrderDetail != null){
                  cmbItemCode.setDisable(true);
                  cmbItemCode.setValue(selectedOrderDetail.getCode());
                  btnSave.setText("Update");
                  txtQtyOnHand.setText(Integer.parseInt(txtQtyOnHand.getText()) + selectedOrderDetail.getQty() + "");
                  txtQty.setText(selectedOrderDetail.getQty() + "");
              }else{
                  btnSave.setText("Add");
                  cmbItemCode.setDisable(false);
                  cmbItemCode.getSelectionModel().clearSelection();
                  txtQty.clear();
              }
          });
          loadAllCustomerIds();
          loadAllItemCodes();

    }

    private void loadAllItemCodes() {
        try{
            ArrayList<ItemDTO> all = purchaseOrderBO.getAllItems();
            for (ItemDTO dto : all) {
                cmbItemCode.getItems().add(dto.getCode());
            }
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR,"Can't load Item Codes").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllCustomerIds() {
       try{
           ArrayList<CustomerDTO> all = purchaseOrderBO.getAllCustomers();
           for (CustomerDTO customerDTO : all) {
               cmbCustomerId.getItems().add(customerDTO.getId());
           }
       } catch (SQLException throwables) {
           new Alert(Alert.AlertType.ERROR,"Can't load customer ids").show();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
    }

    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
       return purchaseOrderBO.ifitemExist(code);
    }

    private boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return purchaseOrderBO.ifCustomerExist(id);
    }

    private String generateNewOrderId() {
       try{
           return purchaseOrderBO.generateNewOrderId();
       } catch (SQLException throwables) {
           new Alert(Alert.AlertType.ERROR,"Can't create a new order id").show();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
     return null;
    }

    private void enableOrDisablePlaceOrderButten() {
     btnPlaceOrder.setDisable(!(cmbCustomerId.getSelectionModel().getSelectedItem() != null && !tblOrderDetails.getItems().isEmpty()));
    }

    private void calculateTotal() {
      Double total = new Double(0);
      for (OrderDetailsTM detail : tblOrderDetails.getItems()) {
          total = total * (detail.getTotal());
      }
      lblTotal.setText("Total :" + total);

    }


    public void BackToHomeOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../Views/CashierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Context5.getChildren().clear();
        Context5.getChildren().add(load);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
       if (!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <=0 || Integer.parseInt(txtQty.getText()) > Integer.parseInt(txtQtyOnHand.getText())) {
           new Alert(Alert.AlertType.ERROR,"Invaild qty").show();
           txtQty.requestFocus();
           txtQty.selectAll();
           return;
       }
       String itemCode = cmbItemCode.getSelectionModel().getSelectedItem();
       String description = txtItemDescription.getText();
       Double unitPrice = new Double(txtUnitPrice.getText() + "");
       int qty = Integer.parseInt(txtQty.getText());
       Double total = unitPrice * (new Double(qty));


       boolean exists = tblOrderDetails.getItems().stream().anyMatch(detail -> detail.getCode().equals(itemCode));

       if(exists) {
           OrderDetailsTM orderDetailsTM = tblOrderDetails.getItems().stream().filter(detail -> detail.getCode().equals(itemCode)).findFirst().get();

           if (btnSave.getText().equalsIgnoreCase("Update")) {
               orderDetailsTM.setQty(qty);
               orderDetailsTM.setTotal(total);
               tblOrderDetails.getSelectionModel().clearSelection();
           } else {
                orderDetailsTM.setQty(orderDetailsTM.getQty() + qty);
                total = new Double(orderDetailsTM.getQty() * (unitPrice));
                orderDetailsTM.setTotal(total);
           }
           tblOrderDetails.refresh();
       }else {
           tblOrderDetails.getItems().add(new OrderDetailsTM(itemCode,description,qty,unitPrice,total));
       }
       cmbItemCode.getSelectionModel().clearSelection();
       cmbItemCode.requestFocus();
       calculateTotal();
       enableOrDisablePlaceOrderButten();
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        double total = 0;
        for (OrderDetailsTM item : tblOrderDetails.getItems()) {
            total += item.getQty() * item.getUnitPrice();
        }
          boolean b  = saveOrder(orderId,cmbCustomerId.getValue(),LocalDate.now(),LocalTime.now(),total,tblOrderDetails.getItems().stream().map(tm -> new OrderdetailDTO(tm.getCode(),orderId,tm.getQty(),tm.getUnitPrice()*tm.getQty())).collect(Collectors.toList()));
       if (b) {
           new Alert(Alert.AlertType.INFORMATION,"Order has been placed successfully").show();
       }else {
           new Alert(Alert.AlertType.ERROR,"Order has not been placed successfully").show();
       }
       orderId = generateNewOrderId();
       lblOId.setText("Order Id:" +orderId);
       cmbCustomerId.getSelectionModel().clearSelection();
       cmbItemCode.getSelectionModel().clearSelection();
       tblOrderDetails.getItems().clear();
       txtQty.clear();
       calculateTotal();
    }

    private boolean saveOrder(String oid, String customerID, LocalDate date, LocalTime time, double total, List<OrderdetailDTO> orderdetail) {
      try {
          OderDTO oderDTO = new OderDTO(oid,customerID,date,time,total,orderdetail);
          return purchaseOrderBO.purchaseOrder(oderDTO);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
     return false;
    }
   public ItemDTO findItem(String code) {
        try{
            return purchaseOrderBO.searchItem(code);
        } catch (SQLException throwables) {
            throw new RuntimeException("Faild to find the item" + code,throwables);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
     return null;
    }


}
