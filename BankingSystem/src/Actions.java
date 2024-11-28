import java.util.*;

public class Actions extends Helpers{
    public static Map<String, Customer> customerDetails = new HashMap<>();

    public void CreateAccount(String Name, double Balance,String Password){
//        Helpers helper =  new Helpers();

        String Pwd = EncryptPassword(Password);
        if(Pwd==""){
            return;
        }
        Customer customer = new Customer(Name);
        customer.setEncryptedPwd(Pwd);
        customer.setBalance(Balance);
        customer.incrementTransactionCount();
        customer.setPwdHistory(Password);
        customer.incrementTransactionCount();
        TransactionHistory transaction = new TransactionHistory(customer.getCustId(), "Opening", Balance, Balance);
        customer.setTransactionHistory(transaction);
        System.out.println("\nYour Account has been successfully created\n");
        System.out.printf("\nID: %d,Name: %s, AccNo: %s, Encrypyted Pwd: %s\n",customer.getCustId(),customer.getName(),customer.getAccNo(),customer.getEncryptedPwd());
        customerDetails.put(customer.getAccNo(),customer);

    }

    public String Login(Scanner scanner){
        System.out.println("\nEnter Your AccNo\n");
        String Accno = scanner.next();
        if(!customerDetails.containsKey(Accno)){
            System.out.println("\nEntered Accno doesn't Exist\n");
            return "0";
        }
        System.out.print("\nEnter password: \n");
        String pwd = EncryptPassword(scanner.next());
        if(pwd.equals(customerDetails.get(Accno).getEncryptedPwd())){
            System.out.println("\nYou're LoggedIn\n");
            return Accno;
        }
        return  "0";
    }

    public void PasswordChange(Scanner scanner,String AccNo){
        System.out.print("\nEnter the new Password: \n");
        String Pwd = scanner.next();
        String Encrypted = EncryptPassword(Pwd);
        if(!Objects.equals(Encrypted, "")){
            System.out.print("\nRe Enter the new Password: \n");
            String RePwd = scanner.next();
            if(!Pwd.equals(RePwd)){
                System.out.println("\nRe entered Password doesn't matches\n");
                return;
            }
            List<String> pwdhis = customerDetails.get(AccNo).getPwdHistory();
            int len=0;
            if(pwdhis.size()>=3){
                len = pwdhis.size()-3;
            }

            for (int i = pwdhis.size()-1; i >=len ; i--) {
                if (pwdhis.get(i).equals(Pwd)){
                    System.out.println("Should not set recent 3 pwd as new pwd. Try other!");
                    return;
                }
            }
            customerDetails.get(AccNo).setEncryptedPwd(Encrypted);
            customerDetails.get(AccNo).setPwdHistory(Pwd);
            System.out.println("\nPassword Changed Successfully!\n");
        }

    }

    public void viewTransactionHistory(Scanner scanner,String AccNo){
        List<TransactionHistory> T_history = customerDetails.get(AccNo).getTransactionHistory();
        System.out.printf("\nTransaction Details of Name: %s, AccNo: %s\n",customerDetails.get(AccNo).getName(),AccNo);
        for (TransactionHistory Data:T_history){
            System.out.printf("\nTransactionId: %d,\n" +
                              "Type: %s,\n" +
                              "Amount: %f\n" +
                              "Balance: %f\n",Data.getTransId(),Data.getType(),Data.getAmount(),Data.getBalance());
        }
    }


    public void viewPwdHistory(Scanner scanner,String AccNo) {
        int counter = 1;
        for (String pwd:customerDetails.get(AccNo).getPwdHistory()){
            System.out.printf("%d. %s",counter++,pwd);
        }
    }
}
