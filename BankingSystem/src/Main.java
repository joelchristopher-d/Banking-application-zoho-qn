import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Actions Create = new Actions();
        while (true){
            System.out.println("Welcome to Dawson's Banking Application");
            System.out.println("1.CreateAccount \n" +
                                " 2.Login \n" +
                                "3.Exit");

            int input = scan.nextInt();
            if (input == 1){
                System.out.println("Enter your Name");
                String Name = scan.next();
                System.out.println("Enter a valid password 2upper 2lower 2number");
                String pwd = scan.next();
                Create.CreateAccount(Name,10000,pwd);
            } else if (input == 2) {
                System.out.println("Enter Your AccntNo");
                String AccNo = Create.Login(new Scanner(System.in));
                if(!Objects.equals(AccNo, "0")){
                    System.out.println("1.ATM withdrawal \n" +
                                        "2.Cash Deposit \n" +
                                        "3.Transfer Amount \n" +
                                        "4.Check Transaction History\n"+
                                        "5.Password change");
                    int in = scan.nextInt();
                    if(in==5){
                        Create.PasswordChange(new Scanner(System.in),AccNo);
                    }


                }

            }

            break;
        }

    }
}