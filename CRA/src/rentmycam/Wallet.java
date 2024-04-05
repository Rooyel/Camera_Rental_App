package rentmycam;

//Wallet class to manage user's wallet
class Wallet {
 private double balance;

 public Wallet() {
     this.balance = 1000.0;
 }

 public double getBalance() {
     return balance;
 }

 public void deposit(double amount) {
     balance += amount;
 }
}
