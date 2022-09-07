package com.chen.spike.o_common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpStatusCode {

    OK("1000010", "访问成功"),
    FAIL("200010", "访问失败"),
    AUTHENTICATION("300010", "访问权限不足");

    private final String code;
    private final String msg;

}
