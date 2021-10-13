package com.example.jdbc.set3;

import java.sql.*;

// Display each movie with the highest rating given
public class Question6 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/rating";
        final String userName = "ganesh";
        final String password = "password";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, r.name AS user, m.name AS movie, r.rating FROM movies m, (SELECT name, movie_id , max(rating) as rating FROM ratings GROUP BY  movie_id ORDER BY  movie_id) r WHERE m.id = r.movie_id ORDER BY m.name;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(
                        resultSet.getString("id") + ", "
                                + resultSet.getString("movie") + ", "
                                + resultSet.getString("user") + ", "
                                + resultSet.getDouble("rating")
                );
            }
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
