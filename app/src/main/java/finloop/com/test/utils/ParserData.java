package finloop.com.test.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParserData {
    public static <T> String getJSONFromModel(T object) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getModelFromJSON(T type, String json) {
        type = new Gson().fromJson(json, (Class<T>) type.getClass());
        return type;
    }
}
