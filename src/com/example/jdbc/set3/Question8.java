package com.example.jdbc.set3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// Update username of user and display effected rows count
public class Question8 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/rating";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String oldUserName = scanner.nextLine();
            String updateduserName = scanner.nextLine();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE ratings SET name = ? WHERE id IN (SELECT id WHERE name = ?);");
            updateStatement.setString(1, updateduserName);
            updateStatement.setString(2, oldUserName);
            int effectedRows = updateStatement.executeUpdate();
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
