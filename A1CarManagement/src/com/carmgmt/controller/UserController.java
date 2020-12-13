package com.carmgmt.controller;

import com.carmgmt.Dao.CustomerDetailsDao;
import com.carmgmt.Dao.DBConnect;
import com.carmgmt.Dao.EmployeeDetailsDao;
import com.carmgmt.Dao.UserDetailsDao;
import com.carmgmt.model.CustomerDetails;
import com.carmgmt.model.EmployeeDetails;
import com.carmgmt.model.UserDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private TextField mobileNumber;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton employeeRadioBtn;

    @FXML
    private RadioButton customerRadioBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label response;


    public void addUser() throws SQLException, ClassNotFoundException {

        UserDetails userDetails = new UserDetails();
        UserDetailsDao userDetailsDao = new UserDetailsDao();
        EmployeeDetailsDao employeeDetailsDao = new EmployeeDetailsDao();
        CustomerDetailsDao customerDetailsDao = new CustomerDetailsDao();
        Base64.Encoder encoder = Base64.getEncoder();

        if (employeeRadioBtn.isSelected()){
            setUserInfo(userDetails, encoder);
            userDetails.setRole("1");
            userDetailsDao.addUserInfo(userDetails);
            int userId = getUserId();
            EmployeeDetails employeeDetails= new EmployeeDetails();
            employeeDetails.setEmployeeName(userDetails.getUserName());
            employeeDetails.setEmail(email.getText());
            employeeDetails.setEmployeeMblNum(Integer.parseInt(mobileNumber.getText()));
            employeeDetails.setUserId(userId);
            employeeDetailsDao.addEmployee(employeeDetails);
            response.setText("Admin "+employeeDetails.getEmployeeName()+" added Successfully");
        }
        if (customerRadioBtn.isSelected()){
            setUserInfo(userDetails, encoder);
            userDetails.setRole("2");
            userDetailsDao.addUserInfo(userDetails);
            int userId = getUserId();
            CustomerDetails customerDetails= new CustomerDetails();
            customerDetails.setCustomerName(userDetails.getUserName());
            customerDetails.setEmail(email.getText());
            customerDetails.setCustomerMblNum(Integer.parseInt(mobileNumber.getText()));
            customerDetails.setUserId(userId);
            customerDetailsDao.addCustomers(customerDetails);
            response.setText("Customer "+customerDetails.getCustomerName()+" added Successfully");
        }

    }

    private void setUserInfo(UserDetails userDetails, Base64.Encoder encoder) {
        userDetails.setUserName(this.userName.getText());
        String password = this.password.getText();
        userDetails.setPassword(encoder.encodeToString(password.getBytes()));
    }


    public void checkRadioBtn(){
        if (this.customerRadioBtn.isSelected()) {
            this.employeeRadioBtn.isDisable();
        }
        if (this.employeeRadioBtn.isSelected()) {
            this.customerRadioBtn.isDisable();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static int getUserId() throws ClassNotFoundException {
        try {
            DBConnect dbConnect = new DBConnect();
            PreparedStatement preparedStatement=null;
            preparedStatement = dbConnect.getConnection().prepareStatement("SELECT * FROM A1_users ORDER BY userid DESC LIMIT 1;");
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                return rst.getInt("userid");
            }
        } catch (SQLException var1) {
            System.out.println("Error creating connection to database: " + var1);
            System.exit(-1);
        }

        return 0;
    }

    public void backBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
