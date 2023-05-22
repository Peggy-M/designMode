package com.peggy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Projectname: designMode
 * @Filename: User
 * @Author: peggy
 * @Data:2023/5/22 21:51
 * @Description: TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;

    private int age;
}
