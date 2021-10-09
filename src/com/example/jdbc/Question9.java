package com.example.jdbc;

import java.sql.*;
import java.util.Scanner;

// Delete student submissions where points are below given subject and threshold and print effected rows
public class Question9 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);
        try {
            String subject = scanner.nextLine();
            String assignmentCategory = scanner.nextLine() + "%"; //% is for Regular Expression
            int points = scanner.nextInt();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM assignments WHERE SUBJECT = ? AND ASSIGNMENT_CATEGORY LIKE ? AND POINTS < ?;");
            deleteStatement.setString(1, subject);
            deleteStatement.setString(2, assignmentCategory);
            deleteStatement.setInt(3, points);
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
