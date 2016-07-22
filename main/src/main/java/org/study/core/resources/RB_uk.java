package org.study.core.resources;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class RB_uk extends ListResourceBundle {

    public static void main(String[] args) {
        String key = "test";
        String key1 = "test1";
        System.out.println(defaultBundle(key1));
        System.out.println(defaultBundle(key));
    }

    private static String defaultBundle(String key) {
        ResourceBundle bundleDefault = ResourceBundle.getBundle("org.study.core.resources.RB");
        return bundleDefault.getString(key);
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"test", "test from Java Class"}
        };
    }
}
