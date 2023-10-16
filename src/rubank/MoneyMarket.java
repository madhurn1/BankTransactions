package rubank;

import java.text.DecimalFormat;

public class MoneyMarket extends Savings{
    int withdrawal; //number of withdrawals
    private static final double MONTHLY_FEE = 25.0;
    private static final double INT_RATE = 0.045;
    private static final double LOYALTY_INT_RATE = 0.0475;

    public MoneyMarket(Profile holder, double balance){
        super(holder, balance, true);       //loyal customer default
        this.withdrawal = 0;
    }

    public int getWithdrawals() {
        return withdrawal;
    }

    public void setLoyalty(boolean newLoyalty) {
        isLoyal = newLoyalty;
    }

    public void incWithdrawal() {
        withdrawal++;
    }

    @Override
    public double monthlyInterest() {
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
        if (balance < 2000.0) {
            return MONTHLY_FEE;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        String writtenBalance = formatter.format(balance);
        String loyalty = "";
        if (isLoyal) loyalty += "::is loyal";
        return "Money Market::" + holder.getFname() + " " + holder.getLname() + " " + holder.getDOB() + "::Balance " + writtenBalance + loyalty + "withdrawal: " + withdrawal;
    }

    @Override
    public int compareTo(Account obj) {
        String thisAccountType = this.toString();
        String oAccType = obj.toString();
        thisAccountType = thisAccountType.substring(0, thisAccountType.indexOf("::"));
        oAccType = oAccType.substring(0, oAccType.indexOf("::"));
        int accTypeCompare = thisAccountType.compareTo(oAccType);
        if (accTypeCompare != 0) {
            return accTypeCompare;
        }
        return this.holder.compareTo(obj.holder);
    }
}
