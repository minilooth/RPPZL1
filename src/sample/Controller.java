package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Button_addCustomer;

    @FXML
    private Button Button_listClients;

    @FXML
    private Button Button_exit;

    @FXML
    void initialize() {
        Button_listClients.setOnAction(actionEvent -> {
            Button_listClients.getScene().getWindow().hide();
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource(("/sample/customerListForm.fxml")));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        });

        Button_addCustomer.setOnAction(actionEvent -> {
            Button_addCustomer.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/aadcustomer.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        });
        Button_exit.setOnAction(actionEvent -> {
            System.exit(0);
        });
    }
}

