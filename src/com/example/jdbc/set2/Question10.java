package com.example.jdbc.set2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// Delete product record with a given name where the price is below given threshold
public class Question10 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/catalog";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String productName = scanner.nextLine();
            double value = scanner.nextDouble();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM products WHERE name = ? AND price < ?;");
            deleteStatement.setString(1, productName);
            deleteStatement.setDouble(2, value);
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
