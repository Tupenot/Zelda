package app;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader();
            URL url = new File("src/vue/map.fxml").toURI().toURL();
            loader.setLocation(url);
            System.out.println(loader.getLocation());
            BorderPane root = new BorderPane();
            root = loader.load();
            Scene scene = new Scene(root, 1007, 782);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            primaryStage.setTitle("The Legend of Zelda");
            primaryStage.getIcons().add(new Image("file:src/ressources/Compass.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}