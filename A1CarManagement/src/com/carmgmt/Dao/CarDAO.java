package com.carmgmt.Dao;


import com.carmgmt.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO{

    //Declare DB objects
    static DBConnect dbConnect = null;
    public static  List<Car> carList=null;

    public CarDAO() throws SQLException {
        PreparedStatement preparedStatement = null;
        dbConnect = new DBConnect();
        carList= new ArrayList<>();
        Car car;
        try {
            preparedStatement = dbConnect.getConnection().prepareStatement("Select * from A1_CARS");
        } catch (SQLException sqlException) {
            System.out.println("database error while connecting" + sqlException);
            System.exit(-1);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            car = new Car();
            car.setCarid(resultSet.getInt("carid"));
            System.out.println(resultSet.getString("carname"));
            car.setCarname(resultSet.getString("carname"));
            car.setCartype(resultSet.getString("cartype"));
            car.setPrice(resultSet.getInt("price"));
            car.setChargingbattery(resultSet.getString("chargingbattery"));
            car.setFillingtank(resultSet.getString("fillingtank"));
            carList.add(car);
        }
    }

    public static void addCar(String carName, String carType, int carPrice, String chargingBattery, String fuelTank) throws ClassNotFoundException, SQLException {
        try {
            dbConnect = new DBConnect();
            PreparedStatement preparedStatement=null;
            String insertTableSQL = "INSERT INTO A1_CARS(CARNAME, CARTYPE, PRICE,CHARGINGBATTERY,FILLINGTANK) VALUES(?,?,?,?,?)";
            preparedStatement = dbConnect.getConnection().prepareStatement(insertTableSQL);
            preparedStatement.setString(1, carName);
            preparedStatement.setString(2, carType);
            preparedStatement.setInt(3, carPrice);
            preparedStatement.setString(4, chargingBattery);
            preparedStatement.setString(5, fuelTank);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("database error while connecting ..." + sqlException);
            System.exit(-1);
        }
    }

    public static void updateCar(String carName, String carType, int carPrice, String chargingBattery, String fuelTank, int carid) throws ClassNotFoundException, SQLException {
        try {
            PreparedStatement preparedStatement=null;
            String updateTableSQL = "UPDATE A1_CARS SET CARNAME = ?,CARTYPE = ?,PRICE = ?,CHARGINGBATTERY = ?,FILLINGTANK = ? WHERE CARID = ?";
            preparedStatement = dbConnect.getConnection().prepareStatement(updateTableSQL);
            preparedStatement.setString(1, carName);
            preparedStatement.setString(2, carType);
            preparedStatement.setInt(3, carPrice);
            preparedStatement.setString(4, chargingBattery);
            preparedStatement.setString(5, fuelTank);
            preparedStatement.setInt(6, carid);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("Database error while connecting: " + sqlException);
            System.exit(-1);
        }
    }

    public static void deleteCar(String carName) throws ClassNotFoundException, SQLException {
        try {
            PreparedStatement preparedStatement=null;
            String deleteTableSQL = "DELETE FROM A1_CARS WHERE CARNAME = ?";
            preparedStatement = dbConnect.getConnection().prepareStatement(deleteTableSQL);
            preparedStatement.setString(1, carName);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("database error while connecting: " + sqlException);
            System.exit(-1);
        }

    }
}
