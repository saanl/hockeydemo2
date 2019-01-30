package com.example.willl.hockeydemo2.test;

public class Son extends Father{
    private String name ;
    private Integer age;

    public static Father newInstance(String name,Integer age){
        Son son = new Son();
        son.setAge(age);
        son.setName(name);
        return son;
    }

    public void say(){
        int a =9;
    }
    public void doa(){
        int a =9;
    }
}
