package Controller;

import Dao.custom.impl.LoginDAOimpl;
import Dto.LoginDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Startupcontroller {
    public AnchorPane Context1;
    public TextField username;
    public TextField password;

    public void openAllDashboardOnAction(ActionEvent actionEvent) throws IOException,SQLException, ClassNotFoundException {

        LoginDTO login = new LoginDTO (username.getText(),password.getText());
        String userType = new LoginDAOimpl().Login(login);

        if (userType.equals("Adminstrator")){
            Parent load = FXMLLoader.load(getClass().getResource("../Views/AdminForm.fxml"));
            Scene scene = new Scene(load);
            Stage stage = (Stage) Context1.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }else if (userType.equals("Cashier")){
            Parent load = FXMLLoader.load(getClass().getResource("../Views/CashierForm.fxml"));
            Scene scene = new Scene(load);
            Stage stage = (Stage) Context1.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }else{
            new Alert(Alert.AlertType.WARNING, "Please check username & Password").show();
        }
    }
}
