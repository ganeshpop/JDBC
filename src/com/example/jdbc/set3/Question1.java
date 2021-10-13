package com.example.jdbc.set3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question1 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/rating";
        final String userName = "ganesh";
        final String password = "password";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement createStatement = connection.prepareStatement("CREATE TABLE movies(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(25), release_year INT);");
            PreparedStatement createStatement1 = connection.prepareStatement("CREATE TABLE ratings(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(25), movie_id INT, rating DOUBLE);");
            createStatement.execute();
            createStatement1.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
