import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class StudentAccountTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
     // Assert.assertEquals("Default test added by jGRASP. Delete "
          // + "this test once you have added your own.", 0, 1);
   }
   
   
   /*
   setName -tested
   getName -tested
   setAddress - tested
   getAddress - tested
   setLibraryAccount -tested
   getLibraryAccount -tested
   setTuitionAccount - tested
   getTuitionAccount -tested
   setDiningAccount - tested
   getDiningAccount -tested

   getBalance-tested
   charge-tested
   credit
   */
   
   @Test
   public void testSetandGetName(){
      StudentAccount Pranav = new StudentAccount("pxd666", "Pranav Dhinikar");
      Assert.assertEquals("Pranav Dhinikar", Pranav.getName() );
      Pranav.setName("Ashley Sah");
      Assert.assertEquals("Ashley Sah", Pranav.getName() );
   }
   
   @Test
   public void testSetAndGetAddress(){
      StudentAccount Pranav = new StudentAccount("pxd666", "Pranav Dhinikar");
      
      Pranav.setAddress("525 Marine Street");
      Assert.assertEquals("525 Marine Street", Pranav.getAddress());
      Pranav.setAddress("13311 Juniper Road");
      Assert.assertEquals("13311 Juniper Road", Pranav.getAddress());
      
   } 
   
   @Test
   public void testSetandGetLibraryAccount(){
      StudentAccount Ashley = new StudentAccount("ajs511", "Ashley Sah");
      
      LibraryAccount AshLibAccount = new LibraryAccount("ajs511", 500, 5.0, 6.0);
      Ashley.setLibraryAccount(AshLibAccount);
      Assert.assertEquals(AshLibAccount, Ashley.getLibraryAccount() );
      
      LibraryAccount PranavLibAccount = new LibraryAccount("pxd666", 600, 4.0, 5.0);
      Ashley.setLibraryAccount(PranavLibAccount);
      Assert.assertEquals(PranavLibAccount, Ashley.getLibraryAccount() ); 
   
   }
   
   @Test
   public void testSetandGetTuitionAccount(){
      
      StudentAccount Ashley = new StudentAccount("ajs511", "Ashley Sah");
      
      CreditAccount AshCredAccount = new CreditAccount("ajs511", 2.50);
      Ashley.setTuitionAccount(AshCredAccount);
      Assert.assertEquals(AshCredAccount, Ashley.getTuitionAccount() );
      
      CreditAccount SriCredAccount = new CreditAccount("sxv666", 6.0);
      Ashley.setTuitionAccount(SriCredAccount);
      Assert.assertEquals(SriCredAccount, Ashley.getTuitionAccount() );

   }
   
   @Test
   public void testSetandGetDiningAccount(){
      
      StudentAccount Ashley = new StudentAccount("ajs511", "Ashley Sah");
      
      CreditAccount AshCredAccount = new CreditAccount("ajs511", 2.50);
      Ashley.setDiningAccount(AshCredAccount);
      Assert.assertEquals(AshCredAccount, Ashley.getDiningAccount() );
      
      CreditAccount SriCredAccount = new CreditAccount("sxv666", 6.0);
      Ashley.setDiningAccount(SriCredAccount);
      Assert.assertEquals(SriCredAccount, Ashley.getDiningAccount() );

   }
   
   @Test
   public void testGetBalance(){
      StudentAccount Ashley = new StudentAccount("ajs511", "Ashley Sah");
      
      /*plan:
      - create a dining and library account, no tuition account (yay)
      - put charges on dining and library accounts, set them to student account
      - use assert equals method
      - create tutition account, set it to student account
      - add charge to tuition account
      - use assert equals method again
      */
      
      CreditAccount AshDining = new CreditAccount("ajs511", 6.0);
      AshDining.charge(400);
      LibraryAccount AshLib = new LibraryAccount("ajs511", 500, 4.0, 6.0);
      AshLib.charge(400);
      
      Ashley.setLibraryAccount(AshLib);
      Ashley.setDiningAccount(AshDining);
      
      Assert.assertEquals(800, Ashley.getBalance(), 0);
      
      CreditAccount AshTuition = new CreditAccount("ajs511", 5.0);
      Ashley.setTuitionAccount(AshTuition);
      AshTuition.charge(200);
      Assert.assertEquals(1000, Ashley.getBalance(), 0.0);
   
   }
   
   @Test
   public void testCharge(){
      StudentAccount Ashley = new StudentAccount("ajs511", "Ashley Sah");
      
      CreditAccount AshTuition = new CreditAccount("ajs511", 5.0);
      
      Ashley.charge(20000);
      
      Assert.assertEquals(0, Ashley.getBalance(), 0);
      
      Ashley.setTuitionAccount(AshTuition);
      Ashley.charge(20000);
      Assert.assertEquals(20000, Ashley.getBalance(), 0);
      
   }
   
   @Test
   public void testCredit(){
      StudentAccount Ashley = new StudentAccount("ajs511", "Ashley Sah");
      
      /*plan:
      - create a dining and library account, no tuition account (yay)
      - put charges on dining and library accounts, set them to student account
      - run credit (should be enough to pay off dining account and part of lib account)
      - use assert equals method of getBalance of lib account and dining account
      - create tutition account, set it to student account
      - add charge to tuition account, dining account, and lib account
      - use assert equals method again for getBalance of all three accounts
      */
      
      //dining account
      CreditAccount AshDining = new CreditAccount("ajs511", 4.0);
      //Tuition account
      CreditAccount AshTuition = new CreditAccount("ajs511", 3.0);
      //library account
      LibraryAccount AshLib = new LibraryAccount("ajs511", 400, 5.0, 6.0);
      
      //set dining account and library account to student
      
      Ashley.setLibraryAccount(AshLib);
      Ashley.setDiningAccount(AshDining);
      
      //add charges to dining and library account
      AshLib.charge(300);
      AshDining.charge(1300);
      
      Ashley.credit(1500);//now Dining balance should be 0 and lib balance should be 100
      
      Assert.assertEquals(0.0, AshDining.getBalance(), 0);
      Assert.assertEquals(100.0, AshLib.getBalance(), 0);
      
      Ashley.setTuitionAccount(AshTuition);
      
      
      AshTuition.charge(200);
      AshDining.charge(1300);
      AshLib.charge(200);//don't forget Libaccount still has 100 dollars
      //total charges = 1800
      
      Ashley.credit(1600);
      
      Assert.assertEquals(0.0, AshTuition.getBalance(), 0);
      Assert.assertEquals(0.0, AshDining.getBalance(), 0);
      Assert.assertEquals(200.0, AshLib.getBalance(), 0);
      Assert.assertEquals(200.0, Ashley.getBalance(), 0);   
   
   }
   
   
        
}
