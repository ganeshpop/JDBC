package com.example.jdbc.set1;

import java.sql.*;
import java.util.Scanner;

// Display all the assignment, subject, points submitted by a student and order by subject name and assignment category
public class Question4 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String studentName = scanner.nextLine();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement getAssignments = connection.prepareStatement("SELECT SUBJECT, ASSIGNMENT_CATEGORY, POINTS FROM assignments WHERE STUDENT_NAME = ? ORDER BY SUBJECT, ASSIGNMENT_CATEGORY;");
            getAssignments.setString(1, studentName);
            ResultSet resultSet = getAssignments.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("SUBJECT") + " "
                                + resultSet.getString("ASSIGNMENT_CATEGORY") + " "
                                + resultSet.getInt("POINTS")
                );
            }
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
