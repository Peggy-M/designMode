package com.peggy.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Peggy
 * @Date 2023-05-17 17:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseResult<T> {

    private Integer code;
    private String message;
    private T data;

    public static ResponseResult fail(String message) {
        return new ResponseResult().setCode(400).setMessage(message);
    }
}
