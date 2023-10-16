package rubank;

import java.text.DecimalFormat;

public class CollegeChecking extends Checking {
    private final Campus campus; //campus code 0 - New Brunswick, 1 - Newark, 2 - Camden

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
        if (balance >= 1000.0) {
            return 0.0;
        }
        else {
            return MONTHLY_FEE;
        }
    }

    @Override
    public String toString() {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        String writtenBalance = formatter.format(balance);
        return "College Checking::" + holder.getFname() + " " + holder.getLname() + " " + holder.getDOB() + "::Balance " + writtenBalance + "::" + campus.toString();
    }
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