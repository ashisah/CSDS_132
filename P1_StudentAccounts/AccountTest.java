import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class AccountTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
   }
   /*
   -getAccountNumber
   -getBalanceLimit
   -getBalance
   -setBalanceLimit
   -charge
   -credit
   */
   
   //tests getAccountNumber
   
   @Test
   
   public void testGetAccountNumber(){
      Account Ashley = new Account("ajs511", 500000);
      Account Sirini = new Account("sxv666", 900000);
      Assert.assertEquals("ajs511", Ashley.getAccountNumber());
      Assert.assertEquals("sxv666", Sirini.getAccountNumber());
   }
   
   @Test
   public void testGetBalanceLimit(){
      Account Ashley = new Account("ajs511", 500000);
      Account Sirini = new Account("sxv666", 900000);
      Assert.assertEquals(500000, Ashley.getBalanceLimit());
      Assert.assertEquals(900000, Sirini.getBalanceLimit());
   }
   
   @Test
   public void testGetBalance(){
      Account Ashley = new Account("ajs511", 500000);
      Assert.assertEquals(0.0, Ashley.getBalance(),0);
      Ashley.charge(5000);
      Assert.assertEquals(5000.0, Ashley.getBalance(),5000);
   }
   
   @Test
   public void testSetBalanceLimit(){
      Account Ashley = new Account("ajs511", 500000);
      Ashley.setBalanceLimit(1000000);
      Assert.assertEquals(1000000, Ashley.getBalanceLimit());
      Ashley.setBalanceLimit(300);
      Assert.assertEquals(300, Ashley.getBalanceLimit());
   }
   
   @Test
   public void testCharge(){
      Account Ashley = new Account("ajs511", 500000);
      Ashley.charge(5000);
      Assert.assertEquals(5000.0, Ashley.getBalance(),5000);
      Ashley.charge(300);
      Assert.assertEquals(5300.0, Ashley.getBalance(),5300);     
   }
   
   @Test
   public void testCredit(){
      Account Ashley = new Account("ajs511", 500000);
      Ashley.charge(5000);
      Ashley.credit(400);
      Assert.assertEquals(4600.0, Ashley.getBalance(),4600);
      Ashley.credit(5000);
      Assert.assertEquals(0.0, Ashley.getBalance(),0);
   }
      
}
