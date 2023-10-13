package rubank;

public class CollegeChecking extends Checking {
    private Campus campus; //campus code

    public CollegeChecking(Profile holder, double balance, Campus campus){
            super(holder,balance);
            this.campus=campus;
    }
}