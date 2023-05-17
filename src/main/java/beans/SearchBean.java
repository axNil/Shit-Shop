package beans;

import enums.Color;
import enums.Condition;
import enums.ProductType;

public class SearchBean {
    public ProductType productType;
    public double priceMin = -1;
    public double priceMax = -1;
    public String name;
    public String yearOfProduction;
    public Color color;
    public Condition conditionMin;
    public Condition conditionMax;
}
