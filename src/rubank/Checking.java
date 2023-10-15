package rubank;

public class Checking extends Account{
    private static final double INT_RATE =0.01; //1%
    private static final double MONTHLY_FEE = 12.0;

    public Checking(Profile holder,double balance) {
        super(holder, balance);
    }

    @Override
    public double monthlyInterest() {
        return balance * (INT_RATE / 12);
    }

    @Override
    public double monthlyFee() {
        if (balance >= 1000.0){
            return 0.0;
        }
        else {
            return MONTHLY_FEE;
        }
    }
}
