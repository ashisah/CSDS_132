//represents an account that also charges interest on it's balance
public class CreditAccount extends Account{

   //stores the interest rate applied at the end of the month to the remaining balance
   private double interestRate =0.0;
   //stores the minimum payment to be paid at the end of the month
   private double monthlyPayment = this.getBalance();
   //stores the amount you pay to the account
   private double amountPaidThisMonth = 0.0;
   
   //constructor
   public CreditAccount(String accountNumber,double interestRate)
   {
      super(accountNumber);
      this.interestRate = interestRate;
   }
   
   //sets the interest rate value
   public void setInterestRate(double interestRate)
   {
      this.interestRate = interestRate;
   }
   
   //returns interest rate
   public double getInterestRate()
   {
      return interestRate;
   }
   
   //returns monthly payment
   public double getMonthlyPayment()
   {
      monthlyPayment = this.getBalance();
      return monthlyPayment;
   }
   
   //returns amount paid this month
   public double getAmountPaidThisMonth()
   {
      return amountPaidThisMonth;
   }
   
   /*performs the end of month functions
      -checks if interest must be charged
      -sets next monthly payment + resets the amount paid for that month 
   */
   
   public void endOfMonth()
   {
      if(this.getAmountPaidThisMonth()<this.getMonthlyPayment())
         this.charge( (this.getInterestRate()/100)*this.getBalance()/12 );
      this.monthlyPayment = this.getBalance();
      amountPaidThisMonth = 0.0;
   }
   
   //overwrites credit method from account
   public void credit(double payment)
   {
      super.credit(payment);
      amountPaidThisMonth = amountPaidThisMonth + payment;
   }
      
   
   
}

/*
setInterestRate-done
getInterestRate-done
getMonthlyPayment
getAmountPaidThisMonth
endOfMonth
credit (overload...maybe overwrite, we'll see)
*/