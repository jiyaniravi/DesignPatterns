package com.af.singleton.basic;

class BasicSingleton{

    private BasicSingleton(){}

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getInstance(){
        return INSTANCE;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class Demo {
    public static void main(String[] args) {
        BasicSingleton basicSingleton = BasicSingleton.getInstance();
        basicSingleton.setValue(5);
        System.out.println(basicSingleton.getValue());
    }
}
