package com.example.jdbc;

import java.sql.*;
import java.util.Scanner;

// Update student's marks when subject and category given and print the record
public class Question7 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String studentName = scanner.nextLine();
            String subject = scanner.nextLine();
            String assignmentCategory = scanner.nextLine();
            int updatedPoints = scanner.nextInt();

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement updateAssignment = connection.prepareStatement("UPDATE assignments SET  POINTS = ? WHERE STUDENT_NAME = ? AND SUBJECT = ? AND ASSIGNMENT_CATEGORY = ?;");
            updateAssignment.setInt(1, updatedPoints);
            updateAssignment.setString(2, studentName);
            updateAssignment.setString(3, subject);
            updateAssignment.setString(4, assignmentCategory);
            int effectedRows = updateAssignment.executeUpdate();
            if (effectedRows > 0) {
                connection.commit();
                PreparedStatement getAssignment = connection.prepareStatement("SELECT * FROM assignments WHERE STUDENT_NAME = ? AND SUBJECT = ? AND ASSIGNMENT_CATEGORY = ?;");
                getAssignment.setString(1, studentName);
                getAssignment.setString(2, subject);
                getAssignment.setString(3, assignmentCategory);
                ResultSet resultSet = getAssignment.executeQuery();
                if (resultSet.next()) {
                    System.out.println(
                            resultSet.getString("STUDENT_NAME") + " "
                                    + resultSet.getString("SUBJECT") + " "
                                    + resultSet.getString("ASSIGNMENT_CATEGORY") + " "
                                    + resultSet.getInt("POINTS")
                    );

                }
                connection.close();
            } else throw new SQLException();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
