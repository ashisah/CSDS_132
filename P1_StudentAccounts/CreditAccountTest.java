import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CreditAccountTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
    
   }
   
  
   
   /*
   setInterestRate-done
   getInterestRate-done
   getMonthlyPayment-done
   getAmountPaidThisMonth-done
   endOfMonth-done
   credit-done
   */
   
   @Test
   public void testSetAndGetInterestRate(){
      CreditAccount AshCredit = new CreditAccount("ajs511", 15.4);
      CreditAccount SriCredit = new CreditAccount("sxv666", 20.5);
      
      Assert.assertEquals(15.4, AshCredit.getInterestRate(), 0.0);
      AshCredit.setInterestRate(30.8);
      Assert.assertEquals(30.8, AshCredit.getInterestRate(), 0.0); 
      
      Assert.assertEquals(20.5, SriCredit.getInterestRate(), 0.0);
      SriCredit.setInterestRate(15.4);
      Assert.assertEquals(15.4, SriCredit.getInterestRate(), 15.4);       
     
   }
   
   @Test
   public void testGetMonthlyPayment(){
      CreditAccount AshCredit = new CreditAccount("ajs511", 15.4);
      CreditAccount SriCredit = new CreditAccount("sxv666", 20.5);
      Assert.assertEquals(0.0, AshCredit.getMonthlyPayment(), 0);
      AshCredit.charge(500);
      Assert.assertEquals(500.0, AshCredit.getMonthlyPayment(), 0.0);
      
      Assert.assertEquals(0.0, SriCredit.getMonthlyPayment(), 0);
      SriCredit.charge(1200);
      Assert.assertEquals(1200.0, SriCredit.getMonthlyPayment(), 0.0);
   }
   
   @Test
   public void testCredit(){
      CreditAccount AshCredit = new CreditAccount("ajs511", 15.4);
      CreditAccount SriCredit = new CreditAccount("sxv666", 20.5);
      AshCredit.credit(500);
      Assert.assertEquals(500.0, AshCredit.getAmountPaidThisMonth(), 500);
      
      SriCredit.credit(400);
      Assert.assertEquals(400.0, SriCredit.getAmountPaidThisMonth(), 400);
      
   }
   
   @Test
   public void testEndOfMonth(){
      CreditAccount AshCredit = new CreditAccount("ajs511", 15.4);
      CreditAccount SriCredit = new CreditAccount("sxv666", 20.5);
      
      AshCredit.charge(500);
      AshCredit.endOfMonth();
      Assert.assertEquals(506.417, AshCredit.getBalance(), 506.417);
      
      SriCredit.charge(1000);
      SriCredit.endOfMonth();
      Assert.assertEquals(1017.083, SriCredit.getBalance(), 1017.08);
      
      
   }
}
