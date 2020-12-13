package com.carmgmt.controller;


import com.carmgmt.Dao.UserDetailsDao;
import com.carmgmt.Main;
import com.carmgmt.model.UserDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    private PreparedStatement preparedStatement;

    public static int userId;
    

    public void landNewUserPage() throws IOException {
        Parent root = (Parent) FXMLLoader.load(Main.class.getResource("/com/carmgmt/view/registration.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent actionEvent) throws SQLException, IOException {
        Scene scene;
        Parent root;
        Stage primaryStage;
        String userName = this.userName.getText();
        String password = this.password.getText();
        if(userName.equalsIgnoreCase("") && password.equalsIgnoreCase("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("please enter username and password");
            alert.showAndWait();
        }else if (userName.equalsIgnoreCase("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("please enter username");
            alert.showAndWait();
        }else if(password.equalsIgnoreCase("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("please enter password");
            alert.showAndWait();
        }
        String flag ="false";
        String role = null;
        UserDetailsDao userDetailsDao = new UserDetailsDao();
        List<UserDetails> userDetailsList = userDetailsDao.userValidation(userName,password);
        if (userDetailsList.size() > 0){
            for (UserDetails userDetails : userDetailsList) {
                if (userName.equalsIgnoreCase(userDetails.getUserName()) &&
                        password.equalsIgnoreCase(userDetails.getPassword())) {
                    flag = "true";
                    role = userDetails.getRole();
                    userId=userDetails.getUserId();
                }
                else {
                    flag = "false";
                }
            }
            if (role.equalsIgnoreCase("1")){
                root = (Parent)FXMLLoader.load(Main.class.getResource("/com/carmgmt/view/employee.fxml"));
                primaryStage = new Stage();
                scene = new Scene(root, 600.0D, 600.0D);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            if (role.equalsIgnoreCase("2")){
                root = (Parent)FXMLLoader.load(Main.class.getResource("/com/carmgmt/view/customer.fxml"));
                primaryStage = new Stage();
                scene = new Scene(root, 600.0D, 600.0D);
                primaryStage.setScene(scene);
                primaryStage.show();
            }

            if (flag.equalsIgnoreCase("false") || flag == null){

            }

        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void close() {
        System.exit(0);
    }
}
