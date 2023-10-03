package com.example.codex;


import java.sql.*;
import java.util.Map;

public class DataBaseHandler {
    private static final String JDBC_URL_PREFIX = "jdbc:postgresql://";
    private String dbHost;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private Connection dbConnection;

    public DataBaseHandler(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) {
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        if (dbConnection == null || dbConnection.isClosed()) {
            String connectionString = JDBC_URL_PREFIX + dbHost + ":" + dbPort + "/" + dbName;
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        }
        return dbConnection;
    }

    public void closeDbConnection() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            // Handle the exception or log it using a logger
        }
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
        } finally {
            closeDbConnection();
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
                System.out.println(user);
            } else {
                System.out.println("Login or password entered incorrectly");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDbConnection();
        }

    }

    public void createNewNote(String userId, String noteName, String note) {
        String query = "INSERT INTO " + ConstNotes.NOTES_TABLE + "(" +
                ConstNotes.USER_ID + "," + ConstNotes.NOTE_NAME +
                "," + ConstNotes.NOTE + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, noteName);
            preparedStatement.setString(3, note);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDbConnection();
        }
    }

    void getAllNotes(Map<String, String> noteNames) {
        String query = "SELECT * FROM " + ConstNotes.NOTES_TABLE + " WHERE " + ConstNotes.USER_ID + " =?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, CurrentUser.getCurrentUser().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                noteNames.put(resultSet.getString(ConstNotes.NOTE_NAME), resultSet.getString(ConstNotes.NOTE));
            }
            System.out.println(noteNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDbConnection();
        }
    }
}
