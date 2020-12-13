package com.carmgmt.Dao;


import com.carmgmt.model.TransactionDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailsDAO {
    private static PreparedStatement preparedStatement;
    static DBConnect dbConnect = null;
    public static List<TransactionDetails> transactions;

    public TransactionDetailsDAO() throws ClassNotFoundException, SQLException {
        dbConnect = new DBConnect();
        try {
            preparedStatement = dbConnect.getConnection().prepareStatement("Select * from A1_transactions");
        } catch (SQLException sqlException) {
            System.out.println("database error while connecting... " + sqlException);
            System.exit(-1);
        }

        transactions = new ArrayList();
        ResultSet rst = preparedStatement.executeQuery();

        while(rst.next()) {
            transactions.add(new TransactionDetails(rst.getInt("transactionid"), rst.getInt("customerid"), rst.getInt("carid")));
        }

    }

    public static int returnLatestTransaction() throws ClassNotFoundException {
        try {
            dbConnect = new DBConnect();
            preparedStatement = dbConnect.getConnection().prepareStatement("SELECT * FROM A1_transactions ORDER BY transactionid DESC LIMIT 1;");
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                return rst.getInt("transactionid");
            }
        } catch (SQLException sqlException) {
            System.out.println("database error while connecting: " + sqlException);
            System.exit(-1);
        }

        return 0;
    }

    public static void addTransaction(int customerID, int carID) throws ClassNotFoundException, SQLException {
        try {
            dbConnect = new DBConnect();
            String sql = "INSERT INTO A1_transactions(customerid, carid) VALUES(?,?)";
            preparedStatement = dbConnect.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, customerID);
            preparedStatement.setInt(2, carID);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("database error while connecting... " + sqlException);
            System.exit(-1);
        }

    }
}
