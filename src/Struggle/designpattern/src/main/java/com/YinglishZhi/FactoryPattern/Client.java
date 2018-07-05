package com.YinglishZhi.FactoryPattern;

import com.YinglishZhi.FactoryPattern.Factory.Creator;
import com.YinglishZhi.FactoryPattern.Product.Product;
import com.YinglishZhi.FactoryPattern.Product.impl.ConcreteProduct1;

public class Client {
    public static void main(String[] args) {
        Product p = Creator.getProduct(ConcreteProduct1.class.getName());
        p.func();
    }
}
