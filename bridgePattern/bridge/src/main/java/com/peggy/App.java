package com.peggy;

import com.peggy.service.Abstraction;
import com.peggy.service.RefinedAbstraction;
import com.peggy.service.impl.ConcretImplementorA;

import javax.swing.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Abstraction abstraction=new RefinedAbstraction();
        abstraction.setImplementor(new ConcretImplementorA());
        abstraction.operation();


    }
}
