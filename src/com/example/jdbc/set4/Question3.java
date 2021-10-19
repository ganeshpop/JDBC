package com.example.jdbc.set4;

import java.sql.*;

// Display all the distinct employees and order them by the name
public class Question3 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT EMPLOYEE_NAME FROM EMPLOYEES ORDER BY EMPLOYEE_NAME;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("EMPLOYEE_NAME"));
            }
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
