package com.example.jdbc.set3;

import java.sql.*;
import java.util.Scanner;

// Display the review's of all the available movies which have a rating above given threshold
public class Question5 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/rating";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            double value = scanner.nextDouble();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT r.name AS user, m.name AS movie, r.rating  FROM ratings r, movies m WHERE r.rating > ? AND r.movie_id = m.id ORDER BY r.name, m.name;");
            preparedStatement.setDouble(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("user") + ", "
                                + resultSet.getString("movie") + ", "
                                + resultSet.getString("rating")
                );
            }
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
