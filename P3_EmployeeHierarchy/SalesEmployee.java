public class SalesEmployee extends Employee{
  
  private double commission;
  private int numSales = 0;
  public SalesEmployee(String firstName, String lastName, String number, double salary, double commission){
    super(firstName, lastName, number, salary);
    this.commission = commission;
  }
  
  public double getCommission(){
    return commission;
  }
  
  public void setCommission(double commission){
    this.commission = commission;
  }
  
  public int getNumSales(){
    return numSales;
  }
  
  public void setNumSales(int numSales){
    this.numSales = numSales;
  }
  
  public double getAmountEarned(){
    return getSalary()+ getCommission()*getNumSales()+ getBonus();
  }
  
  public void adjustPay(double percentage){
    setCommission((1+(percentage/100))*getCommission());
  }
  
  @Override
  public String toString(){
    return super.toString() + ", Sales Employee";
  }
}