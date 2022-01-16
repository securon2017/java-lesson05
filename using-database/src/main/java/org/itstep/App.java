package org.itstep;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //demo();
        // Практическое задание:
        // Написать приложение, в котором возможно добавить нового
        // студента в группу 'ВПУ 911'
        // Для этого необходимо через Scanner ввести данные студента
        // После успешного добавления необходимо отобразить список
        // всех студентов группы 'ВПУ 911'
    }

    private static void demo() {
        // url (connection string)
        String url = "jdbc:mariadb://localhost:3306/db-univer";
        // credentials (login, password)
        String user = "root";
        String password = "";
        // Connection -> DriverManager
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Statement (PreparedStatement, CallableStatement)
            Statement stmt = conn.createStatement();
            // DML query (insert, update, delete) - executeUpdate(sql)
            // int count = stmt.executeUpdate("INSERT groups (name) values ('ВПД 911')");
            // System.out.println("count = " + count);
            // DQL query (select) - executeQuery(sql) -> ResultSet
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `groups`");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.printf("%3d | %-20s%n", id, name);
            }
            // close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
