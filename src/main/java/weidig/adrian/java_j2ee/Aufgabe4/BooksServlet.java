package weidig.adrian.java_j2ee.Aufgabe4;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weidig.adrian.java_j2ee.Aufgabe4.model.Book;
import weidig.adrian.java_j2ee.Aufgabe4.repository.BookRepository;

/**
 * AUFGABE 4.1: BooksServlet
 * Standard: Servlet 4.0 mit @WebServlet Annotation
 * Pattern: MVC - Controller Layer
 */
@WebServlet("/books")
public class BooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookRepository bookRepo = new BookRepository();
        List<Book> books = bookRepo.getAllBooks();

        request.setAttribute("books", books);
        request.getRequestDispatcher("/views/Aufgabe4/books.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
