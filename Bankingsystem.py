class Customer:
    CustId=1    
    def __init__(self,Name,Encryptedpwd,Balance=10000):
        self.Accno = str(Customer.CustId)+"00"+str(Customer.CustId)
        self.Name = Name
        self.Balance = Balance
        self.Encryptedpwd = Encryptedpwd
        self.transactionCount=0
        self.transactionhistory = []
        self.pwdhistory = []
        self.CustId=Customer.CustId
        Customer.CustId+=1
        
        
        
class TransactionHistory:
    TransId=1
    def __init__(self,CustId,Type,Amount,Balance):
        self.CustId = CustId
        self.Type = Type
        self.Amount = Amount
        self.Balance=Balance
        self.TransId=TransactionHistory.TransId
        TransactionHistory.TransId+=1
        
        
class start:
    CustomerDetailsdetails={}
    def CreateAccounts(self,customerdata,balance=10000):
        customerdata.Balance=balance
        t = TransactionHistory(customerdata.CustId,"Opening",balance,balance)
        customerdata.transactionhistory.append(t)
        start.CustomerDetailsdetails[customerdata.CustId] = customerdata
        print(f"Account has been created\nCustomerId: {customerdata.CustId}\nAccount No.: {customerdata.Accno}")
        for i in customerdata.transactionhistory:
            print(i.Type,i.TransId,i.Balance)
        print()
        
    def login(self):
        CustId = int(input("enter id"))
        if CustId not in start.CustomerDetailsdetails:
            print("no customers available")
            return 0
        
        pwd = self.PasswordEncrypt(str(input("enter pwd")))
        print(pwd)
        if pwd == start.CustomerDetailsdetails[CustId].Encryptedpwd:
            print("You're Logged in")
        else:
            print("wrong Password")
            return 0
        
        
        return CustId
        
        
    def showcustomers(self):
        for i in start.CustomerDetailsdetails:
            print("Acc.No",start.CustomerDetailsdetails[i].Accno)
            print("Name",start.CustomerDetailsdetails[i].Name)
            print("balance",start.CustomerDetailsdetails[i].Balance)
            print()


                
        
    def PasswordEncrypt(self,password):
        encryptedpwd=""
        for i in password:
            if i=="9":
                encryptedpwd+="0"
            elif i == "Z":
                encryptedpwd+="A"
            elif i =="z":
                encryptedpwd+="a"
            else:
                encryptedpwd+=(chr(ord(i)+1))
        return encryptedpwd
    

    
class transaction:
    def atm(self,CustId):
        withdrawal = float(input("enter withdrawal amount"))
        if withdrawal <= 0:
            print("enter valid amount ")
            return
        currbal = start.CustomerDetailsdetails[CustId].Balance
#         print(currbal)
        if (currbal >= 1000 and currbal-withdrawal>1000):
            if (currbal >= withdrawal):
                start.CustomerDetailsdetails[CustId].Balance = currbal - withdrawal
                t = TransactionHistory(CustId,"ATMWithdra",withdrawal,start.CustomerDetailsdetails[CustId].Balance)
                start.CustomerDetailsdetails[CustId].transactionhistory.append(t)
            else:
                print("low Balance")
                
        else:
            print("Minimum balance is 1000")
    
    def Deposit(self,CustId):
        amount = float(input("enter deposit amount"))
        if amount <=0:
            print("enter valid amount")
            return
        currbal = start.CustomerDetailsdetails[CustId].Balance
        start.CustomerDetailsdetails[CustId].Balance = currbal + amount
        t = TransactionHistory(CustId,"Cash deposit",amount,start.CustomerDetailsdetails[CustId].Balance)
        start.CustomerDetailsdetails[CustId].transactionhistory.append(t)
        print("transaction completed")
        
    def printhistory(self,CustId):
        print("Name: "+start.CustomerDetailsdetails[CustId].Name+" \nACcno: "+start.CustomerDetailsdetails[CustId].Accno)
        for i in start.CustomerDetailsdetails[CustId].transactionhistory:
