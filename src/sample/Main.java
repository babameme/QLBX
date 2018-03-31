package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
        primaryStage.setTitle("LoginForm");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image((new File("assets/images/iconLoginForm.png")).toURI().toString()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
