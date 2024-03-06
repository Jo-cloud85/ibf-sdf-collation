public class FixedDepositAccount extends BankAccount {

    private double interest;
    private double duration;

    public FixedDepositAccount(String holderName, double balance) {
        super(holderName, balance);
    }

    public FixedDepositAccount(String holderName, double balance, double interest) {
        super(holderName, balance);
        this.interest = interest;
    }

    public FixedDepositAccount(String holderName, double balance, double interest, double duration) {
        super(holderName, balance);
        this.interest = interest;
        this.duration = duration;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public void deposit(double amount) {
        System.err.println("Operations not allowed.");
    }

    @Override
    public void withdraw(double amount) {
        System.err.println("Operations not allowed.");
    }

    public void showBalance() {
        System.out.println("Account No: " + getAccountNo());
        System.out.println("Account Holder: " + getHolderName());
        System.out.println("Computed Balance with interest: " + this.getBalance() * (100 + this.interest)/100);
    }
}
