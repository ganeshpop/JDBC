package com.example.jdbc.set2;

import java.sql.*;
import java.util.Scanner;

// Update product's price when product code and new price is given and print the updated record
public class Question7 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/catalog";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String productCode = scanner.nextLine();
            double updatedPrice = scanner.nextDouble();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE products SET  price = ? WHERE code = ?;");
            updateStatement.setDouble(1, updatedPrice);
            updateStatement.setString(2, productCode);
            int effectedRows = updateStatement.executeUpdate();
            if (effectedRows > 0) {
                connection.commit();
                PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM products WHERE code = ?");
                getStatement.setString(1, productCode);
                ResultSet resultSet = getStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println(
                            resultSet.getString("code") + ", "
                                    + resultSet.getString("name") + ", "
                                    + resultSet.getString("description") + ", "
                                    + resultSet.getDouble("price")
                    );
                }
                connection.close();
            } else throw new SQLException();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
