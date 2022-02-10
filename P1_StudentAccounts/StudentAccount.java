//represents student's master account w/ dining, tuition, and library account

public class StudentAccount extends Account{
   
   //stores student's name
   private String name = "";
   //stores student's address
   private String address = "";
   //stores student's library account
   private LibraryAccount libraryAccount = null;
   //stores student's associated tuition account
   private CreditAccount tuitionAccount = null;
   //stores student's associated dining account
   private CreditAccount diningAccount = null;
   //stores the refund amount
   private double refundAmount = 0.0;
   
   //constructor
   public StudentAccount(String accountNumber, String name)
   {
      super(accountNumber);
      this.name = name;
   }
   
   //changes name
   public void setName(String name)
   {
      this.name = name;
   }
   
   //returns student's name
   public String getName()
   {
      return name;
   }
   
   //sets the student's address
   public void setAddress(String address)
   {
      this.address = address;
   }
   
   //returns the student's address
   public String getAddress()
   {
      return address;
   }
   
   //sets student's library account
   public void setLibraryAccount(LibraryAccount inputLibraryAccount)
   {
      libraryAccount = inputLibraryAccount;
   }
   
   //returns associated libraryAccount
   public LibraryAccount getLibraryAccount(){
      return libraryAccount;
   }
   
   //sets student's assocaited tuition account
   public void setTuitionAccount(CreditAccount credAccount)
   {
      tuitionAccount = credAccount;  
   }
   
   //returns student's associated tuition account
   public CreditAccount getTuitionAccount()
   {
      return tuitionAccount;
   }
   
   //sets the student's associated dining account
   public void setDiningAccount(CreditAccount diningAccount)
   {
      this.diningAccount = diningAccount;
   }
   
   //returns student's associated diningAccount
   public CreditAccount getDiningAccount()
   {
      return diningAccount;
   }
   
   //returns the overall balance of all the student's accounts
   public double getBalance()
   {
      double balance = 0.0;
      if(this.getLibraryAccount() != null)
         balance = balance + this.getLibraryAccount().getBalance();
      if(this.getDiningAccount() != null)
         balance = balance + this.getDiningAccount().getBalance();
      if(this.getTuitionAccount() != null)
         balance = balance + this.getTuitionAccount().getBalance();
      refundAmount = refundAmount - balance;
      return balance;
   }
   
   
   //applies a charge to the student's tuition account
   public void charge(double charge)
   {
      if( this.getTuitionAccount() != null)//if tuition account exists...
      {
         if(charge-refundAmount > 0)
            this.getTuitionAccount().charge(charge-refundAmount);
         else
            this.refundAmount = (refundAmount-charge);
      }
      
      
   }
   
   /*
   Applies payment to the students' account
   */   
   public void credit(double payment)
   {
      /*pay off tuition amount*/
      //check if tutition account exists
      
      if(this.getTuitionAccount() != null)//if it exists then...
      {
         if(this.getTuitionAccount().getMonthlyPayment() > payment) //if payment is less than tuition amount
         {
            this.getTuitionAccount().credit(payment); //applies payment to tuitionAccount
            payment = 0.0;
         }
         else //if payment is more than/equal to tuition
         {
            payment = payment - getTuitionAccount().getMonthlyPayment();//reduces payment by tuition amount
            this.getTuitionAccount().credit( this.getTuitionAccount().getMonthlyPayment() );//makes tuition balance 0
         }
         
      }
      
      //pay off dining account
      
      if(this.getDiningAccount() !=null)//if it exists then...
      {
         if(this.getDiningAccount().getMonthlyPayment() > payment)//if payment is less than dining amount
         {
            this.getDiningAccount().credit(payment);
            payment = 0.0;
         }
         else
         {
            payment = payment - this.getDiningAccount().getMonthlyPayment();
            this.getDiningAccount().credit( this.getDiningAccount().getMonthlyPayment() );
         }
      
      }
      
      //pay off library account
      
      if(this.getLibraryAccount() != null)
      {
         if(this.getLibraryAccount().getBalance() > payment)//if payment is less than library account balance
         {
            this.getLibraryAccount().credit(payment);
            payment = 0.0;
         }
         else
         {
            payment = payment - this.getLibraryAccount().getBalance();
            this.getLibraryAccount().credit( this.getLibraryAccount().getBalance() );
            
         }
      
      }
      
      if(payment>0)
         this.refundAmount = payment;
      
   
   }
   
   


}

/*
A student's account is divided into three parts: 
the amount they owe for tuition, 
the amount they owe for any dining charges, 
and the amount owed to the library for overdue items. 
the StudentAccount class should extend the Account class. 
*/