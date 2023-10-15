package rubank;
import java.util.Scanner;

/**
 * User interface to process command line input for the Event Calendar.
 * Can process a single or multiple lines at once.
 * @author Dany Chucri, Madhur Nutulapati
 */
public class TransactionManager {
    private AccountDatabase accountDatabase;

    private static final int OPEN_INDICATION = 1;
    private static final int CLOSE_INDICATION = 2;
    private static final int DEPOSIT_INDICATION = 3;
    private static final int WITHDRAW_INDICATION = 4;

    /**
     * Instantiates the event organizer using Event Calendar.
     */
    public TransactionManager(){
        accountDatabase = new AccountDatabase();
    }

    /**
     * Begins the reading of standard input, parsing and executing commands for the Event Organizer.
     */
    public void run(){
        Scanner S = new Scanner(System.in);
        System.out.println("Transaction Manager is running.");
        while(true){
            String fullCommand = S.nextLine().trim();//read + trim
            if(fullCommand.isEmpty())//blank line
                continue;
            String result = fullCommand .replaceAll("\s+", " ");
            String[] token = result.split(" ");
            String command = token[0];
            switch(command){
                case "O":
                    oCommand(token);
                    break;
                case "C":
                    cCommand(token);
                    break;
                case "D":
                    dCommand(token);
                    break;
                case "W":
                    wCommand(token);
                    break;
                case "P":
//                    pcCommand();
                    break;
                case "PI":
//                    pdCommand();
                    break;
                case "UB":
//                    pdCommand();
                    break;
                case "Q":
                    System.out.println("Transaction Manager is terminated.");
                    System.exit(0);
                default:
                    System.out.println("Invalid command!");
            }
        }
    }
    private int bankType(String entry) {
        if (entry.equals("C"))
            return 1;

        else if (entry.equals("CC"))
            return 2;

        else if (entry.equals("MM"))
            return 3;

        else if (entry.equals("S"))
            return 4;

        else
            return 5;
    }
    private void createChecking(Profile addProfile,double balance,int operation){
        Checking addAccount = new Checking(addProfile,balance);
        if(operation==OPEN_INDICATION){
            if(accountDatabase.open(addAccount))
                System.out.println("Account opened");
            else
                System.out.println("The account is already on the database.");
            //add else everywhere
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                System.out.println("Account has been removed from the database!");
            else
                System.out.println("ERROR closing account");
        } else if (operation==DEPOSIT_INDICATION) {
            accountDatabase.deposit(addAccount);
            System.out.println("Account has been deposited into!");
        } else if (operation==WITHDRAW_INDICATION) {
            if(accountDatabase.withdraw(addAccount))
                System.out.println("Money has been withdrawn");
            else
                System.out.println("Error withdrawing!");
        } else{
            System.out.println("The account is already on the database.");
        }
    }
    private void createCollegeChecking(Profile addProfile,double balance, Campus code,int operation){
        CollegeChecking addAccount = new CollegeChecking(addProfile,balance,code);
        if(operation==OPEN_INDICATION){
            if(accountDatabase.open(addAccount))
                System.out.println("Account opened");
            else
                System.out.println("The account is already on the database.");
            //add else everywhere
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                System.out.println("Account has been removed from the database!");
            else
                System.out.println("ERROR closing account");
        } else if (operation==DEPOSIT_INDICATION) {
            accountDatabase.deposit(addAccount);
            System.out.println("Account has been deposited into!");
        } else if (operation==WITHDRAW_INDICATION) {
            if(accountDatabase.withdraw(addAccount))
                System.out.println("Money has been withdrawn");
            else
                System.out.println("Error withdrawing!");
        } else{
            System.out.println("The account is already on the database.");
        }
    }
    private void createMoneyMarket(Profile addProfile,double balance,int operation){
        MoneyMarket addAccount = new MoneyMarket(addProfile,balance);
        if(operation==OPEN_INDICATION){
            if(accountDatabase.open(addAccount))
                System.out.println("Account opened");
            else
                System.out.println("The account is already on the database.");
            //add else everywhere
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                System.out.println("Account has been removed from the database!");
            else
                System.out.println("ERROR closing account");
        } else if (operation==DEPOSIT_INDICATION) {
            accountDatabase.deposit(addAccount);
            System.out.println("Account has been deposited into!");
        } else if (operation==WITHDRAW_INDICATION) {
            if(accountDatabase.withdraw(addAccount))
                System.out.println("Money has been withdrawn");
            else
                System.out.println("Error withdrawing!");
        } else{
            System.out.println("The account is already on the database.");
        }
    }
    private void createSavings(Profile addProfile,double balance, int loyal,int operation){
        boolean loyalKey=false;//check default val
        if(loyal==1)
            loyalKey=true;
        else
            loyalKey=false;
        Savings addAccount = new Savings(addProfile, balance, loyalKey);
        if(operation==OPEN_INDICATION){
            if(accountDatabase.open(addAccount))
                System.out.println("Account opened");
            else
                System.out.println("The account is already on the database.");
            //add else everywhere
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                System.out.println("Account has been removed from the database!");
            else
                System.out.println("ERROR closing account");
        } else if (operation==DEPOSIT_INDICATION) {
            accountDatabase.deposit(addAccount);
            System.out.println("Account has been deposited into!");
        } else if (operation==WITHDRAW_INDICATION) {
            if(accountDatabase.withdraw(addAccount))
                System.out.println("Money has been withdrawn");
            else
                System.out.println("Error withdrawing!");
        } else{
            System.out.println("The account is already on the database.");
        }
    }
    /**
     * Command for adding an Event to the Event Calendar.
     * @param token An array of tokens from the command-line arguments.
     */
    private void oCommand(String[] token){
        if(token.length==1){
            System.out.println("Invalid Command!");
            return;
        }
        if(token.length>=2 && token.length<=4){
            System.out.println("Account Database is empty!");
            return;
        }
        try{
            Date dateInput = createDate(token[5]);
            if(dateInput == null){
                System.out.println("Invalid tokens");
                return;
            }
            Profile addProfile = new Profile(token[2],token[3],dateInput);
            int key = bankType(token[1]);

            if(key==1)
                createChecking(addProfile,Double.parseDouble(token[5]),OPEN_INDICATION);
            else if(key==2){
                Campus campCode= Campus.valueOf(token[1]);
                createCollegeChecking(addProfile,Double.parseDouble(token[5]),campCode,OPEN_INDICATION);
            }
            else if(key ==3)
                createMoneyMarket(addProfile,Double.parseDouble(token[5]),OPEN_INDICATION);
            else if(key == 4)
                createSavings(addProfile,Double.parseDouble(token[5]),Integer.parseInt(token[6]),OPEN_INDICATION);
            else
                System.out.println("Not valid Bank Type");//specify
        } catch (Exception e){
            System.out.println("Error with adding an account.");
        }
    }

