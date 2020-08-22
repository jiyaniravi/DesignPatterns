package com.af.singleton.synchronize;

class LazySingleton{
    private static LazySingleton INSTANCE;

    private LazySingleton(){
        System.out.println("Lazy initialization of singleton object");
    }

    /*public static LazySingleton getInstance(){
        if(INSTANCE == null){
            return new LazySingleton();
        }
        return INSTANCE;
    }*/

    //Using synchronization
    /*public synchronized static LazySingleton getInstance(){
        if(INSTANCE == null){
            return new LazySingleton();
        }
        return INSTANCE;
    }*/

    //Double checked locking
    public static LazySingleton getInstance(){
        if(INSTANCE == null){
            synchronized (LazySingleton.class){
                if(INSTANCE ==null){
                    return new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}

public class DoubleCheckingDemo {
    public static void main(String[] args) {
        LazySingleton singleton = LazySingleton.getInstance();
    }
}
