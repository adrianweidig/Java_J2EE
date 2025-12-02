package weidig.adrian.java_j2ee.util;

import weidig.adrian.java_j2ee.Aufgabe4.model.Book;
import weidig.adrian.java_j2ee.Aufgabe4.model.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConsoleTest {

    public static void main(String[] args) {
        System.out.println("Starte DB-Tests ...");

        try (Connection conn = DatabaseManager.getConnection()) {
            System.out.println("Verbindung erfolgreich.\n");

            printBooks(conn);
            System.out.println();
            printCars(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printBooks(Connection conn) throws SQLException {
        String sql = "SELECT id, title, author, price FROM books";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Bücher (books) ===");
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price")
                );
                System.out.printf("ID=%d | %s | %s | %.2f EUR%n",
                        book.getId(), book.getTitle(), book.getAuthor(), book.getPrice());
            }
        }
    }

    private static void printCars(Connection conn) throws SQLException {
        String sql = "SELECT id, brand, year, price FROM cars";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Autos (cars) ===");
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getInt("year"),
                        rs.getDouble("price")
                );
                System.out.printf("ID=%d | %s | Baujahr %d | %.2f EUR%n",
                        car.getId(), car.getBrand(), car.getYear(), car.getPrice());
            }
        }
    }
}
