package rubank;

public class Savings extends Account {
    private static final double INT_RATE = 0.04;
    private static final double LOYALTY_INT_RATE = 0.0425;
    private static final double MONTHLY_FEE = 25.0;
    protected boolean isLoyal; //loyal customer status

    public Savings(Profile holder, double balance, boolean isLoyal) {
        super(holder, balance);
        this.isLoyal = isLoyal;
    }

    public double monthlyInterest(){
        double monthlyInt;
        if(isLoyal){
            monthlyInt=balance * (LOYALTY_INT_RATE/12);
        }
        else {
            monthlyInt = balance * (INT_RATE/12);
        }
        return monthlyInt;
    }

    public double monthlyFee(){
        if (balance >= 500.0){
            return 0.0;
        }
        else{
            return MONTHLY_FEE;
        }
    }
    @Override
    public int compareTo(Account other) {
        return this.holder.getLastName().compareTo(other.holder.getLastName());
    }
}