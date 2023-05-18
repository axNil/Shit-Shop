package request;

import application.Distributer;
import beans.Product;
import beans.SearchBean;
import com.google.gson.Gson;
import filter.FilterCriteria;
import io.javalin.http.Context;
import presentation.CriteriaMapper;

import java.util.ArrayList;
import java.util.List;

public class SearchRequest extends UnsafeRequest {
    @Override
    protected void handle(Context ctx) {
        SearchBean sb = new Gson().fromJson(ctx.body(), SearchBean.class);

        //create filter criterias based on json message from client
        List<FilterCriteria> criterias = new ArrayList<>();
        if(sb != null) {
            criterias = CriteriaMapper.fromJSON(sb);
        }

        //filter products based on criterias
        List<Product> result = Distributer.getInstance()
                .getProductManager().productSearch(criterias);

        ctx.status(200).json(result);
    }
}
