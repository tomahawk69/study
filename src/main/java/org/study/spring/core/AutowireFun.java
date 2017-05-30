package org.study.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

interface AutowireFunI {}

class AutowireFunIImpl1 implements AutowireFunI{
    @Override
    public String toString() {
        return "AutowireFunIImpl1";
    }
}

@Component
@Primary
class AutowireFunIImpl2 implements AutowireFunI {
    @Override
    public String toString() {
        return "AutowireFunIImpl2";
    }
}

@Component("surpriseMe")
/*
or:
@Component
@Qualifier("surpriseMe")
*/
class AutowireFunIImpl3 implements AutowireFunI {
    @Override
    public String toString() {
        return "AutowireFunIImpl3";
    }
}

@Component
class AutowireFunIImpl4 implements AutowireFunI {
    @Override
    public String toString() {
        return "AutowireFunIImpl4";
    }
}

@Component
public class AutowireFun  {

    @Resource
    private AutowireFunI autowireFunIImpl1;
    @Resource
    private AutowireFunI autowireFunIImpl2;
    @Autowired
    private AutowireFunI autowireFunIImpl3;
    @Resource
    private AutowireFunI surpriseMe;
    @Resource
    @Qualifier("bad")
    private AutowireFunI autowireFunIImpl4;

    @Resource
    private AutowireFunI autowireFunIImpl5;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CommonConfiguration.class);
        AutowireFun obj = (AutowireFun) context.getBean("autowireFun");
        System.out.println("Resource by name: " + obj.autowireFunIImpl1);
        System.out.println("Resource by primary: " + obj.autowireFunIImpl2);
        System.out.println("Autowired by primary: " + obj.autowireFunIImpl3);
        System.out.println("Resource by custom name: " + obj.surpriseMe);
        System.out.println("Resource with bad qualifier, by name instead: " + obj.autowireFunIImpl4);
        System.out.println("Resource with bad qualifier, by name instead: " + obj.autowireFunIImpl5);
    }
}
