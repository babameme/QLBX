package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    @FXML public ImageView imageUserDefault;
    @FXML public ImageView imageUser;
    @FXML public ImageView imagePass;
    @FXML public ImageView buttonLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageUserDefault.setImage(new Image((new File("assets/images/userdefault.png")).toURI().toString()));
        imageUser.setImage(new Image((new File("assets/images/user.png")).toURI().toString()));
        imagePass.setImage(new Image((new File("assets/images/pass.png")).toURI().toString()));
        buttonLogin.setImage(new Image((new File("assets/images/login.png")).toURI().toString()));
    }
}
