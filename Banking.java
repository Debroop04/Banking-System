import java.util.Scanner;

 class Account {
    private double balance;
    private double loanamm = 0;

 Account(double initialBalance) {
        this.balance = initialBalance;
    }
    
void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit! Deposit must be more than 0");
        }
    }
    
 void withdraw(double amount,Scanner scan) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
            System.out.print("Would you like to take a loan? (y/n): ");
            String resp = scan.next().toLowerCase();
            if (resp.equals("y")) {
                double min = amount - balance;
                takeLoan(min);
                balance -= amount;
                System.out.println("Withdrawn: $" + amount + " (including loan)");
            } else {
                System.out.println("Loan cancelled.");
            }
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }
    
        void takeLoan(double amount) {
        double interestRate = 0.10; // 10% interest
        double totalRepayable = amount * (1 + interestRate);
        loanamm += totalRepayable;
        balance += amount;
        System.out.println("Loan approved: $" + amount);
        System.out.println("Interest (10%): $" + (amount * interestRate));
        System.out.println("Total to be repaid later: $" + totalRepayable);
    }
    
       void repay() {
        if (loanamm == 0) {
            System.out.println("You have no owed loan.");
        } else if (balance >= loanamm) {
            balance -= loanamm;
            System.out.println("Loan of $" + loanamm + " repaid successfully.");
            loanamm = 0;
        } else {
            System.out.println("Insufficient balance to repay the loan.");
            System.out.println("You need: $" + (loanamm-balance) + " more to repay the loan");
        }
    }
void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }
}


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to your bank account!\nPlease enter your password before proceeding");
        final String Passw = "Deb2025"; // Set the correct password
        final int MAX_ATTEMPTS = 5;
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        Scanner scan = new Scanner(System.in);

        Account account = new Account(0);
        int choice;
        
      while (attempts < MAX_ATTEMPTS) {
          System.out.print("\nEnter your password: ");
          String in = scanner.nextLine(); 
        // Check if the entered password matches the correct one
        if (in.equals(Passw)) {
            System.out.println("Access Granted!You may proceed");
          do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("Enter 1. Deposit");
            System.out.println("Enter 2. Withdraw");
            System.out.println("Enter 3. Check Balance");
            System.out.println("Enter 4. Repay Loan");
            System.out.println("Enter 5. Exit Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount,scan);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    account.repay();
                    break;
                case 5:
                    System.out.println("Exiting Menu\nThank you for using the bank system!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
          } while (choice != 5);

            
        } else {
            attempts++;
                if (attempts < MAX_ATTEMPTS) {
                    System.out.println("Incorrect password. Attempts left: " + (MAX_ATTEMPTS - attempts));
                }
        }
      }
        System.out.println("Access Denied. Too many failed attempts.");
        scanner.close(); // Close scanner to prevent resource leak
    }
}