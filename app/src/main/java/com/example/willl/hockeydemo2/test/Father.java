package com.example.willl.hockeydemo2.test;

import android.util.Log;


public class Father {
    private String name;
    private Integer age;

    public static Father newInstance(String name,Integer age){
        Father father = new Father();
        father.setAge(age);
        father.setName(name);
        return father;
    }

    public void say(){
        Log.e("",name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
