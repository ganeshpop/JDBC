package com.example.jdbc.set4;

import java.sql.*;
import java.util.Scanner;

// Update performance when department and project given and print the record
public class Question7 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String employeeName = scanner.nextLine();
            String department = scanner.nextLine();
            String project = scanner.nextLine();
            int updatedPerformance = scanner.nextInt();

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement updateAssignment = connection.prepareStatement("UPDATE employees SET  PERFORMANCE = ? WHERE EMPLOYEE_NAME = ? AND DEPARTMENT = ? AND PROJECT = ?;");
            updateAssignment.setInt(1, updatedPerformance);
            updateAssignment.setString(2, employeeName);
            updateAssignment.setString(3, department);
            updateAssignment.setString(4, project);
            int effectedRows = updateAssignment.executeUpdate();
            if (effectedRows > 0) {
                connection.commit();
                PreparedStatement getAssignment = connection.prepareStatement("SELECT EMPLOYEE_NAME, DEPARTMENT, PROJECT, PERFORMANCE FROM employees WHERE EMPLOYEE_NAME = ? AND DEPARTMENT = ? AND PROJECT = ?;");
                getAssignment.setString(1, employeeName);
                getAssignment.setString(2, department);
                getAssignment.setString(3, project);
                ResultSet resultSet = getAssignment.executeQuery();
                if (resultSet.next()) {
                    System.out.println(
                            resultSet.getString("EMPLOYEE_NAME") + " "
                                    + resultSet.getString("DEPARTMENT") + " "
                                    + resultSet.getString("PROJECT") + " "
                                    + resultSet.getInt("PERFORMANCE")
                    );
                }
                connection.close();
            } else throw new SQLException();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
