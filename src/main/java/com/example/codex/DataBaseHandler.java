package com.example.codex;


import java.sql.*;

public class DataBaseHandler extends Configs {

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        if (dbConnection == null || dbConnection.isClosed()) {
            String connectionString = "jdbc:postgresql://"
                    + dbHost + ":" + dbPort + "/" + dbName;
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
            if (dbConnection != null) {
                System.out.println("Connection was successfully!");
            }
        }
        return dbConnection;
    }

    public void singUpUser(String firstName, String lastName, String userName, String password, String location) {
        String query = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_FIRST_NAME + "," + Const.USER_LAST_NAME +
                "," + Const.USER_USERNAME + "," + Const.USER_PASSWORD +
                "," + Const.USER_LOCATION + ")" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, location);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println(classNotFoundException);
        }

    }

    public void logInUser(User user, String userName, String password) {
        String query = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + " =? AND  " + Const.USER_PASSWORD + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getString(Const.USER_ID));
                user.setUserName(resultSet.getString(Const.USER_USERNAME));
                user.setPassword(resultSet.getString(Const.USER_PASSWORD));
                user.setFirstName(resultSet.getString(Const.USER_FIRST_NAME));
                user.setLastName(resultSet.getString(Const.USER_LAST_NAME));
                user.setLocation(resultSet.getString(Const.USER_LOCATION));
                System.out.println(user);git 
            } else {
                System.out.println("Login or password entered incorrectly");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
