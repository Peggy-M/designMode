package com.peggy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Peggy
 * @Date 2023-05-19 11:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String name;
    Integer age;
}
