package weidig.adrian.java_j2ee.Aufgabe4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weidig.adrian.java_j2ee.Aufgabe4.model.Car;
import weidig.adrian.java_j2ee.Aufgabe4.repository.CarRepository;

/**
 * AUFGABE 4.3: CarsServlet
 * Standard: Servlet 4.0 mit @WebServlet Annotation
 * Pattern: Validierung + Fehler-Liste
 */
@WebServlet("/cars")
public class CarsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String brand = request.getParameter("brand");
        String yearFromStr = request.getParameter("yearFrom");
        String maxPriceStr = request.getParameter("maxPrice");
        
        List<String> errors = new ArrayList<>();
        List<Car> cars = null;
        int yearFrom = 0;
        double maxPrice = 0;
        
        // Validierung Baujahr
        if (yearFromStr != null && !yearFromStr.trim().isEmpty()) {
            try {
                yearFrom = Integer.parseInt(yearFromStr);
                if (yearFrom < 1900) {
                    errors.add("Baujahr muss mindestens 1900 sein.");
                }
            } catch (NumberFormatException e) {
                errors.add("Baujahr muss eine ganze Zahl sein.");
            }
        }
        
        // Validierung Maximalpreis
        if (maxPriceStr != null && !maxPriceStr.trim().isEmpty()) {
            try {
                maxPrice = Double.parseDouble(maxPriceStr);
                if (maxPrice < 0) {
                    errors.add("Preis darf nicht negativ sein.");
                }
            } catch (NumberFormatException e) {
                errors.add("Preis muss eine Dezimalzahl sein.");
            }
        }
        
        // Nur bei korrekter Validierung suchen
        if (errors.isEmpty()) {
            CarRepository carRepo = new CarRepository();
            cars = carRepo.searchCars(brand, yearFrom, maxPrice);
        }
        
        request.setAttribute("cars", cars);
        request.setAttribute("errors", errors);
        request.setAttribute("inputBrand", brand != null ? brand : "");
        request.setAttribute("inputYearFrom", yearFromStr != null ? yearFromStr : "");
        request.setAttribute("inputMaxPrice", maxPriceStr != null ? maxPriceStr : "");
        
        request.getRequestDispatcher("/views/Aufgabe4/carSearch.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
