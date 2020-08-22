package com.af.singleton.testability;

import java.util.HashMap;
import java.util.function.Supplier;

public class SingletonTester {

    public static boolean isSingleton(Supplier<Object> func){
        Object obj1 = func.get();
        Object obj2 = func.get();

        return (obj1 == obj2);
    }
}
