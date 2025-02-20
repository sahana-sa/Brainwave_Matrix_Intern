import java.util.Scanner;

// BankAccount class to manage balance, deposits, and withdrawals
class BankAccount {
    private double balance;
    private final String pin;

    public BankAccount(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("\nMoney Deposited: $" + amount);
            System.out.println("Updated Balance: $" + balance);
        } else {
            System.out.println("\nInvalid deposit amount! Please enter a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("\nMoney Withdrawn: $" + amount);
            System.out.println("Updated Balance: $" + balance);
        } else {
            System.out.println("\nInsufficient funds or invalid withdrawal amount!");
        }
    }
}

// ATMInterface class to provide user interaction
class ATMInterface {
    private BankAccount account;
    private Scanner scanner;

    public ATMInterface(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        if (!authenticateUser()) {
            System.out.println("\nToo many incorrect attempts! Exiting ATM.");
            return;
        }

        int choice;
        do {
            showMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input! Please enter a number between 1 and 4.");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> checkBalance();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> System.out.println("\nExiting ATM. Thank you!");
                default -> System.out.println("\nInvalid choice! Please select a valid option.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private boolean authenticateUser() {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("\nEnter your 4-digit PIN: ");
            String enteredPin = scanner.next();
            if (account.authenticate(enteredPin)) {
                System.out.println("\nAuthentication successful! Access granted.");
                return true;
            }
            attempts--;
            System.out.println("\nIncorrect PIN. Attempts left: " + attempts);
        }
        return false;
    }

    private void showMenu() {
        System.out.println("\n******** WELCOME TO ATM ********");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Withdraw Cash");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void checkBalance() {
        System.out.println("\nCurrent Balance: $" + account.getBalance());
    }

    private void deposit() {
        System.out.print("\nEnter deposit amount: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("\nInvalid input! Please enter a valid amount.");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private void withdraw() {
        System.out.print("\nEnter withdrawal amount: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("\nInvalid input! Please enter a valid amount.");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}

// Main class to start the ATM application
public class atmtask1 {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000, "1111"); // Initial balance: $1000, PIN: 1111
        ATMInterface atm = new ATMInterface(userAccount);
        atm.start();
    }
}
