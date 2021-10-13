package com.example.jdbc.set1;

import java.sql.*;
import java.util.Scanner;

// Update student name of a given student and display effected rows count
public class Question8 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String studentName = scanner.nextLine();
            String updatedStudentName = scanner.nextLine();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement updateStudentName = connection.prepareStatement("UPDATE assignments SET STUDENT_NAME = ? WHERE SERIAL_NO IN (SELECT SERIAL_NO WHERE STUDENT_NAME = ?);");
            updateStudentName.setString(1, updatedStudentName.toUpperCase());
            updateStudentName.setString(2, studentName);
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
