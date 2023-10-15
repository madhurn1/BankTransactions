package rubank;

public class MoneyMarket extends Savings{
    int withdrawal; //number of withdrawals
    private static final double MONTHLY_FEE = 10.0;
    private static final double INT_RATE = 0.045;
    private static final double LOYALTY_INT_RATE = 0.0475;

    public MoneyMarket(Profile holder, double balance){
        super(holder, balance, true);       //loyal customer default
        this.withdrawal = 0;
    }

    public int getWithdrawals() {
        return withdrawal;
    }

    @Override
    public double monthlyInterestCalculation() {
        if (balance >= 2000.0){
            if(isloyal){
                return balance * (LOYALTY_INT_RATE / 12);
            }
            else{
                return balance * (INT_RATE / 12);
            }
            return
        }
        else{
            isLoyal = false;
            return 0.0;
        }
    }

    @Override
    public double monthlyFeeCalculation(){
        if(balance < 2000.0 || withdrawal > 3){
            return MONTHLY_FEE;
        }
        return 0.0;
    }
}
