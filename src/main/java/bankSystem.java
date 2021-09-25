import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class bankSystem {

    public static void main(String[] args) {



        Scanner obj = new Scanner(System.in);

        List<Customer> customers = new ArrayList();
        customers.add(new Customer("Aamna Kamran", "35202 34123422 2", "Mars House #5", "0301 8422773"));
        customers.add(new Customer("Syed Kamran Ali", "35202 00003422 2", "Earth House #100", "0300 8422773"));
        customers.add(new Customer("Hiba Asim", "35942 34129822 5", "Hogwarts Room #17", "0333 8470281"));

        Customer cust = new Customer();

        //Customer cust = new Customer("35202 34123422 2");
        //Account acc1 = new Account("0014 3445 0013 9591", 45000);

        String choice = "0";

        String id = " ";
        System.out.println("enter cnic: ");
        id = obj.nextLine();

        while (!choice.equals("4")) {
            System.out.println("\n\n\n\npress 1 if you wish to open an account");
            System.out.println("press 2 to go to account operations");
            System.out.println("press 3 if you wish to close your account");
            System.out.println("press 4 to log out");
            System.out.println("press 5 to log in as a different user");

            choice = obj.nextLine();

            //choice="1";
            //choice="2";
            //choice="3";

            if(choice.equals("5")) {
                System.out.println("enter cnic: ");
                id = obj.nextLine();
            }

            //System.out.println(id);

            if (choice.equals("1")) { //open new account

                int index = 0;
                boolean found = false;

                if (!customers.isEmpty()) {
                    for (index = 0; index < customers.size(); index++) {
                        cust = customers.get(index);
                        if (cust.cnic == id) {
                            cust.newAccount();
                            found = true;
                        }

                    }
                } else {
                    customers.add(new Customer(id));
                }

                if (found == false) {
                    customers.add(new Customer(id));
                }

            }
            else if (choice.equals("2")) { //account operations

                for (int index = 0; index < customers.size(); index++) {
                    cust = customers.get(index);
                    if (cust.cnic == id) {
                        break;
                    }
                }

                System.out.println("enter account number: \n");
                //obj.nextLine();
                String accountNo = obj.nextLine();

                System.out.println("\npress 1 to make a transfer");
                System.out.println("press 2 to check balance");
                System.out.println("press 3 to print statement");
                System.out.println("press 4 to calculate Zakat");
                System.out.println("press 5 to make a deposit");
                System.out.println("press 6 to make a withdrawal");

                int input = 0;
                int option = Integer.parseInt(obj.nextLine());

                if (option == 1) { //transfer
                    System.out.println("amount: ");
                    input = Integer.parseInt(obj.nextLine());
                    cust.getAccount(accountNo).makeWithdrawal(input);

                    System.out.println("account number of the receiver: ");
                    String accNum = obj.nextLine();

                    boolean found = false;

                    for (int index = 0; index < customers.size(); index++) {
                        cust = customers.get(index);
                        if (cust.existAccount(accNum)) {
                            found = true;
                            cust.getAccount(accNum).makeDeposit(input);
                            break;
                        }
                    }

                    if(found == false)
                        System.out.println("account does not exist");

                } else if (option == 2) {
                    cust.getAccount(accountNo).checkBalance();
                } else if (option == 3) {
                    cust.printStatement(cust.getAccount(accountNo));
                } else if (option == 4) {
                    //cust.getAccount(accountNo).
                } else if (option == 5) {
                    System.out.println("amount: ");
                    input = Integer.parseInt(obj.nextLine());
                    cust.getAccount(accountNo).makeDeposit(input);
                } else if (option == 6) {
                    System.out.println("amount: ");
                    input = Integer.parseInt(obj.nextLine());
                    cust.getAccount(accountNo).makeWithdrawal(input);
                }
            }
            else if (choice.equals("3")){
                System.out.println("account number of the account you wish to close: ");
                String accNum = obj.nextLine();

                boolean found = false;

                for (int index = 0; index < customers.size(); index++) {
                    cust = customers.get(index);
                    if (cust.existAccount(accNum)) {
                        found = true;
                        cust.getAccount(accNum).accNo = "does not exist";
                        break;
                    }

                    if(found == false){
                        System.out.println("account does not exist");
                    }

                    if(cust.accounts.isEmpty())
                        cust.deleteCustomer();
                }
            }
        }
    }
}

class Customer{
    String name;
    String cnic;
    String address;
    String phoneNo;
    List<Account> accounts = new ArrayList();

    Customer(){
        this.cnic = "";
    }

    Customer(String id){
        this.cnic = id;
        this.newCustomer();
    }

    Customer(String Cname, String id, String Caddress, String CphoneNo){
        this.name = Cname;
        this.cnic = id;
        this.address = Caddress;
        this.phoneNo = CphoneNo;
        newAccount();
    }

    public void newCustomer(){
        Scanner obj= new Scanner(System.in);
        String input;

        System.out.println("name: ");
        input = obj.nextLine();
        this.name=input;

        System.out.println("address: ");
        input = obj.nextLine();
        this.address=input;

        System.out.println("phone number: ");
        input = obj.nextLine();
        this.phoneNo=input;

        newAccount();
    }

    public void newAccount(){
        if(accounts.size()<=2)
            this.accounts.add(new Account(this.cnic+Integer.toString(accounts.size()+1)));
        else
            System.out.println("users can only have a maximum of 2 accounts");
    }

    public Account getAccount(String ACnum){
        Account account = new Account();

        for (int index = 0; index < accounts.size(); index++) {
            account = accounts.get(index);
            if (account.accNo == ACnum) {
                break;
            }
        }

        return account;
    }

    public boolean existAccount(String ACnum){
        Account account = new Account(); boolean found = false;

        for (int index = 0; index < accounts.size(); index++) {
            account = accounts.get(index);
            if (account.accNo == ACnum) {
                found = true;
                break;
            }
        }

        return found;
    }

    public void printStatement(Account account){
        System.out.println("Name of Customer = "+ this.name);
        System.out.println("Account Number = "+ account.accNo);
        System.out.println("Balance = "+ account.balance);
    }

    public void deleteCustomer(){
        this.name = "";
        this.cnic = "";
        this.address = "";
        this.phoneNo = "";
    }
}

class Account{
    String accNo;
    int balance;
    String createdD; //use sys time in constructor

    Account(){
        this.accNo="";
        this.balance=0;
    }

    Account(String accountNumber){
        this.accNo=accountNumber;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        createdD = formatter.format(date);

        System.out.print("ACCOUNT CREATED\nyour account number is: "+this.accNo+"\n");
    }

    Account(String accountNumber, int amount){
        this.accNo=accountNumber;
        this.balance=amount;
    }

    public void checkBalance(){
        System.out.print("balance: "+this.balance);
    }

    public void makeDeposit(int amount){
        this.balance+=amount;
        this.checkBalance();
    }

    public void makeWithdrawal(int amount){
        this.balance-=amount;
        this.checkBalance();
    }
}

class Savings extends Account{
    float interest;

    public void calculateZakat(){
        if(this.balance>=20000)
            this.balance-=(this.balance*2.5)/100;
    }

    public void setInterestRate(float rate){
        this.interest=rate;
    }

    public void calculateInterest(){
        System.out.println(this.balance*this.interest);
    }
}

class Checking extends Account{

}
