package weidig.adrian.java_j2ee;


import weidig.adrian.java_j2ee.Aufgabe4.model.Car;
import weidig.adrian.java_j2ee.repository.CarRepositoryDB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/carsDB")
public class CarSearchServletDB extends HttpServlet {
    private CarRepositoryDB carRepo;

    @Override
    public void init() throws ServletException {
        carRepo = new CarRepositoryDB();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String brand = request.getParameter("brand") != null ? request.getParameter("brand") : "";
        List<Car> cars = carRepo.searchByBrand(brand);
        request.setAttribute("cars", cars);
        request.setAttribute("searchBrand", brand);
        request.getRequestDispatcher("/views/carSearchDB.jsp").forward(request, response);
    }
}
