package com.af.command.bank;

import java.util.List;

class BankAccount{
    private int balance;
    private int overDraftLimit = -500;

    public void deposit(int amount){
        balance+=amount;
        System.out.println("Deposited amount "+amount+", total balance : "+balance);
    }

    public void withdraw(int amount){
        if(balance-amount >= overDraftLimit){
            balance-=amount;
            System.out.println("Withdrew amount "+amount+", total balance : "+balance);
        }
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
}

class BankAccountCommand implements Command{
    private BankAccount bankAccount;

    @Override
    public void call() {
        switch(action){
            case DEPOSIT:
                bankAccount.deposit(amount);
                break;
            case WITHDRAW:
                bankAccount.withdraw(amount);
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

        final List<BankAccountCommand> commands = List.of(new BankAccountCommand(bankAccount, BankAccountCommand.Action.DEPOSIT, 100),
                                                          new BankAccountCommand(bankAccount, BankAccountCommand.Action.WITHDRAW, 1000));

        for(BankAccountCommand command : commands){
            command.call();
            System.out.println(bankAccount);
        }
    }
}
