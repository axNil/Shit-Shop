package beans;

import enums.Color;
import enums.Condition;
import enums.ProductType;

public class SearchBean {
    public ProductType productType;
    public double priceMin = Double.MIN_VALUE;
    public double priceMax = Double.MAX_VALUE;
    public String name;
    public int yearOfProductionMin = Integer.MIN_VALUE;
    public int yearOfProductionMax = Integer.MAX_VALUE;
    public Color color;
    public Condition conditionMin;
    public Condition conditionMax;
}
