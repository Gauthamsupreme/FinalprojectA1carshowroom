package com.carmgmt.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {

    static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";
    // Database credentials
    static final String USER = "db510", PASS = "510";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


}
