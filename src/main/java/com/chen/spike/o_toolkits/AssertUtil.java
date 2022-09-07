package com.chen.spike.o_toolkits;

import com.chen.spike.o_ex.NotNullException;
import org.springframework.util.ObjectUtils;

public class AssertUtil {

    public static void isEmpty(Object obj, String msg) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new NullPointerException(msg);
        }
    }

    public static void isNotEmpty(Object obj, String msg) {
        if (!ObjectUtils.isEmpty(obj)) {
            throw new NotNullException(msg);
        }
    }

    public static void isNotEmpty(Object obj, RuntimeException exception) {
        if (!ObjectUtils.isEmpty(obj)) {
            throw exception;
        }
    }

}
