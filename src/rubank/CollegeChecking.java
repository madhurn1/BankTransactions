package rubank;

public class CollegeChecking extends Checking {
    private Campus campus; //campus code 0 - New Brunswick, 1 - Newark, 2 - Camden

    public CollegeChecking(Profile holder, double balance, Campus campus){
            super(holder,balance);
            this.campus=campus;
    }
}