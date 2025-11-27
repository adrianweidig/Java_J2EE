package weidig.adrian.java_j2ee.Aufgabe3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet3", value = "/aufgabe3/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        try {
            // Validierung: id muss gesetzt sein
            if (idParam == null || idParam.trim().isEmpty()) {
                throw new IllegalArgumentException("Parameter 'id' ist erforderlich");
            }

            // id muss eine Zahl sein
            int id = Integer.parseInt(idParam);

            // id muss zwischen 1 und 9999 liegen
            if (id < 1 || id > 9999) {
                throw new IllegalArgumentException("ID muss zwischen 1 und 9999 liegen");
            }

            // Bei Erfolg: Daten setzen und zu user.jsp forwarden
            request.setAttribute("userId", id);
            request.setAttribute("userName", "Benutzer #" + id);
            request.setAttribute("email", "user" + id + "@example.com");
            request.getRequestDispatcher("/WEB-INF/views/Aufgabe3/user.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // HTTP 400 Bad Request setzen
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorMessage", "Fehler: Die ID muss eine ganze Zahl sein!");
            try {
                request.getRequestDispatcher("/WEB-INF/views/Aufgabe3/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            // HTTP 400 Bad Request setzen
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorMessage", "Fehler: " + e.getMessage());
            try {
                request.getRequestDispatcher("/WEB-INF/views/Aufgabe3/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
