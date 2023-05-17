package beans;

import enums.Color;
import enums.Condition;
import enums.ProductType;

public class SearchBean {
    public ProductType productType;
    public double priceMin = -1;
    public double priceMax = -1;
    public String name;
    public int yearOfProductionMin = -1;
    public int yearOfProductionMax = -1;
    public Color color;
    public Condition conditionMin;
    public Condition conditionMax;
}
