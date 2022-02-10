//represents record of how much a student owes to the school

public class Account{
   
   //stores the student's account number
   private final String accountNumber;
   //stores current balance of the account;
   private double balance = 0.0;
   //stores the balance limit (the max amount the student can owe)
   private int balanceLimit = 0;
   
   //constructor–initalizes all the values
   public Account(String accountNumber, int balanceLimit)
   {
      
      this.accountNumber = accountNumber;
      this.balanceLimit = balanceLimit;
   }
   
   //constructor so creditAccount doesn't give me an error
   public Account(String accountNumber)
   {
      this.accountNumber = accountNumber;
   }
   
   //return the account number
   public String getAccountNumber()
   {
      return this.accountNumber;
   }
   
   //returns the current balance of student's account
   public double getBalance()
   {
      return this.balance;
   }
   
   //returns the balance limit
   public int getBalanceLimit()
   {
      return this.balanceLimit;
   }
   
   //sets the balance limit
   public void setBalanceLimit(int limit)
   {
      balanceLimit = limit;
   }
   
   //adds a charge to the student's balance
   public void charge(double charge1)
   {
      balance = balance + charge1;
   }
   
   //reduces balance on account by payment
   public void credit(double payment)
   {
      if(this.getBalance()-payment <0)
         this.balance = 0;
      else
         this.balance = this.balance - payment;
      
   }
   


}
