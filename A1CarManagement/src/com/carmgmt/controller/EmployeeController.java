package com.carmgmt.controller;

import com.carmgmt.Dao.CarDAO;
import com.carmgmt.Dao.EmployeeDetailsDao;
import com.carmgmt.Main;
import com.carmgmt.model.Car;
import com.carmgmt.model.EmployeeDetails;
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

public class EmployeeController implements Initializable {

    @FXML
    private TextField employeeName;

    @FXML
    private TextField employeeMblNum;

    @FXML
    private TextField email;

    @FXML
    public ListView listView;

    @FXML
    public Button updateCar;

    @FXML
    public Button deleteCar;

    /*@FXML
    private Button refresh;*/


    @FXML
    private List<String> list;

    public static String operationType;

    public static String selectedCar;


    public void addCar(ActionEvent actionEvent) throws IOException {
        selectedCar = "";
        operationType="add";
        Parent root = (Parent) FXMLLoader.load(Main.class.getResource("/com/carmgmt/view/car.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 600.0D, 500.0D);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void deleteCar() throws IOException, ClassNotFoundException, SQLException {
        String selectedCar = ((String)this.listView.getSelectionModel().getSelectedItem()).toString();
        String operationType = "delete";
        CarDAO.deleteCar(selectedCar);
        this.listView.getItems().remove(selectedCar);
    }

    public void refreshCarDB() throws ClassNotFoundException, SQLException {
        new CarDAO();
        this.listView.getItems().removeAll(this.list);
        this.list = (List)CarDAO.carList.stream().map((d) -> {
            return d.getCarname();
        }).collect(Collectors.toList());
        this.listView.getItems().addAll(this.list);

    }

    public void updateDeleteCar() throws IOException, ClassNotFoundException, SQLException {
        selectedCar = ((String)this.listView.getSelectionModel().getSelectedItem()).toString();
        operationType = "update";
        Parent root = (Parent)FXMLLoader.load(Main.class.getResource("/com/carmgmt/view/AddUpdateCar.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 700.0D, 600.0D);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void initialize(URL location, ResourceBundle resources) {
        try {
            new EmployeeDetailsDao();
            List<EmployeeDetails> employeeDetailsList = EmployeeDetailsDao.employeeDetailsList;
            for (EmployeeDetails employeeDetails : employeeDetailsList ){
                if (employeeDetails.getUserId() == LoginController.userId) {
                    this.employeeName.setText(employeeDetails.getEmployeeName());
                    this.employeeMblNum.setText(String.valueOf(employeeDetails.getEmployeeMblNum()));
                    this.email.setText(employeeDetails.getEmail());
                }
            }
            new CarDAO();
            List<Car> carsList = CarDAO.carList;
            this.list = carsList.stream().map(Car::getCarname).collect(Collectors.toList());
            ObservableList<String> items = this.listView.getItems();
            items.addAll(this.list);
            this.updateCar.setDisable(true);
            this.deleteCar.setDisable(true);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    public void enableButtons(MouseEvent mouseEvent) {
        if (((String)this.listView.getSelectionModel().getSelectedItem()).isEmpty()) {
            this.updateCar.setDisable(true);
            this.deleteCar.setDisable(true);
        } else {
            this.updateCar.setDisable(false);
            this.deleteCar.setDisable(false);
        }
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/carmgmt/view/login.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("A1 CAR MANAGEMENT");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
