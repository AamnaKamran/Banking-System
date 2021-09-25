import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testing {

    static List<Customer> customers = new ArrayList<>();

    @BeforeClass
    public static void Populate(){
        System.out.println("START TESTING NOW\n");
    }

    @Test
    @DisplayName("check customer details")
    public void aTest(){
        customers.add(new Customer("Aamna Kamran", "35202 34123422 2", "Mars House #5", "0301 8422773"));
        Assertions.assertEquals(customers.get(0).name,"Aamna Kamran");
        Assertions.assertEquals(customers.get(0).cnic,"35202 34123422 2");
        Assertions.assertEquals(customers.get(0).address,"Mars House #5");
        Assertions.assertEquals(customers.get(0).phoneNo,"0301 8422773");
    }

    @Test
    @DisplayName("check initial balance")
    public void bTest(){
        Assertions.assertEquals(customers.get(0).getAccount("35202 34123422 21").balance,0);
    }

    @Test
    @DisplayName("check balance after deposit")
    public void cTest(){
       customers.get(0).getAccount("35202 34123422 21").makeDeposit(10500);
        Assertions.assertEquals(customers.get(0).getAccount("35202 34123422 21").balance,10500);
    }

    @Test
    @DisplayName("check balance after withdrawal")
    public void dTest(){
        customers.get(0).getAccount("35202 34123422 21").makeWithdrawal(500);
        Assertions.assertEquals(customers.get(0).getAccount("35202 34123422 21").balance,10000);
    }

    @Test
    @DisplayName("create new account")
    public void eTest(){
       customers.get(0).newAccount();
       Assertions.assertEquals(customers.get(0).getAccount("35202 34123422 22").accNo,"35202 34123422 22");
    }

    @Test
    @DisplayName("create third account")
    public void fTest(){
        customers.get(0).newAccount(); //fails because a customer can only have 2 accounts

        Assertions.assertEquals(customers.get(0).accounts.size(),2); //size will still be 2 so pass
    }

    @AfterClass
    public static void endTesting(){
        System.out.println("\nEND OF TESTING");
    }
}
