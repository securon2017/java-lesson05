package org.itstep;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        practik();
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
            int count = stmt.executeUpdate("INSERT groups (name) values ('ВПД 911')");
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

    private static void practik(){
        System.out.println("Enter Username");
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();

        System.out.println("Enter Age");
        int age = scan.nextInt();

        System.out.println("Enter email");
        String email = scan.next();

        String url = "jdbc:mariadb://localhost:3306/db-univer";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String query = "INSERT users (username, age, email) values (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,userName);
            stmt.setInt(2,age);
            stmt.setString(3,email);
            stmt.executeUpdate();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `users`");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");
                int agePrint = resultSet.getInt("age");
                String emailPrint = resultSet.getString("email");
                System.out.printf("%3d | %-20s | %3d | %20s%n", id, name,agePrint,emailPrint);
            }
            // close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
