package application.filter;

import beans.Product;
import enums.Condition;

import java.util.ArrayList;
import java.util.List;

public class ConditionMinCriteria implements FilterCriteria {
    private Condition condition;
    public ConditionMinCriteria(Condition condition){
        this.condition = condition;
    }
    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getCondition().getValue() >= condition.getValue())
                list.add(p);
        }
        return list;
    }
}

