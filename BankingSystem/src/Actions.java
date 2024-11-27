import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

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
        TransactionHistory transaction = new TransactionHistory(customer.getCustId(), "Opening", Balance, Balance);
        customer.setTransactionHistory(transaction);
        System.out.println("Your Account has been successfully created");
        System.out.printf("ID: %d,Name: %s, AccNo: %s, Encrypyted Pwd: %s",customer.getCustId(),customer.getName(),customer.getAccNo(),customer.getEncryptedPwd());
        customerDetails.put(customer.getAccNo(),customer);

    }

    public String Login(Scanner scanner){
        System.out.print("Enter Customer ID: ");
        String Accno = scanner.next();
        if(!customerDetails.containsKey(Accno)){
            System.out.println("Entered Accno doesn't Exist");
            return "0";
        }
        System.out.print("Enter password: ");
        String pwd = EncryptPassword(scanner.next());
        if(pwd.equals(customerDetails.get(Accno).getEncryptedPwd())){
            System.out.println("You're LoggedIn");
            return Accno;
        }
        return  "0";
    }

    public void PasswordChange(Scanner scanner,String AccNo){
        System.out.print("Enter the new Password: ");
        String Pwd = scanner.next();
        String Encrypted = EncryptPassword(Pwd);
        if(!Objects.equals(Encrypted, "")){
            System.out.print("Re Enter the new Password: ");
            String RePwd = scanner.next();
            if(!Pwd.equals(RePwd)){
                System.out.println("Re entered Password doesn't matches");
                return;
            }
            customerDetails.get(AccNo).setEncryptedPwd(Encrypted);
            customerDetails.get(AccNo).setPwdHistory(Encrypted);
            System.out.println("Password Changed Successfully!");
        }

    }

}