#             print(i.Type,i.TransId,i.Balance)
            print(f"Transid: {i.TransId}\n transType: {i.Type} \n Amount: {i.Amount}\n Balance:{i.Balance}")
        print()
    
    def Checkbalance(self,CustId):
        print("Balance: ",start.CustomerDetailsdetails[CustId].Balance)
    def checkpassword(self,pwd):
        upper = 0
        lower = 0
        number = 0
        for i in pwd:
            order = ord(i)
            if order>=48 and order<=57:
                number+=1
            if order>=97 and order<=122:
                lower+=1
            if order>=65 and order<=90:
                upper+=1
        print(upper,lower,number)
        if upper>=2 and lower>=2 and number>=2:
            return True
        else:return False
    
    def PasswordEncrypt(self,password):
        encryptedpwd=""
        for i in password:
            if i=="9":
                encryptedpwd+="0"
            elif i == "Z":
                encryptedpwd+="A"
            elif i =="z":
                encryptedpwd+="a"
            else:
                encryptedpwd+=(chr(ord(i)+1))
        return encryptedpwd
        
    def transfer(self,CustId):
        tranferid = int(input("enter tranferring acnnt id"))
        if tranferid not in start.CustomerDetailsdetails:
            print("no customers available")
            return 0
        amount = float(input("enter amount to be transferred"))
        if amount<=0:
            print("enter valid amount")
            return 0
        currbal = start.CustomerDetailsdetails[CustId].Balance
        if currbal > 1000 and currbal - amount > 1000:
            start.CustomerDetailsdetails[CustId].Balance = currbal - amount
            start.CustomerDetailsdetails[tranferid].Balance += amount
            t = TransactionHistory(CustId,f"tranfer to {tranferid}",amount,start.CustomerDetailsdetails[CustId].Balance)
            start.CustomerDetailsdetails[CustId].transactionhistory.append(t)
            t = TransactionHistory(CustId,f"tranfer from {CustId}",amount,start.CustomerDetailsdetails[tranferid].Balance)
            start.CustomerDetailsdetails[tranferid].transactionhistory.append(t)
            
            print("transfer completed")
            
    def Changepassword(self,CustId):
        pwd = str(input("enter new pwd"))
        if len(pwd)>=6 and self.checkpassword(pwd):
            retype = str(input("re enter new pwd"))
            if pwd != retype:
                print("mismatches")
                return
#             n= len(start.CustomerDetailsdetails[CustId].pwdhistory)-1
#             for i in range(n,n-1,-1):
#                 if i == start.CustomerDetailsdetails[CustId].pwdhistory[i]:
#                     print("cant change last 3 recent password")
#                     return
            start.CustomerDetailsdetails[CustId].Encryptedpwd = self.PasswordEncrypt(pwd)
            print("password changed")
            start.CustomerDetailsdetails[CustId].pwdhistory.append(pwd)
            
        else:
            print("password criteria violated")
    
    def passwordhistory(self,CustId):
        print("pwd history")
        for i in start.CustomerDetailsdetails[CustId].pwdhistory:
            print(i)
        

        
        
            
            
if __name__ == "__main__":
    Start = start()
    trans = transaction()
    Start.CreateAccounts(Customer("Kumar",Start.PasswordEncrypt("12345")),10000)
    Start.CreateAccounts(Customer('Madhu',Start.PasswordEncrypt("678910")),20000)
    Start.CreateAccounts(Customer("Rahul",Start.PasswordEncrypt("112233")),30000)
    Start.CreateAccounts(Customer("Robin",Start.PasswordEncrypt("44556677")),40000)
    while(True):
        first = int(input("1.CreateAccount\n 2.Login 3.Exit\n"))
        if first == 1:
            print("--Account Creating Page--")
            name = input("Enter Name")
            pwd = str(input("Type the pwd"))
            c = Customer(name,Start.PasswordEncrypt(pwd))
            Start.CreateAccounts(c)
#             Start.showcustomers()
        elif first == 2:
            CustId = Start.login()
            if CustId != 0:
                action = int(input("1.ATM Withdrawal\n 2.CashDeposit 3.AccountTransfer 4.see transhistory 5.Check balance 6.change password 7.pwdhistory\n"))
                if action == 1:
                    trans.atm(CustId)
                elif action == 2:
                    trans.Deposit(CustId)
                elif action == 3:
                    trans.transfer(CustId)
                elif action == 4:
                    trans.printhistory(CustId)
                elif action == 5:
                    trans.Checkbalance(CustId)
                elif action ==6:
                    trans.Changepassword(CustId)
                elif action ==7:
                    trans.passwordhistory(CustId)
                else:
                    continue
        
            
        else:
            continue
