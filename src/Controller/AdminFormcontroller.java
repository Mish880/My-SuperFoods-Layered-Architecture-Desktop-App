package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminFormcontroller {

    public AnchorPane InContext1;


    public void btnViewOrderFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../Views/LoadAllOrderDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        InContext1.getChildren().add(load);
    }

    public void OpenStartupOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../Views/Startup.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) InContext1.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
