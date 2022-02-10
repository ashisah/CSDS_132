import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LibraryAccountTest {

   
   /*methods to test
   setBookFine-tested
   getBookFine-tested
   setReserveFine-tested
   getReserveFine-tested
   incrementOverdueBooks-tested
   decrementOverdueBooks-tested
   getNumberOverdueBooks-tested
   incrementOverdueReserve-tested
   decrementOverdueReserve-tested
   getNumberOverdueReserve-tested
   canBorrow-tested
   endOfDay-tested
   
   */
   
   
   
   @Test
   public void testSetandGetBookFine(){
      LibraryAccount Ash = new LibraryAccount("ajs511", 500, 5.0, 6.0 );
      LibraryAccount Pranav = new LibraryAccount("pxd666", 600, 6.0, 8.0);
      
      Assert.assertEquals(5.0, Ash.getBookFine(), 0.0);
      Ash.setBookFine(4.0);
      Assert.assertEquals(4.0, Ash.getBookFine(), 0.0);
      
      Assert.assertEquals(6.0, Pranav.getBookFine(), 0.0);
      Pranav.setBookFine(5.0);
      Assert.assertEquals(5.0, Pranav.getBookFine(), 0.0);
      
   }
   
   @Test
   public void testSetandGetReserveFine(){
      
      LibraryAccount Ash = new LibraryAccount("ajs511", 500, 5.0, 6.0 );
      LibraryAccount Pranav = new LibraryAccount("pxd666", 600, 6.0, 8.0);
      
      Assert.assertEquals(6.0, Ash.getReserveFine(), 0.0);
      Ash.setReserveFine(4.0);
      Assert.assertEquals(4.0, Ash.getReserveFine(), 0.0);
      
      Assert.assertEquals(8.0, Pranav.getReserveFine(), 0.0);
      Pranav.setReserveFine(5.0);
      Assert.assertEquals(5.0, Pranav.getReserveFine(), 0.0);
   
   }
   
   @Test
   
   public void testNumberOfOverdueBooksMethods(){
      LibraryAccount Ash = new LibraryAccount("ajs511", 500, 5.0, 6.0 );
      
      Assert.assertEquals(0, Ash.getNumberOverdueBooks());      
      Ash.incrementOverdueBooks();
      Assert.assertEquals(1, Ash.getNumberOverdueBooks());
      Ash.decrementOverdueBooks();
      Assert.assertEquals(0, Ash.getNumberOverdueBooks());

   }
   
   @Test
   public void testNumberOverdueReserveMethods(){
      LibraryAccount Pranav = new LibraryAccount("pxd666", 600, 6.0, 8.0);
      
      Assert.assertEquals(0, Pranav.getNumberOverdueReserve());
      Pranav.incrementOverdueReserve();
      Assert.assertEquals(1, Pranav.getNumberOverdueReserve());
      Pranav.decrementOverdueReserve();
      Assert.assertEquals(0, Pranav.getNumberOverdueReserve());      
   }
   
  @Test
  public void testCanBorrow(){
      
      LibraryAccount Ash = new LibraryAccount("ajs511", 120, 5.0, 6.0 );
      Ash.charge(400);
      Assert.assertEquals(false, Ash.canBorrow());
      Ash.credit(300);
      Assert.assertEquals(true, Ash.canBorrow());
  }

 @Test 
 public void testEndOfDay(){
      
      LibraryAccount Ash = new LibraryAccount("ajs511", 120, 5.0, 6.0);
      Ash.incrementOverdueReserve();
      Ash.incrementOverdueReserve();
      Ash.endOfDay();
      Assert.assertEquals(12.0, Ash.getBalance(),0.0);
      
      Ash.incrementOverdueBooks();
      Ash.endOfDay();
      Assert.assertEquals(29.0, Ash.getBalance(), 0.0);
 }
   
}
