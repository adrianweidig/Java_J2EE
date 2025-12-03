package weidig.adrian.java_j2ee;

import weidig.adrian.java_j2ee.model.Book;
import weidig.adrian.java_j2ee.repository.BookRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/booksJPA")
public class BooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> books = BookRepository.findAll();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/views/books.jsp").forward(request, response);
    }
}
