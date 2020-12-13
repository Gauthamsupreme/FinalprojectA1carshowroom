package com.carmgmt.controller;

import com.carmgmt.Dao.CarDAO;
import com.carmgmt.model.Car;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CarController implements Initializable {
    @FXML
    TextField carName;
    @FXML
    ListView<String> carType;
    @FXML
    TextField carPrice;
    @FXML
    TextField chargingBattery;
    @FXML
    TextField fuelCapacity;
    @FXML
    Button addUpdateCar;
    @FXML
    Label messageLabel;

    @FXML
    private Button backBtn;

    private int carID;

    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = this.carType.getItems();
        items.add("Electric");
        items.add("Fuel");
        if (!EmployeeController.selectedCar.equals("")) {
            Iterator var5 = CarDAO.carList.iterator();

            while(var5.hasNext()) {
                Car car = (Car)var5.next();
                if (car.getCarname().equals(EmployeeController.selectedCar)) {
                    this.carID = car.getCarid();
                    this.carName.setText(car.getCarname());
                    this.carType.getSelectionModel().select(car.getCartype());
                    this.carPrice.setText(String.valueOf(car.getPrice()));
                    this.disableInvalidProperty();
                    this.chargingBattery.setText(car.getChargingbattery());
                    this.fuelCapacity.setText(car.getFillingtank());
                }
            }
        }

    }

    public void disableInvalidProperty() {
        if (((String)this.carType.getSelectionModel().getSelectedItem()).equals("Electric")) {
            this.fuelCapacity.setDisable(true);
            this.chargingBattery.setDisable(false);
        }

        if (((String)this.carType.getSelectionModel().getSelectedItem()).equals("Fuel")) {
            this.fuelCapacity.setDisable(false);
            this.chargingBattery.setDisable(true);
        }

    }

    public void updateCar() throws NumberFormatException, ClassNotFoundException, SQLException {
        if (EmployeeController.operationType.equals("add")) {
            CarDAO.addCar(this.carName.getText(), (String)this.carType.getSelectionModel().getSelectedItem(), Integer.parseInt(this.carPrice.getText()), this.chargingBattery.getText(), this.fuelCapacity.getText());
        } else {
            String chargingBatteryText = null;
            String fuelCapacityText = null;
            if (!this.chargingBattery.isDisable()) {
                chargingBatteryText = this.chargingBattery.getText();
            }

            if (!this.fuelCapacity.isDisable()) {
                fuelCapacityText = this.fuelCapacity.getText();
            }

            CarDAO.updateCar(this.carName.getText(), (String)this.carType.getSelectionModel().getSelectedItem(), Integer.parseInt(this.carPrice.getText()), chargingBatteryText, fuelCapacityText, this.carID);
        }

        this.messageLabel.setText("Add/Update car is successfull, Please refresh RefreshCarList for latest changes");
    }

    public void backBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
