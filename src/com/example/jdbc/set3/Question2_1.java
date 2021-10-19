package com.example.jdbc.set3;

import java.sql.*;
import java.util.Scanner;

public class Question2_1 {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/rating";
        final String userName = "ganesh";
        final String password = "password";
        Scanner scanner = new Scanner(System.in);

        try {
            int numberOfItems = scanner.nextInt();
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ratings(name, movie_id, rating) VALUES ( ?, ?, ?);");
            for (int i = 0; i < numberOfItems; i++) {
                scanner.nextLine(); // For consuming newline character
                insertStatement.setString(1, scanner.nextLine());
                insertStatement.setInt(2, scanner.nextInt());
                insertStatement.setDouble(3, scanner.nextDouble());
                if (insertStatement.executeUpdate() < 1) throw new SQLException();
                connection.commit();
            }
            PreparedStatement getCount = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM ratings;");
            ResultSet resultSet = getCount.executeQuery();
            if (resultSet.next()) System.out.println(resultSet.getInt("COUNT"));
            connection.close();
        } catch (NumberFormatException | SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
