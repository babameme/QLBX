package sample;

import data.DataAccess;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    /*** FXML variables ***/
    @FXML public ImageView imageUserDefault;
    @FXML public ImageView imageUser;
    @FXML public ImageView imagePass;
    @FXML public ImageView buttonLogin;
    @FXML public TextField txtUsername;
    @FXML public TextField txtPassword;

    /*** Public variables ***/
    public Connection sql;
    public Statement comm;
    public String strSql;

    public void login(){
        String user = txtUsername.getText();
        if (user == null) user = "";

        String pass = txtPassword.getText();
        if (pass == null) pass = "";

        try {
            strSql = "SELECT * FROM users WHERE username = \"" + user + "\" AND pass = \"" + pass +"\"";
            ResultSet rs = comm.executeQuery(strSql);
            if (!rs.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Sai tên tài khoản hoặc mật khẩu!");
                alert.setContentText("Vui lòng kiểm tra lại");
                alert.showAndWait();
            }
            else{
                String stat = rs.getString("trangthai");
                if (stat == "online"){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Tài khoản đã đăng nhập!");
                    alert.setContentText("Vui lòng kiểm tra lại");
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Đăng nhập thành công");
                    alert.setContentText("Chào mừng " + user + " đến với C500 Chat");
                    alert.showAndWait();
                    // TODO: Fix Database to online
                    strSql = "UPDATE users SET trangthai = \"offline\" WHERE username = \"" + user + "\"";
                    comm.executeUpdate(strSql);
                    // TODO: Add Main form static user variable
                    // TODO: Show Main Form
//                    Stage stage = (Stage) buttonConnect.getScene().getWindow();
//                    stage.close();
//
//                    Parent rootChat = FXMLLoader.load(getClass().getResource("ChatUI.fxml"));
//                    stage.setTitle("C500 Chat");
//                    stage.setScene(new Scene(rootChat));
//                    stage.setResizable(false);
//                    stage.show();
                }
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Kết nối cơ sở dữ liệu bị lỗi");
            alert.setContentText("Vui lòng kiểm tra lại");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    public void loginMouseClicked(MouseEvent event){
        login();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageUserDefault.setImage(new Image((new File("assets/images/userdefault.png")).toURI().toString()));
        imageUser.setImage(new Image((new File("assets/images/user.png")).toURI().toString()));
        imagePass.setImage(new Image((new File("assets/images/pass.png")).toURI().toString()));
        buttonLogin.setImage(new Image((new File("assets/images/login.png")).toURI().toString()));

        try{
            sql = DataAccess.connection();
            comm = sql.createStatement();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Kết nối cơ sở dữ liệu bị lỗi");
            alert.setContentText("Vui lòng kiểm tra lại");
            alert.showAndWait();
        }
    }
}
