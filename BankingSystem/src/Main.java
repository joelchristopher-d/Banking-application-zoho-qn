import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Transaction Create = new Transaction();
        Create.CreateAccount("Joel",10000,"ABab12");
        Create.CreateAccount("Leo",20000,"AB123ab");
        Create.CreateAccount("Doggy",40000,"JoE1234");
        while (true){
            System.out.println("\n---------------------Welcome to Dawson's Banking Application---------------------\n");
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
//                System.out.println("Enter Your AccntNo");
                String AccNo = Create.Login(new Scanner(System.in));
                if(!Objects.equals(AccNo, "0")){
                    System.out.println("1.ATM withdrawal \n" +
                                        "2.Cash Deposit \n" +
                                        "3.Transfer Amount \n" +
                                        "4.Check Transaction History\n"+
                                        "5.Password change");
                    int in = scan.nextInt();
                    if(in==1){
                        Create.ATMWithdraw(new Scanner(System.in),AccNo);
                    } else if (in==2) {
                        Create.Cashdeposit(new Scanner(System.in),AccNo);
                    } else if (in==3) {
                        Create.Transfer(new Scanner(System.in),AccNo);
                    }
                    else if (in == 4) {
                        Create.viewTransactionHistory(new Scanner(System.in),AccNo);
                    }
                    else if(in==5){
                        Create.PasswordChange(new Scanner(System.in),AccNo);
                    } else if (in == 6) {
                        Create.viewPwdHistory(new Scanner(System.in),AccNo);
                    }


                }

            }

        }

    }
}
