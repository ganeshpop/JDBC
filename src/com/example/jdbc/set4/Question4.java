package com.example.jdbc.set4;

import java.sql.*;
import java.util.Scanner;

// Display all the department, project, performance of an employee and order by project name
public class Question4 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String employeeName = scanner.nextLine();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement getRecords = connection.prepareStatement("SELECT DEPARTMENT, PROJECT, PERFORMANCE FROM employees WHERE EMPLOYEE_NAME = ? ORDER BY PROJECT;");
            getRecords.setString(1, employeeName);
            ResultSet resultSet = getRecords.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("DEPARTMENT") + " "
                                + resultSet.getString("PROJECT") + " "
                                + resultSet.getInt("PERFORMANCE")
                );
            }
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
