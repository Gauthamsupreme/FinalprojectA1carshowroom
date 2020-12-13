package com.carmgmt.Dao;

import com.carmgmt.model.UserDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserDetailsDao {
    //Declare DB objects
    DBConnect dbConnect = null;
    Statement statement = null;
    PreparedStatement preparedStatement=null;

    // constructor
    public UserDetailsDao() {
        //create db object instance
        dbConnect = new DBConnect();
    }

    public List<UserDetails> userValidation(String userName,String password) throws SQLException {
        List<UserDetails> userDetailsList = new ArrayList<>();
        UserDetails userDetails=new UserDetails();
        Base64.Decoder decoder = Base64.getDecoder();
        try {
          this.preparedStatement = dbConnect.getConnection().prepareStatement("Select * from A1_users where USERNAME=?");
          preparedStatement.setString(1,userName);
        } catch (SQLException sqlException) {
            System.out.println("database error"+sqlException);
            System.exit(-1);
        }
        ResultSet resultSet = this.preparedStatement.executeQuery();
        while(resultSet.next()) {
            userDetails.setUserId(resultSet.getInt("userid"));
            userDetails.setUserName(resultSet.getString("username"));
            String pass= resultSet.getString("password");
            System.out.println("pass>>>>>>>>>>"+pass);
            String decoding = new String(decoder.decode(pass));
            userDetails.setPassword(decoding);
            System.out.println("userDetails>> password"+userDetails.getPassword());
            userDetails.setRole(resultSet.getString("role"));
            userDetailsList.add(userDetails);
        }
        return userDetailsList;
    }

    public void addUserInfo(UserDetails userDetails){
        try {
            PreparedStatement preparedStatement = null;
            String sql = "INSERT INTO A1_USERS(USERNAME,PASSWORD,ROLE) VALUES(?,?,?)";
            preparedStatement = dbConnect.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, userDetails.getUserName());
            preparedStatement.setString(2, userDetails.getPassword());
            preparedStatement.setString(3, userDetails.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("database error while creating new user " + sqlException);
            System.exit(-1);
        }

    }



}
