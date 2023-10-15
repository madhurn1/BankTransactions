package rubank;

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;

    public abstract double monthlyInterest();

    public abstract double monthlyFee();

    public Account(Profile holder, double balance) {
        this.holder = holder;
        this.balance = balance;
    }


    public double getBalance() {
        return balance;
    }

    public Profile getHolder() {
        return holder;
    }

    public abstract String toString();

    public abstract int compareTo(Account obj);
}
