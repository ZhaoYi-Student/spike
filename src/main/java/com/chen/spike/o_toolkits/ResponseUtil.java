package com.chen.spike.o_toolkits;

import com.chen.spike.o_common.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static <T> void writeResponse(HttpServletRequest request, HttpServletResponse response, ResponseEntity<T> result) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(GsonUtil.toJson(result));
    }

}
