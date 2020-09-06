package com.af.command.bank;

import com.google.common.collect.Lists;

import java.util.List;

class BankAccount{
    private int balance;
    private int overDraftLimit = -500;

    public void deposit(int amount){
        balance+=amount;
        System.out.println("Deposited amount "+amount+", total balance : "+balance);
    }

    public boolean withdraw(int amount){
        if(balance-amount >= overDraftLimit){
            balance-=amount;
            System.out.println("Withdrew amount "+amount+", total balance : "+balance);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command{
    void call();
    void undo();
}

class BankAccountCommand implements Command{
    private BankAccount bankAccount;
    private boolean succeeded;

    @Override
    public void call() {
        switch(action){
            case DEPOSIT:
                bankAccount.deposit(amount);
                succeeded = true;
                break;
            case WITHDRAW:
                succeeded = bankAccount.withdraw(amount);
                break;
        }
    }

    @Override
    public void undo() {

        if(!succeeded){
            return;
        }

        switch(action){
            case DEPOSIT:
                bankAccount.withdraw(amount);
                break;
            case WITHDRAW:
                bankAccount.deposit(amount);
                break;
        }
    }

    public enum Action{
        DEPOSIT, WITHDRAW
    }

    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount bankAccount, Action action, int amount) {
        this.bankAccount = bankAccount;
        this.action = action;
        this.amount = amount;
    }
}

public class Demo {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        System.out.println(bankAccount);

        List<BankAccountCommand> commands = List.of(new BankAccountCommand(bankAccount, BankAccountCommand.Action.DEPOSIT, 100),
                                                    new BankAccountCommand(bankAccount, BankAccountCommand.Action.WITHDRAW, 1000));

        for(Command command : commands){
            command.call();
            System.out.println(bankAccount);
        }

        for(Command command : Lists.reverse(commands)){
            command.undo();
            System.out.println(bankAccount);
        }

    }
}
