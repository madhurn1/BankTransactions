package rubank;

import java.text.DecimalFormat;

public class Savings extends Account {
    private static final double INT_RATE = 0.04;
    private static final double LOYALTY_INT_RATE = 0.0425;
    private static final double MONTHLY_FEE = 25.0;
    protected boolean isLoyal; // loyal customer status

    public Savings(Profile holder, double balance, boolean isLoyal) {
        super(holder, balance);
        this.isLoyal = isLoyal;
    }

    public double getInterestRate() {
        return INT_RATE;
    }

    public double getLoyaltyIntRate() {
        return LOYALTY_INT_RATE;
    }

    public double getMonthlyFee() {
        return MONTHLY_FEE;
    }

    public boolean getLoyalty() {
        return isLoyal;
    }

    @Override
    public double monthlyInterest(){
        double monthlyInt;
        if (isLoyal) {
            monthlyInt = balance * (LOYALTY_INT_RATE / 12);
        }
        else {
            monthlyInt = balance * (INT_RATE / 12);
        }
        return monthlyInt;
    }

    @Override
    public double monthlyFee(){
        if (balance >= 500.0) {
            return 0.0;
        }
        else{
            return MONTHLY_FEE;
        }
    }

    @Override
    public String toString() {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        String writtenBalance = formatter.format(balance);
        String loyalty = "";
        if (isLoyal) loyalty += "::is loyal";
        return "Savings::" + holder.getFname() + " " + holder.getLname() + holder.getDOB() + "::Balance " + writtenBalance + loyalty;
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