package com.YinglishZhi.FactoryPattern;

import com.YinglishZhi.FactoryPattern.Factory.Creator;
import com.YinglishZhi.FactoryPattern.Product.Product;
import com.YinglishZhi.FactoryPattern.Product.impl.ConcreteProduct1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Product p = Creator.getProduct(ConcreteProduct1.class.getName());
        p.func();

        Integer[] nums = new Integer[10];

        List<List<Integer[]>>  re = new ArrayList<List<Integer[]>>();
        List<Integer> te = new ArrayList<Integer>();
        List<Integer> numsList = Arrays.asList(nums);

        re.add((List<Integer[]>) numsList);

        String[] string=new String[]{"s1","s2","s3","s4","s5"};

        List<String> li= Arrays.asList(string);

        System.out.println(li);
    }





    private List<List<Integer[]>> result = new ArrayList<List<Integer[]>>();
    
}
