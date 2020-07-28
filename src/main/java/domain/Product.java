package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//@JacksonXmlRootElement(localName = "items", namespace = "sameUri")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "item")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Product {


    private String category;
    private String subcategory;
    private String manufacturer;
    private String date;
    private String model;
    private String color;
    private Double price;
    private int quantity;

    public Product(String category, String subcategory, String manufacturer, String date, String model, String color, Double price, int quantity) {
        this.category = category;
        this.subcategory = subcategory;
        this.manufacturer = manufacturer;
        this.date = date;
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
                ", date='" + date + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @XmlAttribute
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
