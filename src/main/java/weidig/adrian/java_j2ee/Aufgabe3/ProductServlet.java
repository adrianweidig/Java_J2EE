package weidig.adrian.java_j2ee.Aufgabe3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/aufgabe3/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Produktliste erstellen
        List<Produkt> products = new ArrayList<>();

        products.add(new Produkt(1, "Laptop", 899.99));
        products.add(new Produkt(2, "Smartphone", 699.99));
        products.add(new Produkt(3, "Monitor", 299.99));
        products.add(new Produkt(4, "Tastatur", 79.99));
        products.add(new Produkt(5, "Maus", 39.99));

        // Attribute setzen und zur JSP forwarden
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/Aufgabe3/products.jsp").forward(request, response);
    }
}
