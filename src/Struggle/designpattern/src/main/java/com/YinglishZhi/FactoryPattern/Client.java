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

    public List<List<Integer[]>> permute(int[] nums) {
        // write your code here
        return this.permute(nums, 0);
    }

    public List<List<Integer[]>> permute(int[] nums, int start) {
        // write your code here

        if(start == nums.length){
            List<Integer[]> tem = new ArrayList<Integer[]>();
            tem.add(nums);
             result.add(tem);
        }else{
            for (int i = start; i < nums.length;++i){
                swap(nums, i, start);
                permute(nums, start + 1);
                swap(nums, i, start);
            }
        }
        return result;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
