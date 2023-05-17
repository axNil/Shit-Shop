package presentation;

import application.filter.*;
import beans.SearchBean;

import java.util.ArrayList;
import java.util.List;

public class CriteriaMapper {
    public static List<FilterCriteria> fromJSON(SearchBean searchBean) {
        List<FilterCriteria> list = new ArrayList<>();
        if (searchBean.productType != null)
            list.add(new ProductTypeCriteria(searchBean.productType));
        if (searchBean.conditionMax != null)
            list.add(new ConditionMaxCriteria(searchBean.conditionMax));
        if (searchBean.conditionMin != null)
            list.add(new ConditionMinCriteria(searchBean.conditionMin));
        if (searchBean.priceMax >= 0)
            list.add(new PriceMaxCriteria(searchBean.priceMax));
        if (searchBean.priceMin >= 0)
            list.add(new PriceMinCriteria(searchBean.priceMin));

        return list;
    }
}
