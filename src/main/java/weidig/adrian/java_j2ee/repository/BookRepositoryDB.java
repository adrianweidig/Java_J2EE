package weidig.adrian.java_j2ee.repository;


import weidig.adrian.java_j2ee.Aufgabe4.model.Book;
import weidig.adrian.java_j2ee.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryDB {
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getDouble("price")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Laden der Bücher: " + e.getMessage());
        }
        return books;
    }

    public Book findById(int id) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Laden des Buches: " + e.getMessage());
        }
        return null;
    }
}
