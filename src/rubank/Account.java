package rubank;

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;
    public abstract double monthlyInterest();
    public abstract double monthlyFee();
}
public Account(Profile holder,double balance){
    this.holder=holder;
    this.balance=balance;
}
public abstract double monthlyInt();
public abstract double monthlyFee();

public Account getBate() {return balance;}

public int compareTo(Account obj){
}
