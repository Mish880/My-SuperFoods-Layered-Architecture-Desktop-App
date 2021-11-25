package Controller;

import Dto.ItemDTO;
import Views.tdm.ItemTM;
import bo.BoFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageItemformcontroller {
    public AnchorPane Context4;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtqtyonhand;
    public JFXTextField txtunitprice;
    public TableView<ItemTM> tblItems;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnAddNewItem;
  private final ItemBO itemBO = (ItemBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.ITEM);

   public void initialize(){
       tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
       tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
       tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("packSize"));
       tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
       tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
       iniUI();
       tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           btnDelete.setDisable(newValue == null);
           btnSave.setText(newValue !=null ? "Update" : "Save");
           btnSave.setDisable(newValue == null);

           if (newValue != null) {
               txtCode.setText(newValue.getCode());
               txtDescription.setText(newValue.getDescription());
               txtPackSize.setText(newValue.getPackSize());
               txtqtyonhand.setText(newValue.getQtyOnHand() + "");
               txtunitprice.setText(newValue.getUnitPrice() + "");

               txtCode.setDisable(false);
               txtDescription.setDisable(false);
               txtPackSize.setDisable(false);
               txtunitprice.setDisable(false);
           }
       });
     txtunitprice.setOnAction(event -> btnSave.fire());
     loadAllItems();
   }

    private void loadAllItems() {
     tblItems.getItems().clear();
     try {
         /*Get all items*/
         ArrayList<ItemDTO> allItems = itemBO.getAllItem();
         for (ItemDTO item : allItems) {
             tblItems.getItems().add(new ItemTM(item.getCode(),item.getDescription(),item.getPackSize(),item.getQtyOnHand(),item.getUnitprice()));
         }
     } catch (SQLException throwables) {
         new Alert(Alert.AlertType.ERROR,throwables.getMessage()).show();
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }
   }

    private void iniUI() {

        txtCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtqtyonhand.clear();
        txtunitprice.clear();
        txtCode.setDisable(true);
        txtDescription.setDisable(true);
        txtPackSize.setDisable(true);
        txtqtyonhand.setDisable(true);
        txtunitprice.setDisable(true);
        txtCode.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
   }


    public void OpenCashierFormOnAtction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../Views/CashierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Context4.getChildren().clear();
        Context4.getChildren().add(load);
   }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String code = txtCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        String qtyOnHand = txtqtyonhand.getText();
        String unitPrice = txtunitprice.getText();

        if (!description.matches("[A-Za-z0-9]+")) {
            new Alert(Alert.AlertType.ERROR,"Invaild description").show();
            txtDescription.requestFocus();
            return;
        }else if (!packSize.matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR,"Invaild PackSize").show();
            txtPackSize.requestFocus();
            return;
        }else if (! qtyOnHand.matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR,"Invaild qty on hand").show();
            txtqtyonhand.requestFocus();
           return;
        }else if (! unitPrice.matches("^[0-9]+[.]?[0-9]*$")){
            new Alert(Alert.AlertType.ERROR,"Invaild Unit Price").show();
            txtunitprice.requestFocus();
            return;
        }
        int QtyOnHand  = Integer.parseInt(txtqtyonhand.getText());
        Double UnitPrice = new Double(txtunitprice.getText());

        if (btnSave.getText().equalsIgnoreCase("save")){
            try {
                if (existItem(code)){
                    new Alert(Alert.AlertType.ERROR,code +"already exists").show();
                }
                //Save Item
                ItemDTO dto = new ItemDTO(code,description,packSize,QtyOnHand,UnitPrice);
                itemBO.addItem(dto);

                tblItems.getItems().add(new ItemTM(code,description,packSize,QtyOnHand,UnitPrice));
            } catch (SQLException throwables) {
               new Alert(Alert.AlertType.ERROR,throwables.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try {
                if(!existItem(code)){
                    new Alert(Alert.AlertType.ERROR,"There is no such item associated with the id" +code).show();
                }
                //update Item
                ItemDTO dto = new ItemDTO(code,description,packSize,QtyOnHand,UnitPrice);
                itemBO.updateItem(dto);

                ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
                selectedItem.setDescription(description);
                selectedItem.setPackSize(packSize);
                selectedItem.setQtyOnHand(QtyOnHand);
                selectedItem.setUnitPrice(UnitPrice);
                tblItems.refresh();
            } catch (SQLException throwables) {
                new Alert(Alert.AlertType.ERROR,throwables.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        btnAddNewItem.fire();
        }

     private boolean existItem(String code) throws SQLException, ClassNotFoundException {
    return itemBO.ifItemExist(code);

   }

    private String generateNewId() {
      try{
          return itemBO.generateNewID();
      } catch (SQLException throwables) {
         new Alert(Alert.AlertType.ERROR,throwables.getMessage()).show();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }

      return "I001";
   }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
   //Delete Item
   String code = tblItems.getSelectionModel().getSelectedItem().getCode();
   try{
       if (!existItem(code)){
           new Alert(Alert.AlertType.ERROR,"There is no such idea to item assicoated with the id" +code).show();
       }
       itemBO.deleteItem(code);
       tblItems.getItems().remove(tblItems.getSelectionModel().getSelectedItem());
       tblItems.getSelectionModel().clearSelection();
       iniUI();

   } catch (SQLException throwables) {
      new Alert(Alert.AlertType.ERROR,"Can't delete the item" +code).show();
   } catch (ClassNotFoundException e) {
       e.printStackTrace();
   }
   }

    public void btnAddNewOnAction(ActionEvent actionEvent) {

        txtCode.setDisable(false);
        txtDescription.setDisable(false);
        txtPackSize.setDisable(false);
        txtqtyonhand.setDisable(false);
        txtunitprice.setDisable(false);
        txtCode.clear();
        txtCode.setText(generateNewId());
        txtDescription.clear();
        txtPackSize.clear();
        txtqtyonhand.clear();
        txtunitprice.clear();
        txtDescription.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblItems.getSelectionModel().clearSelection();


   }
}
