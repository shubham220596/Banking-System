import java.util.ArrayList;
import java.util.Scanner;

public class AccountDriver {

    // Entry point of program
    public static void main(String [] args) {

        Scanner keyboard = new Scanner(System.in);
        ArrayList<Account>accounts = new ArrayList<>();
        int choice;
        do {
            choice = menu(keyboard);
            System.out.println();
            
            if(choice == 1) {
                 accounts.add(createAccount(accounts,keyboard));
            } else if(choice == 2) {
                doDeposit(accounts,keyboard);
            } else if(choice == 3) {
                doWithdraw(accounts,keyboard);
            } else if(choice == 4) {
                applyInterest(accounts,keyboard);
            }else if(choice==5){
                checkBalance(accounts,keyboard);
            }else if(choice==6){
                sendMoney(accounts,keyboard);
            }
            else {
                System.out.println("GoodBye!");
            }
            System.out.println();
        } while(choice != 7);
    }

    public static void checkBalance(ArrayList<Account> accounts, Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts,accountNumber);

        if(index >= 0) {
            // Amount
            System.out.print(" Your Current Balance is : "+ accounts.get(index).getBalance());
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }

    /**
     * Account choice
     */
    public static int accountMenu(Scanner keyboard) {
        System.out.println("Select Account Type");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");

        int choice;
        do {
            System.out.print("Enter choice: ");
            choice = keyboard.nextInt();
        }while(choice < 1 || choice > 2);
        
        return choice;
    } 

    public static int searchAccount(ArrayList<Account> accounts, int accountNumber) {

        for(int i=0; i<accounts.size(); i++) {
            if(accounts.get(i).getAccountNumber() == accountNumber) {
                return i;
            }
        }

        return -1; 
    }

    /**
     * Function to perform Deposit on a selected account
     */
    public static void doDeposit(ArrayList<Account> accounts,Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts,accountNumber);

        if(index >= 0) {
            // Amount
            System.out.print("Please enter Deposit Amount: ");
            double amount = keyboard.nextDouble();

            accounts.get(index).deposit(amount);
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }

    public static void doWithdraw(ArrayList<Account> accounts,Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts,accountNumber);

        if(index >= 0) {
            // Amount
            System.out.print("Please enter Withdraw Amount: ");
            double amount = keyboard.nextDouble();

            accounts.get(index).withdraw(amount);
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }
    public static void sendMoney(ArrayList<Account> accounts,Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter your account number: ");
        int senderAccount = keyboard.nextInt();
        System.out.println("\nPlease enter receiver account number: ");
        int receiverAccount = keyboard.nextInt();
        // search for your account
        int index1 = searchAccount(accounts,senderAccount);
        //search for receiver account
        int index2 = searchAccount(accounts,receiverAccount);

        if(index1>= 0 && index2>=0) {
            // Amount
            System.out.print("Please enter amount to be send: ");
            double amount = keyboard.nextDouble();
           if(accounts.get(index1).balance<amount){
               System.out.println("Insufficient balance in your account");
           }
           else{
               accounts.get(index1).balance = accounts.get(index1).balance-amount;
               accounts.get(index2).balance = accounts.get(index2).balance+amount;
               System.out.println("Transferred Successfully.....");
               System.out.println("Your remaining balance is " + accounts.get(index1).balance);
           }
        } else {
            System.out.println("Incorrect details");
        }
    }

    public static void applyInterest(ArrayList<Account> accounts,Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts,accountNumber);

        if(index >= 0) {
            
            // must be instance of savings account
            if(accounts.get(index) instanceof SavingsAccount) {
                ((SavingsAccount) accounts.get(index)).applyInterest();
            }
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }

    /**
     * Function to create a new Account
     */
    public static Account createAccount(ArrayList<Account> accounts, Scanner keyboard)  {

        Account account = null; 
        int choice = accountMenu(keyboard);
        int accountNumber;
        System.out.print("Enter account number: ");
        accountNumber = keyboard.nextInt();
        int index = searchAccount(accounts,accountNumber);
        if(index==-1) {
            if (choice == 1) { // checking account
                System.out.print("Enter Transaction Fee: ");
                double fee = keyboard.nextDouble();
                account = new CheckingAccount(accountNumber, fee);
                System.out.println("Checking account created successfully");
            } else { // Savings account

                System.out.print("Please enter Interest Rate: ");
                double ir = keyboard.nextDouble();
                account = new SavingsAccount(accountNumber, ir);
                System.out.println("Savings account created successfully");
            }
        }
        else{
            System.out.println("account already exists ");
        }
        return account;
    }

    /**
     * Menu to display options and get the Login's selection
     */
    public static int menu(Scanner keyboard) {
        System.out.println("Bank Account Menu");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Apply Interest");
        System.out.println("5. Check Balance");
        System.out.println("6. sendMoney");
        System.out.println("7. Quit");
        int choice;

        do {
            System.out.print("Enter choice: ");
            choice = keyboard.nextInt();
        } while(choice < 1 || choice > 7);

        return choice;
    }    
}
