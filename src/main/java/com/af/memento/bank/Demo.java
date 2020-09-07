package com.af.memento.bank;

//Memento (Token) class can not be changed explicitly. So not having setter API as well for any property
class BankAccountToken{
    private int balance;

    public BankAccountToken(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}

class BankAccount{
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public BankAccountToken deposit(int amount){
        this.balance+=amount;
        return new BankAccountToken(balance);
    }

    public void restore(BankAccountToken token){
        balance = token.getBalance();
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

public class Demo {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100);
        BankAccountToken bt1=bankAccount.deposit(30);
        BankAccountToken bt2=bankAccount.deposit(35);
        System.out.println(bankAccount);

        //Restore to bt1
        bankAccount.restore(bt1);
        System.out.println(bankAccount);

        //Restore to bt2
        bankAccount.restore(bt2);
        System.out.println(bankAccount);
    }
}
