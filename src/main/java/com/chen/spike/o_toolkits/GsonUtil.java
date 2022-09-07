package com.chen.spike.o_toolkits;

import com.google.gson.Gson;

public class GsonUtil {

    private static final Gson GSON;

    static {
        GSON = new Gson();
    }

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

}
