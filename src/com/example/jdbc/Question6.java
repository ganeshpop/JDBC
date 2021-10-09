package com.example.jdbc;

import java.sql.*;
import java.util.Scanner;

// Display top 3 students  name who has maximum marks in given subject and category
public class Question6 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String subject = scanner.nextLine();
            String assignmentCategory = scanner.nextLine();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement getAssignments = connection.prepareStatement("SELECT STUDENT_NAME, POINTS FROM assignments WHERE SUBJECT = ? AND ASSIGNMENT_CATEGORY = ? ORDER BY POINTS DESC LIMIT 3;");
            getAssignments.setString(1, subject);
            getAssignments.setString(2, assignmentCategory);
            ResultSet resultSet = getAssignments.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                                  resultSet.getString("STUDENT_NAME") + " "
                                + resultSet.getInt("POINTS")
                );
            }
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
