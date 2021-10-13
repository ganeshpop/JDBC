package com.example.jdbc.set2;

import java.sql.*;
import java.util.Scanner;

// Display the code, name, description, price of all the available variants of a given product code
public class Question5 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/catalog";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String productCode = scanner.nextLine();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE name = (SELECT name FROM products WHERE code = ?) ORDER BY name, code;");
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("code") + ", "
                                + resultSet.getString("name") + ", "
                                + resultSet.getString("description") + ", "
                                + resultSet.getDouble("price")
                );
            }
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
