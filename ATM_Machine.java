import javax.swing.*;

class ATM {
    private BankAccount acc;
    private int pin;

    public ATM(BankAccount ac, int pin) {
        this.acc = ac;
        this.pin = pin;
    }

    public void menu() {
        String[] options = {"Transactions History", "Withdraw", "Deposit", "Transfer", "Quit"};
        int choice = JOptionPane.showOptionDialog(null, "ATM Machine Interface", "ATM", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "Transactions History:\n\n" + acc.getTransactionHistory());
                break;
            case 1:
                withdraw();
                break;
            case 2:
                deposit();
                break;
            case 3:
                transfer();
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Thank you for using the ATM.");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "You have entered an invalid option.");
        }
    }

    private void withdraw() {
        String amountString = JOptionPane.showInputDialog(null, "Enter the amount to withdraw:");
        if (amountString != null && !amountString.isEmpty()) {
            double amount = Double.parseDouble(amountString);
            acc.withdraw(amount);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount entered.");
        }
    }

    private void deposit() {
        String amountString = JOptionPane.showInputDialog(null, "Enter the amount to deposit:");
        if (amountString != null && !amountString.isEmpty()) {
            double amount = Double.parseDouble(amountString);
            acc.deposit(amount);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount entered.");
        }
    }

    private void transfer() {
        String amountString = JOptionPane.showInputDialog(null, "Enter the amount to transfer:");
        if (amountString != null && !amountString.isEmpty()) {
            double amount = Double.parseDouble(amountString);
            String recipient = JOptionPane.showInputDialog(null, "Enter recipient's account number:");
            if (recipient != null && !recipient.isEmpty()) {
                acc.transfer(amount, recipient);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid recipient account number.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount entered.");
        }
    }

    public boolean verifyPin(String enteredPin) {
        return this.pin == Integer.parseInt(enteredPin);
    }

    public void bankProcess() {
        String enteredPin = JOptionPane.showInputDialog(null, "Enter your pin:");
        if (enteredPin != null && !enteredPin.isEmpty() && verifyPin(enteredPin)) {
            int opt;
            do {
                menu();
                opt = JOptionPane.showConfirmDialog(null, "Do you want to perform another transaction?",
                        "ATM", JOptionPane.YES_NO_OPTION);
            } while (opt == JOptionPane.YES_OPTION);
            JOptionPane.showMessageDialog(null, "Thank you for using the ATM.");
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect pin.");
        }
    }
}

class BankAccount {
    private double balance;
    private StringBuilder transactionHistory;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new StringBuilder();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.append("Deposited: \u20B9").append(amount).append("\n");
            JOptionPane.showMessageDialog(null, "The amount \u20B9" + amount + " is deposited successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "You cannot deposit an amount less than or equal to 0.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Withdrawn: \u20B9").append(amount).append("\n");
            JOptionPane.showMessageDialog(null, "The amount \u20B9" + amount + " is withdrawn successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "You do not have sufficient balance to withdraw or invalid amount.");
        }
    }

    public void transfer(double amount, String recipient) {
        // Simplified transfer implementation, you may need to enhance this based on your needs
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Transferred: \u20B9").append(amount).append(" to account ").append(recipient).append("\n");
            JOptionPane.showMessageDialog(null, "The amount \u20B9" + amount + " is transferred successfully to account " + recipient + ".");
        } else {
            JOptionPane.showMessageDialog(null, "Transfer failed. Insufficient balance or invalid amount.");
        }
    }

    public String getTransactionHistory() {
        return transactionHistory.toString();
    }
}

public class ATM_Machine {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        int actualPin = 1234;
        ATM atm = new ATM(userAccount, actualPin);
        atm.bankProcess();
    }
}
