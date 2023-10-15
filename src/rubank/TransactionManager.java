package rubank;
import java.util.Scanner;

/**
 * User interface to process command line input for the Event Calendar.
 * Can process a single or multiple lines at once.
 * @author Dany Chucri, Madhur Nutulapati
 */
public class TransactionManager {
    private AccountDatabase accountDatabase;

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
                    rCommand(token);
                    break;
                case "D":
                    pCommand();
                    break;
                case "W":
                    peCommand();
                    break;
                case "P":
                    pcCommand();
                    break;
                case "PI":
                    pdCommand();
                    break;
                case "UB":
                    pdCommand();
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
            if(key==1){
                Checking addAccount = new Checking(addProfile,Double.parseDouble(token[5]));
            }
            else if(key==2){
                Campus
                CollegeChecking addAccount = new CollegeChecking(addProfile,Double.parseDouble(token[5]));
            }
            else if(key ==3){
                MoneyMarket addAccount = new MoneyMarket(addProfile,Double.parseDouble(token[5]));
            }
            else if(key ==4){
                Savings addAccount = new Savings(addProfile,Double.parseDouble(token[5]));
            }
            else{
                System.out.println("Error");
            }

            if(accountDatabase.add(addAccount)){
                System.out.println("Account opened");
            }else{
                System.out.println("The event is already on the calendar.");
            }
        }catch (Exception e){
            System.out.println("Error with adding an accoutn.");
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
        if(addDate.isValid() == Date.INVALID_DATE){
            System.out.println(date + ": Invalid calendar date!");
            return null;
        } else if (addDate.isValid() == Date.PAST_DATE) {
            System.out.println(date + ": Event date must be a future date!");
            return null;
        } else if (addDate.isValid() == Date.TOO_DISTANT_DATE) {
            System.out.println(date + ": Event date must be within 6 months!");
            return null;
        }
        return addDate;
    }

    /**
     * Command for removing an Event from the Event Calendar.
     * @param token An array of tokens from the command-line arguments.
     */
    private void rCommand(String[] token){
        if(token.length!=4){
            System.out.println("Invalid command format.");
            return;
        }
        try{
            Date dateInput = createDate(token[1]);
            if (dateInput == null) return;
            Timeslot startTime = createTimeSlot(token[2]);
            Location locationInput = createLocation(token[3]);
            Event eventToRemove = new Event(dateInput,startTime,locationInput,null,0);
            if(calendar.remove(eventToRemove)){
                System.out.println("Event has been removed from the calendar!");
            } else{
                System.out.println("Cannot remove; event is not in the calendar!");
            }
        }catch (Exception e){
            System.out.println("Error processing command");
        }
    }

    /**
     * Command for printing the event calendar as it currently is.
     */
    private void pCommand(){
        calendar.print();
    }

    /**
     * Command for printing the event calendar, with the events sorted by dates and then timeslots.
     */
    private void peCommand(){
        calendar.printByDate();
    }

    /**
     * Command for printing the event calendar, with the events sorted by campus and then building/room.
     */
    private void pcCommand(){
        calendar.printByCampus();
    }

    /**
     * Command for printing the event calendar, with the events sorted by the department in the contact.
     */
    private void pdCommand(){
        calendar.printByDepartment();
    }

    public static void main(String[] args){
    }
}