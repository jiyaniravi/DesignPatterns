package com.af.multiton;

import java.util.HashMap;

enum SubSystem{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer{

    private static int instanceCount = 0;

    private Printer(){
        instanceCount++;
        System.out.println("Total instances "+instanceCount+" created so far");
    }

    private static HashMap<SubSystem, Printer> instances = new HashMap<>();

    public static Printer get(SubSystem s){
        if(instances.containsKey(s)){
            return instances.get(s);
        }

        Printer instance = new Printer();
        instances.put(s, instance);
        return instance;
    }
}

public class MultitonDemo {
    public static void main(String[] args) {
        Printer main = Printer.get(SubSystem.PRIMARY);
        Printer auxiliary1 = Printer.get(SubSystem.AUXILIARY);
        Printer auxiliary2 = Printer.get(SubSystem.AUXILIARY);
    }
}
