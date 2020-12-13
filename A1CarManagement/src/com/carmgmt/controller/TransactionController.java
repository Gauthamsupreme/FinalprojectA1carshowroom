package com.carmgmt.controller;

import com.carmgmt.Dao.CarDAO;
import com.carmgmt.Dao.CustomerDetailsDao;
import com.carmgmt.Dao.TransactionDetailsDAO;
import com.carmgmt.model.Car;
import com.carmgmt.model.CustomerDetails;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    @FXML
    TextField carName;
    @FXML
    TextField carType;
    @FXML
    TextField carPrice;
    @FXML
    TextField chargingBattery;
    @FXML
    TextField fuelCapacity;
    @FXML
    Label chargingBatterLabel;
    @FXML
    Label fuelCapacityLabel;
    @FXML
    Label messageLabel;
    private int userID;
    private int carID;
    @FXML
    Button buyCar;

    public TransactionController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        if (!CustomerController.selectedCar.equals("")) {
            Iterator var4 = CarDAO.carList.iterator();

            while(var4.hasNext()) {
                Car c = (Car)var4.next();
                if (c.getCarname().equals(CustomerController.selectedCar)) {
                    this.carID = c.getCarid();
                    this.carName.setText(c.getCarname());
                    this.carType.setText(c.getCartype());
                    this.carPrice.setText(String.valueOf(c.getPrice()));
                    if (c.getChargingbattery() != null) {
                        this.chargingBattery.setText(c.getChargingbattery());
                        this.fuelCapacity.setVisible(false);
                        this.fuelCapacityLabel.setVisible(false);
                    }

                    if (c.getFillingtank() != null) {
                        this.fuelCapacity.setText(c.getFillingtank());
                        this.chargingBattery.setVisible(false);
                        this.chargingBatterLabel.setVisible(false);
                    }
                }
            }
        }

    }

    public void purchaseCar() throws ClassNotFoundException, SQLException {
        this.userID = LoginController.userId;
        Iterator var2 = CustomerDetailsDao.customers.iterator();

        while(var2.hasNext()) {
            CustomerDetails c = (CustomerDetails) var2.next();
            if (c.getUserId() == this.userID) {
                TransactionDetailsDAO.addTransaction(c.getCustomerId(), this.carID);
                break;
            }
        }
        this.messageLabel.setText("Thank you for purchasing in  A1-CAR-MANAGEMENT your transaction id : " + TransactionDetailsDAO.returnLatestTransaction() + " for your reference");
    }
}
