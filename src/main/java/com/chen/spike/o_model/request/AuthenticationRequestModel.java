package com.chen.spike.o_model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthenticationRequestModel {

    private String userName;
    private String userPassword;

}
