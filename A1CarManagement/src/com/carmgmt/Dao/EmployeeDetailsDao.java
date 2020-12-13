package com.carmgmt.Dao;

import com.carmgmt.model.EmployeeDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailsDao {
    //Declare DB objects
    DBConnect dbConnect = null;
    public static List<EmployeeDetails> employeeDetailsList;

    // constructor
    public EmployeeDetailsDao() throws SQLException {
        //create db object instance
        dbConnect = new DBConnect();
        PreparedStatement preparedStatement = null;
        EmployeeDetails employeeDetails = new EmployeeDetails();
        try {
            preparedStatement = dbConnect.getConnection().prepareStatement("Select * from A1_employees");
        } catch (SQLException sqlException) {
            System.out.println("database error while connecting ....: " + sqlException);
            System.exit(-1);
        }
        employeeDetailsList = new ArrayList();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            employeeDetails.setEmployeeId(resultSet.getInt("empid"));
            employeeDetails.setEmployeeName(resultSet.getString("empname"));
            employeeDetails.setEmployeeMblNum(resultSet.getInt("empphone"));
            employeeDetails.setEmail(resultSet.getString("empaddress"));
            employeeDetails.setUserId(resultSet.getInt("userid"));
            employeeDetailsList.add(employeeDetails);
        }
    }

    public void addEmployee(EmployeeDetails employeeDetails) {
        PreparedStatement preparedStatement=null;
        String sql = "INSERT INTO A1_employees(empname, empphone, empaddress,userid) VALUES(?,?,?,?)";
        try {
            preparedStatement = dbConnect.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, employeeDetails.getEmployeeName());
            preparedStatement.setInt(2, employeeDetails.getEmployeeMblNum());
            preparedStatement.setString(3, employeeDetails.getEmail());
            preparedStatement.setInt(4, employeeDetails.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
