package beans;

import enums.Color;
import enums.Condition;
import enums.ProductType;

public class Product {
    private ProductType productType;
    private String name;
    private double price;
    private int yearOfProduction;
    private Color color;
    private Condition condition;
    private String seller;
    private int productID;
    private boolean isSold;

    public Product(ProductType productType, String name, double price, int yearOfProduction, Color color, Condition condition, String seller, int productID) {
        this.productType = productType;
        this.name = name;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.color = color;
        this.condition = condition;
        this.seller = seller;
        this.productID = productID;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
