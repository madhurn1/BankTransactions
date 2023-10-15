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

    @Override
    public int compareTo(Account obj) {
        int accTypeCompare = this.toString().compareTo(obj.toString());
        if (accTypeCompare != 0) {
            return accTypeCompare;
        }
        return this.holder.compareTo(obj.holder);
    }
}
