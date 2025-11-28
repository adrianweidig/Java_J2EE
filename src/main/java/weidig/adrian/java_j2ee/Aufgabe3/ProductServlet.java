package weidig.adrian.java_j2ee.Aufgabe3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductServlet", value = "/aufgabe3/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Produktliste erstellen
        //List<Map<String, Object>> products = new ArrayList<>();
        List<Produkt> products = new ArrayList<>();

        products.add(new Produkt(1, "Laptop", 899.99));
        products.add(new Produkt(2, "Smartphone", 699.99));
        products.add(new Produkt(3, "Monitor", 299.99));
        products.add(new Produkt(4, "Tastatur", 79.99));
        products.add(new Produkt(5, "Maus", 39.99));


       /* Map<String, Object> p1 = new HashMap<>();
        p1.put("id", 1);
        p1.put("name", "Laptop");
        p1.put("price", 899.99);
        products.add(p1);

        Map<String, Object> p2 = new HashMap<>();
        p2.put("id", 2);
        p2.put("name", "Smartphone");
        p2.put("price", 699.99);
        products.add(p2);

        Map<String, Object> p3 = new HashMap<>();
        p3.put("id", 3);
        p3.put("name", "Monitor");
        p3.put("price", 299.99);
        products.add(p3);

        Map<String, Object> p4 = new HashMap<>();
        p4.put("id", 4);
        p4.put("name", "Tastatur");
        p4.put("price", 79.99);
        products.add(p4);

        Map<String, Object> p5 = new HashMap<>();
        p5.put("id", 5);
        p5.put("name", "Maus");
        p5.put("price", 39.99);
        products.add(p5); */

        // Attribute setzen und zur JSP forwarden
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/Aufgabe3/products.jsp").forward(request, response);
    }
}
