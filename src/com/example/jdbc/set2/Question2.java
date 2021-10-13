package com.example.jdbc.set2;

import java.sql.*;
import java.util.Scanner;

public class Question2 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/catalog";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);


        try {
            int numberOfProducts = scanner.nextInt();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO PRODUCTS(code, name, description, price) VALUES (?, ?, ?, ?);");
            for (int i = 0; i < numberOfProducts; i++ ) {
                scanner.nextLine(); // For consuming newline character
                insertStatement.setString(1, scanner.nextLine());
                insertStatement.setString(2, scanner.nextLine());
                insertStatement.setString(3, scanner.nextLine());
                insertStatement.setDouble(4, scanner.nextDouble());
                if (insertStatement.executeUpdate() < 1) throw new SQLException();
                connection.commit();
            }
            PreparedStatement getCount = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM products;");
            ResultSet resultSet = getCount.executeQuery();
            if(resultSet.next()) System.out.println(resultSet.getInt("COUNT"));
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
