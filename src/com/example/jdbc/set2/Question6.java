package com.example.jdbc.set2;

import java.sql.*;

// Display products with the highest price from each variant for all product types
public class Question6 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/catalog";
        final String userName = "ganesh";
        final String password = "password";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id , a.name, code, description, price FROM products a, (SELECT name, max(price) AS maxprice FROM products GROUP BY  name) b WHERE a.name = b.name AND price = maxprice;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
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
