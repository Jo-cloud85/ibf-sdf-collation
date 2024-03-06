// 1. javac -d classes src/* 
// 2. java -cp classes App

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to SDF Day02 Workshop Test");

        BankAccount account1 = new BankAccount("James");
        FixedDepositAccount account2 = new FixedDepositAccount("John", 1000, 3, 6);

        System.out.println(account1.toString());
        
        account2.showBalance();
    }
}
