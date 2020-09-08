package com.af.null_object.dynamic;

import java.lang.reflect.Proxy;

interface Log{
    void info(String message);
    void warn(String message);
}

class BankAccount{
    private Log log;
    private int balance;

    public BankAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount){
        balance+=amount;
        log.info("Deposited "+amount);
    }
}

public class Demo {

    @SuppressWarnings("unchecked")
    public static <T> T noOperations(Class<T> itf){
        return (T) Proxy.newProxyInstance(itf.getClassLoader(),
                                            new Class<?>[]{itf},
                                            (proxy, method, arguments) -> {
                                                if(method.getReturnType().equals(Void.TYPE)){
                                                    return null;
                                                }else{
                                                    return method.getReturnType().getConstructor().newInstance();
                                                }
                                            });
    }

    public static void main(String[] args) {
        Log log = noOperations(Log.class);
        BankAccount bankAccount = new BankAccount(log);
        bankAccount.deposit(100);
    }
}
