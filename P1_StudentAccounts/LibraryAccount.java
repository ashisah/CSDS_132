//represents a record of how much a library patron owes their library

public class LibraryAccount extends Account{
   
   //stores the set bookFine of an overdue book
   private double bookFine = 0.0;
   //stores teh set fine of a reserved overdue book (or other item, idk)
   private double reservedItemsFine = 0.0;
   //stores number of books on account that are overdue
   private int numOverdueBooks = 0;
   //stores number of reserved books/items on account that are overdue
   private int numOverdueReserved = 0;
   //stores the current balance of account
   //private double balance = 0.0;
   
   //constructor, sets field values
   public LibraryAccount(String accountNumber, int balanceLimit, double bookFine, double reservedItemsFine)
   {
      super(accountNumber, balanceLimit);
      this.bookFine = bookFine;
      this.reservedItemsFine = reservedItemsFine;
   }
   
   //sets the book fine
   public void setBookFine(double bookFine)
   {
       this.bookFine = bookFine;
   }
   
   //returns the book fine value
   public double getBookFine()
   {
      return this.bookFine;   
   }
   
   //sets the overdue fine value
   public void setReserveFine(double reservedItemsFine)
   {
      this.reservedItemsFine = reservedItemsFine;
   }
   
   //returns the overdue fine value
   public double getReserveFine()
   {
      return reservedItemsFine;
   }
   
   //increases the number of overdue books on the account by 1
   public void incrementOverdueBooks()
   {
      numOverdueBooks++;
   }
   
   //decreases the number of overdue books on the account by 1
   public void decrementOverdueBooks()
   {
      if (numOverdueBooks !=0)
         numOverdueBooks--;
   }  
   
   //returns the number of overdue books on the account
   public int getNumberOverdueBooks()
   {
      return numOverdueBooks;
   }
   
   //increases the number of overdue reserved items on the account by 1
   public void incrementOverdueReserve()
   {
      numOverdueReserved++;
   }
   
   //decreases the number of overdue reserved items on the account by 1
   public void decrementOverdueReserve()
   {
      if(numOverdueReserved != 0)
         numOverdueReserved--;
   }
   
   //returns the number of reserved overdue items on account
   public int getNumberOverdueReserve()
   {
      return numOverdueReserved;
   }
   
   //determines if the person is allowed to borrow items
   public boolean canBorrow()
   {
      return( this.getBalance() <= this.getBalanceLimit() );
   }
   
   //updates account balance
   public void endOfDay()
   {
      double charge = this.getNumberOverdueReserve()*this.getReserveFine() + this.getNumberOverdueBooks()*this.getBookFine();
      this.charge(charge);
      
   }
   
}