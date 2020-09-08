package com.af.null_object.statick;

interface Log{
    void info(String message);
    void warn(String message);
}

class ConsoleLog implements Log{

    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public void warn(String message) {
        System.out.println("WARNING : "+message);
    }
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
    public static void main(String[] args) {
        ConsoleLog consoleLog = new ConsoleLog();
        BankAccount bankAccount = new BankAccount(null);
        bankAccount.deposit(100);
    }
}
