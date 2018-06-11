package game.launch;

import java.io.File;
import java.net.URL;

import game.controller.Taskmaster;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader();
            URL url = new File("src/game/vue/map.fxml").toURI().toURL();
            loader.setLocation(url);
            System.out.println(loader.getLocation());
            BorderPane root = new BorderPane();
            root = loader.load();
            Scene scene = new Scene(root,1200,800);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(true);
            primaryStage.resizableProperty();
            primaryStage.setTitle("The Legend of Zelda");
            primaryStage.getIcons().add(new Image("file:src/game/resources/icons/compass.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}