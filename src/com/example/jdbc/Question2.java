package com.example.jdbc;

import java.sql.*;
import java.util.Scanner;

public class Question2 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);


        try {
            int numberOfAssignments = scanner.nextInt();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ASSIGNMENTS(SERIAL_NO, STUDENT_NAME, SUBJECT, ASSIGNMENT_CATEGORY, POINTS) VALUES (?, ?, ?, ?, ?);");
            for (int i = 0; i < numberOfAssignments; i++ ) {
                insertStatement.setInt(1, scanner.nextInt());
                scanner.nextLine(); // For consuming newline character
                insertStatement.setString(2, scanner.nextLine());
                insertStatement.setString(3, scanner.nextLine());
                insertStatement.setString(4, scanner.nextLine());
                insertStatement.setInt(5, scanner.nextInt());
                if (insertStatement.executeUpdate() < 1) throw new SQLException();
                connection.commit();
            }
            PreparedStatement getCount = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM test.assignments;");
            ResultSet resultSet = getCount.executeQuery();
            if(resultSet.next()) System.out.println(resultSet.getInt("COUNT"));
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
