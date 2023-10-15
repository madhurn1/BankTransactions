package rubank;

import java.text.DecimalFormat;

/**
 An Account Database to be held at a given location.
 @author Dany Chucri, Madhur Nutulapati
 */
public class AccountDatabase {
    private Account [] accounts; // list of various types of accounts

    private int numAcct; // number of accounts in the array

    private static final int NOT_FOUND = -1;

    private static final int NUM_MONTHS = 12;

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
            double newBalance = accounts[foundAccount].getBalance() - account.getBalance();
            if (newBalance < 0) return false;
            accounts[foundAccount].setBalance(newBalance);
            if (accounts[foundAccount] instanceof MoneyMarket acc) {
                if (acc.getWithdrawals() >= 3) {
                    double mmBalance = acc.getBalance() - 10;
                    if (mmBalance < 0) {
                        acc.setBalance(acc.getBalance() + account.getBalance());
                        return false;
                    }
                    else {
                        acc.setBalance(mmBalance);
                    }
                }
                if (newBalance < 2000 && (acc.getLoyalty())) {
                    acc.setLoyalty(false);
                }
                acc.incWithdrawal();
            }
            return true;
        }
        return false;
    }//false if insufficient fund

    public void deposit(Account account) {
        int foundAccount = find(account);
        if (foundAccount != NOT_FOUND) {
            double newBalance = accounts[foundAccount].getBalance() + account.getBalance();
            accounts[foundAccount].setBalance(newBalance);
            if (accounts[foundAccount] instanceof MoneyMarket acc) {
                if (newBalance >= 2000 && !(acc.getLoyalty())) {
                    acc.setLoyalty(true);
                }
            }
        }
    }


    private void selectionSortAccounts(){
        for (int i = 0; i < numAcct - 1; i++){
            int min = i;
            for (int j = i + 1; j < numAcct; j++){
                if (accounts[j].compareTo(accounts[min]) == 0) {
                    if (accounts[j].getHolder().compareTo(accounts[min].getHolder()) < 0) {
                        min = j;
                    }
                }
                else if (accounts[j].compareTo(accounts[min]) < 0){
                    min = j;
                }
            }
            Account minDateEvent = accounts[min];
            accounts[min] = accounts[i];
            accounts[i] = minDateEvent;
        }
    }

    public void printSorted() {
        selectionSortAccounts();
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println("*Accounts sorted by account type and profile.");
        for(int i = 0; i < numAcct; i++){
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list.");


    } // sort by account type and profile, then print

    private double calcMonthlyFee(Account account) {
        double fee = 0;
        if (account instanceof CollegeChecking acc) {
            fee = 0;
        }
        else if (account instanceof Checking acc) {
            if (acc.getBalance() >= 1000)
                fee = 0;
            else fee = acc.getMonthlyFee();
        }
        else if (account instanceof MoneyMarket acc) {
            if (acc.getBalance() >= 2000) {
                fee = 0;
            }
            else fee = acc.getMonthlyFee();
        }
        else if (account instanceof Savings acc) {
            if (acc.getBalance() >= 500) {
                fee = 0;
            }
            else fee = acc.getMonthlyFee();
        }

        return fee;
    }

    private double calcMonthlyInterest(Account account) {
        double interest = 0;
        if (account instanceof Checking acc) {
            interest = acc.getBalance() * (acc.getInterestRate() / NUM_MONTHS);
        }
        else if (account instanceof MoneyMarket acc) {
            if (acc.getLoyalty()) {
                interest = acc.getBalance() * (acc.getLoyaltyIntRate() / NUM_MONTHS);
            }
            else interest = acc.getBalance() * (acc.getInterestRate() / NUM_MONTHS);
        }
        else if (account instanceof Savings acc) {
            if (acc.getLoyalty()) {
                interest = acc.getBalance() * (acc.getLoyaltyIntRate() / NUM_MONTHS);
            }
            else interest = acc.getBalance() * (acc.getInterestRate() / NUM_MONTHS);
        }

        return interest;
    }

    public void printFeesAndInterests() {
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println("*list of accounts with fee and monthly interest");
        for (int i = 0; i < numAcct; i++)
        {
            double fee = calcMonthlyFee(accounts[i]);
            double interest = calcMonthlyInterest(accounts[i]);
            DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            String feeString = "$" + formatter.format(fee);
            String interestString = "$" + formatter.format(interest);
            System.out.println(accounts[i] + "::fee " + feeString + "::monthly interest " + interestString);
        }
        System.out.println("*end of list.");
    } //calculate interests/fees, then print

    public void printUpdatedBalances() {
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println("*list of accounts with fees and interests applied.");
        for (int i = 0; i < numAcct; i++)
        {
            double fee = calcMonthlyFee(accounts[i]);
            double interest = calcMonthlyInterest(accounts[i]);
            accounts[i].setBalance(accounts[i].getBalance() - fee + interest);
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list.");
    } //apply the interests/fees, then print
}