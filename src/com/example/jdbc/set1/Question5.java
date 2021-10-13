package com.example.jdbc.set1;

import java.sql.*;
import java.util.Scanner;

// retrieve all submissions under a subject and category and order the result by student name and assignment category.
public class Question5 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String subject = scanner.nextLine();
            String assignmentCategory = scanner.nextLine() + "%";
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement getAssignments = connection.prepareStatement("SELECT * FROM assignments WHERE SUBJECT = ? AND ASSIGNMENT_CATEGORY LIKE ? ORDER BY STUDENT_NAME, ASSIGNMENT_CATEGORY;");
            getAssignments.setString(1, subject);
            getAssignments.setString(2, assignmentCategory);
            ResultSet resultSet = getAssignments.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                                  resultSet.getString("STUDENT_NAME") + " "
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
