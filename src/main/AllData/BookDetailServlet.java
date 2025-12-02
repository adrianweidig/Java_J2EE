package weidig.adrian.java_j2ee.Aufgabe4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weidig.adrian.java_j2ee.Aufgabe4.model.Book;
import weidig.adrian.java_j2ee.Aufgabe4.repository.BookRepository;

/**
 * AUFGABE 4.2: BookDetailServlet
 * Standard: Servlet 4.0 mit @WebServlet Annotation
 * Pattern: Validierung + Error Handling
 */
@WebServlet("/book")
public class BookDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String error = null;
        Book book = null;

        if (idParam == null || idParam.trim().isEmpty()) {
            error = "Fehler: Buch-ID ist erforderlich.";
        } else {
            try {
                int bookId = Integer.parseInt(idParam);
                BookRepository bookRepo = new BookRepository();
                book = bookRepo.getBook(bookId);

                if (book == null) {
                    error = "Fehler: Buch mit ID " + bookId + " nicht gefunden.";
                }
            } catch (NumberFormatException e) {
                error = "Fehler: Buch-ID muss eine Zahl sein.";
            }
        }

        if (error != null) {
            request.setAttribute("error", error);
        } else {
            request.setAttribute("book", book);
        }

        request.getRequestDispatcher("/views/Aufgabe4/bookDetail.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
