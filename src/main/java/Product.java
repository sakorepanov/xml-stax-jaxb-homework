import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.*;

@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement(name="items")
public class Product {

    private String category;
    private String subcategory;
    private String manufacturer;
    private String model;
    private String color;
    private Double price;
    private int quantity;

    public Product(String category, String subcategory, String manufacturer, String model, String color, Double price, int quantity) {

        this.category = category;
        this.subcategory = subcategory;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
