package com.example.jdbc.set3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// Delete rating record of a given movie name where the rating is below given threshold
public class Question10 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/rating";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);
        try {
            String movieName = scanner.nextLine();
            double value = scanner.nextInt();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM ratings WHERE movie_id = (SELECT id FROM movies WHERE movies.name = ?) AND ratings.rating < ?;");
            deleteStatement.setString(1, movieName);
            deleteStatement.setDouble(2, value);
            int effectedRows = deleteStatement.executeUpdate();
            if (effectedRows > 0) {
                connection.commit();
                System.out.println(effectedRows);
            } else throw new SQLException();
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
