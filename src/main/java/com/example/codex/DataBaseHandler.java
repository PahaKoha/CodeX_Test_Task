package com.example.codex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:postgresql://"
                + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        if (dbConnection != null) {
            System.out.println("Connection was successfully!");
        }
        return dbConnection;
    }

    public void singUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_FIRST_NAME + "," + Const.USER_LAST_NAME +
                "," + Const.USER_USERNAME + "," + Const.USER_PASSWORD +
                "," + Const.USER_LOCATION + ")" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println(classNotFoundException);
        }

    }
}
