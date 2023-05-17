package presentation;

import beans.SearchBean;
import filter.*;

import java.util.ArrayList;
import java.util.List;

public class CriteriaMapper {
    public static List<FilterCriteria> fromJSON(SearchBean searchBean) {
        List<FilterCriteria> list = new ArrayList<>();
        if (searchBean.productType != null)
            list.add(new ProductTypeCriteria(searchBean.productType));
        if (searchBean.color != null)
            list.add(new ColorCriteria(searchBean.color));
        if (searchBean.conditionMax != null)
            list.add(new ConditionMaxCriteria(searchBean.conditionMax));
        if (searchBean.conditionMin != null)
            list.add(new ConditionMinCriteria(searchBean.conditionMin));
        if (searchBean.yearOfProductionMax <= 0)
            list.add(new YOPMaxCriteria(searchBean.yearOfProductionMax));
        if (searchBean.yearOfProductionMin >= 0)
            list.add(new YOPMinCriteria(searchBean.yearOfProductionMin));
        if (searchBean.priceMax >= 0)
            list.add(new PriceMaxCriteria(searchBean.priceMax));
        if (searchBean.priceMin >= 0)
            list.add(new PriceMinCriteria(searchBean.priceMin));
        if (searchBean.name != null)
            list.add(new NameCriteria(searchBean.name));

        return list;
    }
}
