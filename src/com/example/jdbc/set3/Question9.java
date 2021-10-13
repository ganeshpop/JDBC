package com.example.jdbc.set3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// Delete movie and its ratings when given the name of the movie display number of ratings deleted
public class Question9 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/rating";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);
        try {
            String movieName = scanner.nextLine();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM ratings WHERE movie_id = (SELECT id FROM movies WHERE movies.name = ?);");
            deleteStatement.setString(1, movieName);
            int effectedRows = deleteStatement.executeUpdate();
            System.out.println(effectedRows);
            deleteStatement = connection.prepareStatement("DELETE FROM movies WHERE name = ?");
            deleteStatement.setString(1, movieName);
            effectedRows = deleteStatement.executeUpdate();
            if (effectedRows > 0) {
                connection.commit();
            } else throw new SQLException();
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
