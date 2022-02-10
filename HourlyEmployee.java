public class HourlyEmployee extends Employee{
  
  private double hourlyRate = 0;
  
  public HourlyEmployee(String firstName, String lastName, String number, double hourlyRate){
    super(firstName, lastName, number, 0);
    this.hourlyRate = hourlyRate; 
  }
  
  public double getHourlyRate(){
    return hourlyRate;
  }
  
  public void setHourlyRate(double rate){
    hourlyRate = rate;
  }
  
  //overriding getSalary because other programmers might be to dumb to understand that not all employees have a salary
  public double getSalary(){
    return getHoursWorked()*getHourlyRate();
  }
  
  public double getAmountEarned(){
    return getSalary() + getBonus();
  }
  
  public void adjustPay(double percentage){
    setHourlyRate( (1+(percentage/100))*getHourlyRate());
  }
  
  @Override
  public String toString(){
    return super.toString() + ", Hourly Employee";
  }
  
}