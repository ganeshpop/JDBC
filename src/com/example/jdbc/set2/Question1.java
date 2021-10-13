package com.example.jdbc.set2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question1 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/catalog";
        final String userName = "ganesh";
        final String password = "password";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement createStatement = connection.prepareStatement("CREATE TABLE products (id BIGINT AUTO_INCREMENT, code VARCHAR(10), name VARCHAR(20), description VARCHAR(30), price DOUBLE, PRIMARY KEY (id)); ");
            createStatement.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
