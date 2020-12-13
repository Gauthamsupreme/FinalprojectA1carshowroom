package com.carmgmt.Dao;

import com.carmgmt.model.CustomerDetails;
import com.carmgmt.model.EmployeeDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDetailsDao {
    //Declare DB objects
    DBConnect dbConnect = null;
    public static List<CustomerDetails> customers;

    // constructor
    public CustomerDetailsDao() throws ClassNotFoundException, SQLException {
        dbConnect = new DBConnect();
        PreparedStatement preparedStatement=null;

        try {
            preparedStatement = dbConnect.getConnection().prepareStatement("Select * from A1_customers");
        } catch (SQLException sqlException) {
            System.out.println("database while connecting...: " + sqlException);
            System.exit(-1);
        }

        customers = new ArrayList();
        CustomerDetails customerDetails = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            customerDetails = new CustomerDetails();
            customerDetails.setCustomerId(resultSet.getInt("customerid"));
            customerDetails.setCustomerName(resultSet.getString("customername"));
            customerDetails.setCustomerMblNum(resultSet.getInt("customerphone"));
            customerDetails.setEmail(resultSet.getString("customeraddress"));
            customerDetails.setUserId(resultSet.getInt("userid"));
            customers.add(customerDetails);
        }
    }

    public void addCustomers(CustomerDetails customerDetails) {
        PreparedStatement preparedStatement=null;
        String sql = "INSERT INTO A1_customers(customername, customerphone, customeraddress,userid) VALUES(?,?,?,?)";
        try {
            preparedStatement = dbConnect.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, customerDetails.getCustomerName());
            preparedStatement.setInt(2, customerDetails.getCustomerMblNum());
            preparedStatement.setString(3, customerDetails.getEmail());
            preparedStatement.setInt(4, customerDetails.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
