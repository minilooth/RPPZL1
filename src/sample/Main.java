package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

public class Main extends Application {
    private static Session session;

    @Override
    public void start(Stage primaryStage) throws Exception{
        session = HibernateSessionFactory.buildSessionFactory().openSession();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Meню");
        primaryStage.setScene(new Scene(root, 500, 407));
        primaryStage.show();
    }

    public static Session getSession() {
        return session;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
