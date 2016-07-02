package org.study.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
@ComponentScan
public class CustomQualifier {

    @Target({ElementType.FIELD,
            ElementType.METHOD,
            ElementType.TYPE,
            ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Qualifier
    public static @interface Platform {
        PlatformType value();
        enum PlatformType {
            ANDROID, WINDOWS
            }
    }

    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(CustomQualifier.class);
        MyClass obj = (MyClass) ctx.getBean("myClass");
        System.out.println(obj.getAndroid());
        System.out.println(obj.getWindows());
    }
}

@Component
class MyClass {

    @Autowired
    @CustomQualifier.Platform(CustomQualifier.Platform.PlatformType.ANDROID)
    private MyInterface android;

    @Autowired
    @CustomQualifier.Platform(CustomQualifier.Platform.PlatformType.WINDOWS)
    private MyInterface windows;

    public MyInterface getAndroid() {
        return android;
    }

    public MyInterface getWindows() {
        return windows;
    }

    @PostConstruct
    public void qualifyTheTweets() {
        System.out.println("windows:" + this.windows);
        System.out.println("android:" + this.android);
    }

}

interface MyInterface {}

@Component
@CustomQualifier.Platform(CustomQualifier.Platform.PlatformType.ANDROID)
class MyInterfaceImplAndroid implements MyInterface {
    @Override
    public String toString() {
        return "MyInterfaceImplWindows";
    }
}

@Component
@CustomQualifier.Platform(CustomQualifier.Platform.PlatformType.WINDOWS)
class MyInterfaceImplWindows implements MyInterface {
    @Override
    public String toString() {
        return "MyInterfaceImplAndroid";
    }
}
