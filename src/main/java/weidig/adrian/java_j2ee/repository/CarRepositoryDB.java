package weidig.adrian.java_j2ee.repository;


import weidig.adrian.java_j2ee.Aufgabe4.model.Car;
import weidig.adrian.java_j2ee.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryDB {
    public List<Car> searchByBrand(String brand) {
        List<Car> cars = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cars WHERE brand LIKE ?")) {
            pstmt.setString(1, "%" + brand + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Car car = new Car(
                    rs.getInt("id"),
                    rs.getString("brand"),
                    rs.getInt("year"),
                    rs.getDouble("price")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Suchen nach Autos: " + e.getMessage());
        }
        return cars;
    }

    public List<Car> findAll() {
        return searchByBrand("");
    }
}
