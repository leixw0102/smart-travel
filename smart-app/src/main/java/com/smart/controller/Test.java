package com.smart.controller;

/**
 * Created by Lenovo on 2015/8/7.
 */
public class Test {
    public static void main(String[]args){
        String str ="175\n" +
                "176";
        for(String a:str.split("\n")){
            System.out.println("update user set seller_name=(select name from life where user_id="+a+") where userId="+a+";");
        }
    }
}
