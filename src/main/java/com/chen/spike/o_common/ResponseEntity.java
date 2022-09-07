package com.chen.spike.o_common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseEntity<T> {

    private final String code;
    private final String msg;
    private final T body;

    public ResponseEntity(String code, String msg) {
        this(code, msg, null);
    }

    public ResponseEntity(String code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public static <T> ResponseEntity<T> ok(String msg, T body) {
        return new ResponseEntity<>(HttpStatusCode.OK.getCode(), msg, body);
    }

    public static <T> ResponseEntity<T> ok(T body) {
        return new ResponseEntity<>(HttpStatusCode.OK.getCode(), HttpStatusCode.OK.getMsg(), body);
    }

    public static <T> ResponseEntity<T> fail(String msg, T body) {
        return new ResponseEntity<>(HttpStatusCode.FAIL.getCode(), msg, body);
    }

    public static <T> ResponseEntity<T> fail(T body) {
        return new ResponseEntity<>(HttpStatusCode.FAIL.getCode(), HttpStatusCode.FAIL.getMsg(), body);
    }

}
