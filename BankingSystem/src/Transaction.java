import java.util.Scanner;

public class Transaction extends Actions{

    public void ATMWithdraw(Scanner scanner,String Accno){
        Customer customer = customerDetails.get(Accno);
        double amount = scanner.nextDouble();
        double currBal = customer.getBalance();
        if(currBal<=1000){
            System.out.println("Low Balance!! Account Balance should not below 1000 rupees");
            return;
        }
        if(currBal-amount<1000){
            System.out.println("Account Balance should not go below 1000 rupees");
            return;
        }
        double newBal = currBal - amount;
        customer.setBalance(newBal);
        TransactionHistory transaction = new TransactionHistory(customer.getCustId(), "Opening", amount, newBal);
        customer.setTransactionHistory(transaction);
    }

    public void Cashdeposit(){

    }
}
