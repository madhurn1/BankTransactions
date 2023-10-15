package rubank;
/**
 An Account Database to be held at a given location.
 @author Dany Chucri, Madhur Nutulapati
 */
public class AccountDatabase {
    private Account [] accounts; //list of various types of accounts

    private int numAcct; //number of accounts in the array

    private static final int NOT_FOUND = -1;

    private int find(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return NOT_FOUND;
    } //search for an account in the array

    private void grow(){
        Account[] moreAccounts = new Account[accounts.length + 4];
        for (int i = 0; i < numAcct; i++) {
            moreAccounts[i] = accounts[i];
        }
        accounts = moreAccounts;
    } //increase the capacity by 4

    public boolean contains(Account account){
        for (int i = 0 ; i < numAcct; i++) {
            if (accounts[i].equals(account)) {
                return true;
            }
        }
        return false;
    } //overload if necessary

    public boolean open(Account account) {
        if (numAcct == 0) {
            accounts[0] = account;
            numAcct++;
            return true;
        }
        if (find(account) != NOT_FOUND) {
            return false;
        }
        if (numAcct == accounts.length)
            this.grow();
        accounts[numAcct] = account;
        numAcct++;
        return true;
    } //add a new account

    public boolean close(Account account) {
        int foundAccount = find(account);
        if (foundAccount != NOT_FOUND) {
            for(int j=foundAccount; j<numAcct-1;j++) {
                accounts[j] = accounts[j+1];
            }
            numAcct--;
            return true;
        }
        else{
            return false;
        }
    } //remove the given account

    public boolean withdraw(Account account) {
        int foundAccount = find(account);
        if (foundAccount != NOT_FOUND) {
        //...FINISH
        }
        return false;
    }//false if insufficient fund

    public void deposit(Account account) {
        int foundAccount = find(account);
        if (foundAccount != NOT_FOUND) {
            //...FINISH
        }
    }

    public void printSorted() {

    } //sort by account type and profile

    public void printFeesAndInterests() {

    } //calculate interests/fees

    public void printUpdatedBalances() {

    } //apply the interests/fees
}