import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BankAccount {
    private final String accountNo;
    private final String holderName;
    private double balance;
    private Boolean isActive = true;
    private Date accountStart;
    private Date accountEnd;
    private List<String> transactions;

    public BankAccount(String holderName) {
        this(holderName, 0.0);
    }

    public BankAccount(String holderName, double balance) {
        this.accountNo = generateRandomAccountNo();
        this.holderName = holderName;
        this.balance = balance;

        if (transactions == null) {
            transactions = new ArrayList<>();
        }
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getAccountStart() {
        return accountStart;
    }

    public void setAccountStart(Date accountStart) {
        this.accountStart = accountStart;
    }

    public Date getAccountEnd() {
        return accountEnd;
    }

    public void setAccountEnd(Date accountEnd) {
        this.accountEnd = accountEnd;
    }

    private String generateRandomAccountNo() {
        int startAsciiNo = 66;
        int endAsciiNo = 90;
        int accountNoLength = 10;

        Random rand = new Random();
        String accountNo = "";
        for(int i =0; i < accountNoLength; i++) {
            int randomValue = startAsciiNo + (int)(rand.nextFloat() * (endAsciiNo - startAsciiNo + 1)); 
            accountNo = accountNo + (char) randomValue;
        }
        return accountNo;
    }

    public void deposit(double amount) {
        if (isActive) {
            if (amount > 0.0) {
                this.balance += amount;

                LocalDate currentDate = LocalDate.now();
                String log = "deposit " + String.valueOf(amount) + " on " + currentDate.toString();
                transactions.add(log);
            } else {
                throw new IllegalArgumentException("You cannot make negative deposit.");
            }
        } else {
            throw new IllegalArgumentException("You cannot deposit to an inactive account.");
        }
    }

    public void withdraw(double amount) {
        if (isActive) {
            if (balance >= amount) {
                this.balance -= amount;

                LocalDate currentDate = LocalDate.now();
                String log = "withdraw " + String.valueOf(amount) + " on " + currentDate.toString();
                transactions.add(log);
            } else {
                throw new IllegalArgumentException("You cannot withdraw more than what you have.");
            }
        } else {
            throw new IllegalArgumentException("You cannot withdraw from an inactive account.");
        }
    }

    @Override
    public String toString() {
        return "BankAccount [accountNo=" + accountNo + ", holderName=" + holderName + ", balance=" + balance + ", isActive=" + isActive + ", accountStart=" + accountStart + ", accountEnd=" + accountEnd + "]";
    }

}