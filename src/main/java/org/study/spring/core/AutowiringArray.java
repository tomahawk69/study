package org.study.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
@ComponentScan
public class AutowiringArray {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(AutowiringArray.class);
        MyClass1 obj = (MyClass1) config.getBean("myClass1");
        System.out.println(obj);
    }
}

@Component
class MyClass1 {
    @Autowired
    MyObject[] values;

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}

interface MyObject {}

@Component
class MyObj1 implements MyObject {
    @Override
    public String toString() {
        return "MyObj1";
    }
}
@Component
class MyObj2 implements MyObject {
    @Override
    public String toString() {
        return "MyObj2";
    }
}
