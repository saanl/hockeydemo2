package com.example.willl.hockeydemo2.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public Object testthis() {
        Method newInstance = null;
        int ds = 9/0;
        Object invoke = null;
        try {
            newInstance = Father.class.getMethod("newInstance",String.class,Integer.class);
            invoke = newInstance.invoke(new Father(),"测试",200);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }

}
