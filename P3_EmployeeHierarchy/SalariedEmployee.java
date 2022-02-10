/**
 * represents a normal employee w/ a standard salary
 * */
public class SalariedEmployee extends Employee{
  
  public SalariedEmployee(String firstName, String lastName, String number, double salary){
    super(firstName, lastName, number, salary);
  }
  
  public double getAmountEarned(){
    return getBonus()+getSalary();
  }
  
  public void adjustPay(double percentage){
    setSalary( 1+(percentage/100)*getSalary() );
  }
  
  @Override
  public String toString(){
    return super.toString() + ", Salaried Employee";
  }
}