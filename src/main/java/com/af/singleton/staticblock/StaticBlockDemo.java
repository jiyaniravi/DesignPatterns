package com.af.singleton.staticblock;

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton{
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static StaticBlockSingleton getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(StaticBlockSingleton INSTANCE) {
        StaticBlockSingleton.INSTANCE = INSTANCE;
    }

    private StaticBlockSingleton(int value) throws IOException {
        this.value = value;
        System.out.println("Value : "+value);
        File.createTempFile(".",".");
    }

    private static StaticBlockSingleton INSTANCE;

    static{
        try{
            INSTANCE = new StaticBlockSingleton(5);
        }catch (IOException e){
            System.out.println("Exception occured : "+e);
        }
    }

    public static StaticBlockSingleton getInstance(){
        return INSTANCE;
    }
}

public class StaticBlockDemo {
    public static void main(String[] args) {
        StaticBlockSingleton singleton = StaticBlockSingleton.getInstance();
        System.out.println(singleton.getValue());
    }
}
