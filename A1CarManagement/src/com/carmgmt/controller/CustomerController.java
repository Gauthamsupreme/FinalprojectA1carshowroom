package com.carmgmt.controller;

import com.carmgmt.Dao.CarDAO;
import com.carmgmt.Dao.CustomerDetailsDao;
import com.carmgmt.Main;
import com.carmgmt.model.CustomerDetails;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class CustomerController implements Initializable {

    @FXML
    private TextField customerName;

    @FXML
    private TextField customerMobileNum;

    @FXML
    private TextField email;

    @FXML
    private ListView<String> listView;
    @FXML
    private Button buyCar;

    public static String selectedCar;

    public CustomerController() {
    }

    public void enableBuyCars() throws ClassNotFoundException, SQLException {
        if (((String)this.listView.getSelectionModel().getSelectedItem()).isEmpty()) {
            this.buyCar.setDisable(true);
        } else {
            this.buyCar.setDisable(false);
        }

    }

    public void carDetails() throws IOException, ClassNotFoundException, SQLException {
        selectedCar = ((String)this.listView.getSelectionModel().getSelectedItem()).toString();
        Parent root = (Parent) FXMLLoader.load(Main.class.getResource("/com/carmgmt/view/BuyCar.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 700.0D, 600.0D);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            new CustomerDetailsDao();
            Iterator var4 = CustomerDetailsDao.customers.iterator();

            while(var4.hasNext()) {
                CustomerDetails c1 = (CustomerDetails)var4.next();
                if (c1.getUserId() == LoginController.userId) {
                    this.customerName.setText(c1.getCustomerName());
                    this.customerMobileNum.setText(String.valueOf(c1.getCustomerMblNum()));
                    this.email.setText(c1.getEmail());
                }
            }

            new CarDAO();
            List<String> list = (List)CarDAO.carList.stream().map((d) -> {
                return d.getCarname();
            }).collect(Collectors.toList());
            ObservableList<String> items = this.listView.getItems();
            items.addAll(list);
            this.buyCar.setDisable(true);
        } catch (SQLException | ClassNotFoundException var5) {
            var5.printStackTrace();
        }
    }

}
