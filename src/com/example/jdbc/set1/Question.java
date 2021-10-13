package com.example.jdbc.set1;

import java.sql.*;

public class Question {
    public static void main(String[] args) {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String connectionString = "jdbc:mysql://localhost:3306/test";
        final String userName = "ganesh";
        final String password = "password";

        class Assignment {
            final int serialNo;
            final String studentName;
            final String subject;
            final String assignmentCategory;
            final int points;

            public Assignment(int serialNo, String studentName, String subject, String assignmentCategory, int points) {
                this.serialNo = serialNo;
                this.studentName = studentName;
                this.subject = subject;
                this.assignmentCategory = assignmentCategory;
                this.points = points;
            }
        }

        Assignment[] assignments =
                new Assignment[]{
                        new Assignment(1, "ANANTH", "ELECTRO_FIELDS", "TEST_1", 100),
                        new Assignment(2, "BHAGATH", "ELECTRO_FIELDS", "TEST_1", 78),
                        new Assignment(3, "CHAYA", "ELECTRO_FIELDS", "TEST_1", 68),
                        new Assignment(4, "ANANTH", "COMPUTING_TECHNIQUES", "TEST_2", 86),
                        new Assignment(5, "BHAGATH", "COMPUTING_TECHNIQUES", "TEST_2", 100),
                        new Assignment(6, "CHAYA", "COMPUTING_TECHNIQUES", "TEST_2", 20)
                };
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            connection.setAutoCommit(false);
            PreparedStatement createStatement = connection.prepareStatement("CREATE TABLE ASSIGNMENTS (SERIAL_NO INT PRIMARY KEY , STUDENT_NAME VARCHAR(20), SUBJECT VARCHAR(20), ASSIGNMENT_CATEGORY VARCHAR(20), POINTS INT);");
            createStatement.execute();
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ASSIGNMENTS(SERIAL_NO, STUDENT_NAME, SUBJECT, ASSIGNMENT_CATEGORY, POINTS) VALUES (?, ?, ?, ?, ?);");
            for (Assignment assignment : assignments) {
                insertStatement.setInt(1, assignment.serialNo);
                insertStatement.setString(2, assignment.studentName);
                insertStatement.setString(3, assignment.subject);
                insertStatement.setString(4, assignment.assignmentCategory);
                insertStatement.setInt(5, assignment.points);
                if (insertStatement.executeUpdate() < 1) throw new SQLException();
                connection.commit();
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}
