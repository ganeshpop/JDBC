package com.example.jdbc.set1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// Delete all Submissions of given student and category
public class Question10 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);
        try {
            String studentName = scanner.nextLine();
            String subject = scanner.nextLine();
            String assignmentCategory = scanner.nextLine() + "%"; // % is for Regular Expression Validation
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM assignments WHERE STUDENT_NAME = ? AND SUBJECT = ? AND ASSIGNMENT_CATEGORY LIKE ?;");
            deleteStatement.setString(1, studentName);
            deleteStatement.setString(2, subject);
            deleteStatement.setString(3, assignmentCategory);
            int effectedRows = deleteStatement.executeUpdate();
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
