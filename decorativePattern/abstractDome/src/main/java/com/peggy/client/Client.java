package com.peggy.client;

import com.peggy.abstracts.BigTrouser;
import com.peggy.abstracts.Finery;
import com.peggy.abstracts.Tshirts;

import java.util.logging.Filter;

/**
 * @Projectname: designMode
 * @Filename: Client
 * @Author: peggy
 * @Data:2023/5/24 14:45
 * @Description: TODO
 */

public class Client {
    public static void main(String[] args) {
        Finery bigTrouser=new BigTrouser();
        bigTrouser.Show();
        Finery tShirts=new Tshirts();
        tShirts.Show();
    }
}
