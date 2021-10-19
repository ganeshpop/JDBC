package com.example.jdbc.set4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// Update name of a given employee and display effected rows count
public class Question8 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String employeeName = scanner.nextLine();
            String updatedEmployeeName = scanner.nextLine();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement updateStudentName = connection.prepareStatement("UPDATE employees SET EMPLOYEE_NAME = ? WHERE EMPLOYEE_ID IN (SELECT EMPLOYEE_ID WHERE EMPLOYEE_NAME = ?);");
            updateStudentName.setString(1, updatedEmployeeName.toUpperCase());
            updateStudentName.setString(2, employeeName);
            int effectedRows = updateStudentName.executeUpdate();
            if (effectedRows > 0) {
                connection.commit();
                System.out.println(effectedRows);
            } else throw new SQLException();
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
