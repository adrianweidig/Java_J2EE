package weidig.adrian.java_j2ee;


import weidig.adrian.java_j2ee.Aufgabe4.model.Book;
import weidig.adrian.java_j2ee.repository.BookRepositoryDB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/booksDB")
public class BooksServletDB extends HttpServlet {
    private BookRepositoryDB bookRepo;

    @Override
    public void init() throws ServletException {
        bookRepo = new BookRepositoryDB();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookRepo.findAll();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/views/booksDB.jsp").forward(request, response);
    }
}
