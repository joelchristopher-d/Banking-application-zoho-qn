import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static int custIdCounter = 1; // Static variable for unique customer IDs
    private String accNo;
    private String name;
    private double balance;
    private String encryptedPwd;
    private int transactionCount;
    private List<TransactionHistory> transactionHistory;
    private List<String> pwdHistory;
    private int custId;




    public Customer(String name){
        this.custId = custIdCounter++;
        this.accNo = custId + "00" +custId;
        this.name = name;
        this.transactionCount = 0;
        this.transactionHistory = new ArrayList<>();
        this.pwdHistory = new ArrayList<>();
//         custIdCounter++;

    }

    public int getCustId() {
        return custId;
    }

    public String getName() {
        return name;
    }

    public String getAccNo() {
        return accNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEncryptedPwd() {
        return encryptedPwd;
    }

    public void setEncryptedPwd(String encryptedPwd) {
        if(encryptedPwd.length()>=6){
            this.encryptedPwd = encryptedPwd;
        }
        else {
            System.out.println("Enter a password length of min 6");
        }
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void incrementTransactionCount() {
        this.transactionCount ++;
    }

    public List<TransactionHistory> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(TransactionHistory transaction) {
        this.transactionHistory.add(transaction);
    }

    public List<String> getPwdHistory() {
        return pwdHistory;
    }

    public void setPwdHistory(String pwd) {
        this.pwdHistory.add(pwd);
    }




}
