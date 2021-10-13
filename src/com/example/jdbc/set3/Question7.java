package com.example.jdbc.set3;

import java.sql.*;
import java.util.Scanner;

// Update movie's rating when movie name, user-name and new rating is given and print the updated record
public class Question7 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/rating";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            String movie = scanner.nextLine();
            String user = scanner.nextLine();
            double rating = scanner.nextDouble();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE ratings SET  rating = ? WHERE name = ? AND movie_id = (SELECT id FROM movies WHERE name = ?);");
            updateStatement.setDouble(1, rating);
            updateStatement.setString(2, user);
            updateStatement.setString(3, movie);
            int effectedRows = updateStatement.executeUpdate();
            if (effectedRows > 0) {
                connection.commit();
                PreparedStatement getStatement = connection.prepareStatement("SELECT id, r.name as user, m.name as movie, r.rating FROM ratings r, (SELECT id as mid, name FROM movies WHERE name = ?) m WHERE r.name = ? AND r.movie_id = mid;");
                getStatement.setString(1, movie);
                getStatement.setString(2, user);
                ResultSet resultSet = getStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println(
                            resultSet.getString("id") + ", "
                                    + resultSet.getString("movie") + ", "
                                    + resultSet.getString("user") + ", "
                                    + resultSet.getDouble("rating")
                    );
                }
                connection.close();
            } else throw new SQLException();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
