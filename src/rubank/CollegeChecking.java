package rubank;

public class CollegeChecking extends Checking {
    private Campus campus; //campus code

    private static final double INT_RATE = 0.01; //1%
    private static final double MONTHLY_FEE = 0;

    public CollegeChecking(Profile holder, double balance, Campus campus){
            super(holder,balance);
            this.campus = campus;
    }

    public Campus getCampus() {
        return campus;
    }

    @Override
    public double monthlyInterest() {
        return balance * (INT_RATE/12);
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