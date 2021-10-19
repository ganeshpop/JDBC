package com.example.jdbc.set4;

import java.sql.*;
import java.util.Scanner;

// Display top 3 employee name who has maximum performance in given project
public class Question6 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String project = scanner.nextLine();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement getAssignments = connection.prepareStatement("SELECT EMPLOYEE_NAME, PERFORMANCE FROM employees WHERE PROJECT = ? ORDER BY PERFORMANCE DESC LIMIT 3;");
            getAssignments.setString(1, project);
            ResultSet resultSet = getAssignments.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                                  resultSet.getString("EMPLOYEE_NAME") + " "
                                + resultSet.getInt("PERFORMANCE")
                );
            }
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
