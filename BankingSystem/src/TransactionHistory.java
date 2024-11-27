public class TransactionHistory {
    private static int transIdCounter = 1;
    private int transId;
    private int custId;
    private String type;
    private double amount;
    private double balance;

    public TransactionHistory(int custId, String type, double amount, double balance) {
        this.transId = transIdCounter++;
        this.custId = custId;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public int getTransId() {
        return transId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }
}
