package rubank;
import java.text.DecimalFormat;

public class Checking extends Account{
    private static final double INT_RATE = 0.01; //1%
    private static final double MONTHLY_FEE = 12.0;

    public Checking(Profile holder,double balance) {
        super(holder, balance);
    }

    public double getInterestRate() {
        return INT_RATE;
    }

    public double getMonthlyFee() {
        return MONTHLY_FEE;
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

    @Override
    public String toString() {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        String writtenBalance = formatter.format(balance);
        return "Checking::" + holder.getFname() + " " + holder.getLname() + holder.getDOB() + ":: Balance $" + writtenBalance;
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