    /**
     * Instantiates a Date object to be used for the creation of an Event.
     * Performs error checks on the validity of a date.
     * @param date A String token in the form of "xx/xx/xxxx".
     * @return The Date object to be used.
     */

    private Date createDate(String date){
        Date addDate = new Date(date);
        if(addDate.isValid() == 1){
            System.out.println(date + ": Invalid calendar date!");
            return null;
        } else if (addDate.isValid() == 2) {
            System.out.println(date + ": Event date must be a future date!");
            return null;
        } else if (addDate.isValid() == 3) {
            System.out.println(date + ": Event date must be within 6 months!");
            return null;
        }
        return addDate;
    }

    /**
     * Command for removing an Event from the Event Calendar.
     * @param token An array of tokens from the command-line arguments.
     */
    private void cCommand(String[] token){
        if(token.length!=4){
            System.out.println("Invalid command format.");
            return;
        }
        try{
            Date dateInput = createDate(token[4]);

            if (dateInput == null) return;

            Profile closeAccount = new Profile(token[2],token[3],dateInput);

            int key = bankType(token[1]);

            if(key==1)
                createChecking(closeAccount,0,CLOSE_INDICATION);
            else if(key==2)
                createCollegeChecking(closeAccount,0,null,CLOSE_INDICATION);
            else if(key ==3)
                createMoneyMarket(closeAccount,0,CLOSE_INDICATION);
            else if(key == 4)
                createSavings(closeAccount,0,0,CLOSE_INDICATION);
            else
                System.out.println("Not valid Bank Type 2");//specify
        }catch (Exception e){
            System.out.println("Error processing command");
        }
    }
    private void dCommand(String[] token) {
        if(token.length!=5){
            System.out.println("Invalid command format.");
            return;
        }
        //You should reject the transaction if an invalid amount is
        //entered. Below are the sample transactions.
        //LOOK INTO THIS
        try{
            Date dateInput = createDate(token[4]);
            if (dateInput == null) return;
            Profile depositAccount = new Profile(token[2],token[3],dateInput);
            int key = bankType(token[1]);

            if(key==1)
                createChecking(depositAccount,Double.parseDouble(token[5]),DEPOSIT_INDICATION);
            else if(key==2)
                createCollegeChecking(depositAccount,Double.parseDouble(token[5]),null,DEPOSIT_INDICATION);
            else if(key ==3)
                createMoneyMarket(depositAccount,Double.parseDouble(token[5]),DEPOSIT_INDICATION);
            else if(key == 4)
                createSavings(depositAccount,Double.parseDouble(token[5]),0,DEPOSIT_INDICATION);
            else
                System.out.println("Not valid Bank Type 2");//specify
        }catch (Exception e){
            System.out.println("Error processing command");
        }
    }
    private void wCommand(String [] token){
        if(token.length!=5){
            System.out.println("Invalid command format.");
            return;
        }
        //You should reject the transaction if an invalid amount is
        //entered. Below are the sample transactions.
        //LOOK INTO THIS
        try{
            Date dateInput = createDate(token[4]);
            if (dateInput == null) return;
            Profile depositAccount = new Profile(token[2],token[3],dateInput);
            int key = bankType(token[1]);
            if(key==1)
                createChecking(depositAccount,Double.parseDouble(token[5]),WITHDRAW_INDICATION);
            else if(key==2)
                createCollegeChecking(depositAccount,Double.parseDouble(token[5]),null,WITHDRAW_INDICATION);
            else if(key ==3)
                createMoneyMarket(depositAccount,Double.parseDouble(token[5]),WITHDRAW_INDICATION);
            else if(key == 4)
                createSavings(depositAccount,Double.parseDouble(token[5]),0,WITHDRAW_INDICATION);
            else
                System.out.println("Not valid Bank Type 4");//specify
        }catch (Exception e){
            System.out.println("Error processing command");
        }
    }
        /**
         * Command for printing the event calendar as it currently is.
         */
//    private void pCommand(){
//        calendar.print();
//    }
//
//    /**
//     * Command for printing the event calendar, with the events sorted by dates and then timeslots.
//     */
//    private void peCommand(){
//        calendar.printByDate();
//    }
//
//    /**
//     * Command for printing the event calendar, with the events sorted by campus and then building/room.
//     */
//    private void pcCommand(){
//        calendar.printByCampus();
//    }
//
//    /**
//     * Command for printing the event calendar, with the events sorted by the department in the contact.
//     */
//    private void pdCommand(){
//        calendar.printByDepartment();
//    }

    public static void main(String[] args){
    }
}