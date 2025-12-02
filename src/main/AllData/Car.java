package weidig.adrian.java_j2ee.Aufgabe4.model;

/**
 * Car Entity - Standard POJO
 */
public class Car {
    private int id;
    private String brand;
    private int year;
    private double price;

    public Car(int id, String brand, int year, double price) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
