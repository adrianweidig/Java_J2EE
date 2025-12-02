package weidig.adrian.java_j2ee.Aufgabe4.repository;

import java.util.ArrayList;
import java.util.List;
import weidig.adrian.java_j2ee.Aufgabe4.model.Car;

/**
 * CarRepository - Data Access Layer
 * Simuliert In-Memory Datenbankzugriff mit Suchlogik
 */
public class CarRepository {
    
    private List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, "BMW", 2018, 35000));
        cars.add(new Car(2, "Audi", 2019, 42000));
        cars.add(new Car(3, "Mercedes", 2020, 50000));
        cars.add(new Car(4, "BMW", 2017, 32000));
        cars.add(new Car(5, "Volkswagen", 2016, 25000));
        cars.add(new Car(6, "Audi", 2021, 48000));
        cars.add(new Car(7, "Mercedes", 2015, 38000));
        return cars;
    }
    
    public List<Car> searchCars(String brand, int yearFrom, double maxPrice) {
        List<Car> allCars = getAllCars();
        List<Car> results = new ArrayList<>();
        
        for (Car car : allCars) {
            boolean matches = true;
            
            // Filter Marke
            if (brand != null && !brand.trim().isEmpty()) {
                if (!car.getBrand().equalsIgnoreCase(brand.trim())) {
                    matches = false;
                }
            }
            
            // Filter Baujahr
            if (yearFrom > 0 && car.getYear() < yearFrom) {
                matches = false;
            }
            
            // Filter Preis
            if (maxPrice > 0 && car.getPrice() > maxPrice) {
                matches = false;
            }
            
            if (matches) {
                results.add(car);
            }
        }
        
        return results;
    }
}
