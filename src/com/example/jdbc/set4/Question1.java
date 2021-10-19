package com.example.jdbc.set4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question1 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement createStatement = connection.prepareStatement("CREATE TABLE EMPLOYEES( EMPLOYEE_ID  INT PRIMARY KEY, EMPLOYEE_NAME VARCHAR(20), DEPARTMENT VARCHAR(20), PROJECT VARCHAR(20), PERFORMANCE INT); ");
            createStatement.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
