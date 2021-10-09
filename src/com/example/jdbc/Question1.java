package com.example.jdbc;

import java.sql.*;

public class Question1 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement createStatement = connection.prepareStatement("CREATE TABLE ASSIGNMENTS (SERIAL_NO INT PRIMARY KEY , STUDENT_NAME VARCHAR(20), SUBJECT VARCHAR(20), ASSIGNMENT_CATEGORY VARCHAR(20), POINTS INT);");
            createStatement.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
