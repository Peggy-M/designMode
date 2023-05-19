package com.peggy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Peggy
 * @Date 2023-05-19 11:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    String name;
    Integer age;
}
