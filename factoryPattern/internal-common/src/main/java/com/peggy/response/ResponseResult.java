package com.peggy.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Peggy
 * @Date 2023-05-17 17:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    Integer code;
    String message;
    T data;

}
