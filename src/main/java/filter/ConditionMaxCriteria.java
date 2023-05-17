package filter;

import beans.Product;
import enums.Condition;

import java.util.ArrayList;
import java.util.List;

public class ConditionMaxCriteria implements FilterCriteria {
    private Condition condition;
    public ConditionMaxCriteria(Condition condition){
        this.condition = condition;
    }
    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getCondition().getValue() <= condition.getValue())
                list.add(p);
        }
        return list;
    }
}
