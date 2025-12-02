package weidig.adrian.java_j2ee.Aufgabe4.repository;

import java.util.ArrayList;
import java.util.List;
import weidig.adrian.java_j2ee.Aufgabe4.model.Book;

/**
 * BookRepository - Data Access Layer
 * Simuliert In-Memory Datenbankzugriff
 */
public class BookRepository {

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Java EE 8", "Adrian Weidig", 49.99));
        books.add(new Book(2, "Design Patterns", "Gang of Four", 59.99));
        books.add(new Book(3, "Clean Code", "Robert Martin", 39.99));
        books.add(new Book(4, "Refactoring", "Martin Fowler", 44.99));
        books.add(new Book(5, "Spring in Action", "Craig Walls", 54.99));
        return books;
    }

    public Book getBook(int id) {
        for (Book book : getAllBooks()) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
