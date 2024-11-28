import java.util.Scanner;

public class Transaction extends Actions{

    public void ATMWithdraw(Scanner scanner,String Accno){
        Customer customer = customerDetails.get(Accno);
        System.out.println("\nEnter the amount to withdraw\n");
        double amount = scanner.nextDouble();
        double currBal = customer.getBalance();
        if(currBal<=1000){
            System.out.println("\nLow Balance!! Account Balance should not below 1000 rupees\n");
            return;
        }
        if(currBal-amount<1000){
            System.out.println("\nAccount Balance should not go below 1000 rupees\n");
            return;
        }
        double newBal = currBal - amount;
        customer.setBalance(newBal);
        TransactionHistory transaction = new TransactionHistory(customer.getCustId(), "ATM Withdrawal", amount, newBal);
        customer.setTransactionHistory(transaction);
        customer.incrementTransactionCount();
        if(customer.getTransactionCount()%10==0){
            customer.setBalance(customer.getBalance()-100);
            System.out.println("Rs.100 has been deducted as a service fee.\nIt will be deducted for every 10 transactions ");
        }
        System.out.printf("\nINR: %f,Amount has been debited from your account\n",amount);
    }

    public void Cashdeposit(Scanner scanner,String Accno){
        Customer customer = customerDetails.get(Accno);
        System.out.println("\nEnter the amount to Deposit\n");
        double amount = scanner.nextDouble();
        double currBal = customer.getBalance();
        if(amount<=0){
            System.out.println("\nEnter Valid Amount\n");
            return;
        }
        double newBal = currBal + amount;
        customer.setBalance(newBal);
        TransactionHistory transaction = new TransactionHistory(customer.getCustId(), "Cash Deposit", amount, newBal);
        customer.setTransactionHistory(transaction);
        customer.incrementTransactionCount();
        if(customer.getTransactionCount()%10==0){
            customer.setBalance(customer.getBalance()-100);
            System.out.println("Rs.100 has been deducted as a service fee.\n It wll deducted for every 10 transactions ");
        }
        System.out.printf("\nINR: %f,Amount has been deposited to your account\n",amount);


    }

    public void Transfer(Scanner scanner,String Accno){
        Customer customer = customerDetails.get(Accno);
        System.out.println("\nEnter the AccNo to be transferred\n");
        String AccTo = scanner.next();
        if(!customerDetails.containsKey(AccTo)){
            System.out.printf("\nNo Account with AccNo:,%s\n",AccTo);
            return;
        }
        Customer customer1 = customerDetails.get(AccTo);
        System.out.println("\nEnter the Amount to be transferred\n");
        double amount = scanner.nextDouble();
        double currBal = customer.getBalance();
        if(currBal<=1000){
            System.out.println("\nLow Balance!! Account Balance should not below 1000 rupees\n");
            return;
        }
        if(currBal-amount<1000){
            System.out.println("\nAccount Balance should not go below 1000 rupees\n");
            return;
        }
        double newBal = currBal - amount;
        customer.setBalance(newBal);
        TransactionHistory transaction1 = new TransactionHistory(customer.getCustId(), "Transferred To: "+AccTo, amount, newBal);
        customer.setTransactionHistory(transaction1);
        customer.incrementTransactionCount();
        if(customer.getTransactionCount()%10==0){
            customer.setBalance(customer.getBalance()-100);
            System.out.println("Rs.100 has been deducted as a service fee.\n It wll deducted for every 10 transactions ");
        }
        double newBal1 = customer1.getBalance() + amount;
        TransactionHistory transaction2 = new TransactionHistory(customer1.getCustId(), "Transferred From: "+Accno, amount, newBal1);
        customer1.setTransactionHistory(transaction2);
        customer1.incrementTransactionCount();
        if(customer1.getTransactionCount()%10==0){
            customer1.setBalance(customer.getBalance()-100);
        }
        System.out.printf("\nSuccessfully %f amount transferred from %s to %s\n",amount,Accno,AccTo);
    }


}
