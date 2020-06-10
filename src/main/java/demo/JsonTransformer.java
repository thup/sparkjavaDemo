package demo;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * @Author tlibn
 * @Date 2020/6/10 17:21
 **/
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}