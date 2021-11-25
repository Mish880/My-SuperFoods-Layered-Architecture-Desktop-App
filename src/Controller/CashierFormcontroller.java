package Controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CashierFormcontroller {
    public AnchorPane Context2;
    public ImageView imgCustomer;
    public ImageView imgItem;
    public ImageView imgOrder;
    public ImageView imgViewOrders;
    public Label lblMenu;
    public Label lblDescription;


    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000),Context2);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
    public void openLoginFormOnAction(ActionEvent actionEvent) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("../Views/Startup.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) Context2.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgCustomer": root = FXMLLoader.load(this.getClass().getResource("/Views/ManageCustomerform.fxml"));break;
                case "imgItem": root = FXMLLoader.load(this.getClass().getResource("/Views/ManageItemform.fxml"));break;
                case "imgOrder": root = FXMLLoader.load(this.getClass().getResource("/Views/Placeorderform.fxml"));break;
                case "imgViewOrders": root = FXMLLoader.load(this.getClass().getResource("/Views/ViewOrderDetailform.fxml"));break;
            }

            if (root != null) {
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) this.Context2.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350),scene.getRoot());
                tt.setFromX(-scene.getWidth());
                tt.setToX(0);
                tt.play();
            }
        }
    }


    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            switch (icon.getId()) {
                case "imgCustomer":
                    lblMenu.setText("Manage Customers");
                    lblDescription.setText("      Click to add, edit, delete, search or view customers");
                    break;
                case "imgItem":
                    lblMenu.setText("Manage Items");
                    lblDescription.setText("      Click to add, edit, delete, search or view items");
                    break;
                case "imgOrder":
                    lblMenu.setText("Place Orders");
                    lblDescription.setText("      Click here if you want to place a new order");
                    break;
                case "imgViewOrders":
                    lblMenu.setText("Search Orders");
                    lblDescription.setText("                 Click if you want to search orders");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }


    public void playMouseExitAnimation(MouseEvent mouseEvent) {

         if (mouseEvent.getSource() instanceof ImageView) {
             ImageView icon = (ImageView) mouseEvent.getSource();
             ScaleTransition scaleT = new ScaleTransition(Duration.millis(200),icon);
             scaleT.setToX(1);
             scaleT.setToY(1);
             scaleT.play();

             icon.setEffect(null);
             lblMenu.setText("Welcome");
             lblDescription.setText("Please select one of the above  task to complete");
         }

    }
}
