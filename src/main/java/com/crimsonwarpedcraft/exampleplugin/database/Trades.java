package com.crimsonwarpedcraft.exampleplugin.database;

import java.util.HashMap;

public class Trades {

    private static final HashMap<String, String> test = new HashMap<String, String>();


    public static void addToTest(String key, String value) {
        test.put(key, value);
    }

    public static HashMap<String, String> showTest(String key, String value) {
        return test;
    }

    public static void removeFromTest(String key) {
        test.remove(key);
    }

}
