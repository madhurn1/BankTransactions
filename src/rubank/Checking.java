package rubank;

public class Checking extends Account{
    private static final double INT_RATE =0.01; //1%
    private static final double MONTHLY_FEE = 12.0;

    public checking(Profile holder,double balance) {
        super(holder,balance);
    }
    @Override
    public double monthlyInterestCalculation(){
        return balance * (INT_RATE/12);
    }
    @Override
    public double monthlyFeeCalculation(){
        if (balance >= 1000.0){
            return 0.0;
        }
        else {
            return MONTHLY_FEE;
        }
    }
}