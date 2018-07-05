package com.YinglishZhi.FactoryPattern.Factory;

import com.YinglishZhi.FactoryPattern.Product.Product;

public class Creator {
    public static Product getProduct(String ClassName){
        Product p = null;
        try {
            p = (Product) Class.forName(ClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return p;
    }
}
