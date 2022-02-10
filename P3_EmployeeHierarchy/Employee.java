/**
 * This class represents a typical Employee, acts as the parent class for all employees (Salaried Employee, Hourly Employee, Sales Employee) 
 * Also the grandparent class for all the supervisors
 * @author Ashley Sah
 * */

/*
Employee e
*/
public abstract class Employee{

   //represents the firstName of the Employee
   private String firstName;
   //represents the lastName of the Employee
   private String lastName;
   //represents the employee number
   private String number;
   //represents the salary of the employee
   private double salary;
   //represents the bonus of the employee
   private double bonus = 0;
   //represents the number of hours the employees worked
   private double hoursWorked = 0.0;
   
   private Supervisor supervisor;
   
   //constructor
   public Employee(String firstName, String lastName, String number, double salary){
      this.firstName = firstName;
      this.lastName = lastName;
      this.number = number;
      this.salary = salary;
   }
   
   //returns employee's first name
   public String getFirstName(){
     return firstName;
   }
   
   //return's employee's last name
   public String getLastName(){
     return lastName;
   }
   
   //sets employee's first and last name
   public void setName(String firstName, String lastName){
     this.firstName = firstName;
     this.lastName = lastName;
   }
   
   public String getNumber(){
    return number;
   }
   
   //returns employee's salary
   public double getSalary(){
     return salary;
   }
   
   //need to figure out what to do with this method since it's wierd for sales and hourly
   public void setSalary(double salary){
     this.salary = salary;
   }
   
   public double getBonus(){
     return bonus;
   }
   
   public void setBonus(double bonus){
     this.bonus = bonus;
   }
   
   //every employee has hours that they've worked
   public void setHoursWorked(double hoursWorked){
     this.hoursWorked = hoursWorked;
   }
   
   public double getHoursWorked(){
     return hoursWorked;
   }
   
   public void setSupervisor(Supervisor supervisor){
     this.supervisor = supervisor;
   }
   
   //we need to edit this to figure out how we want the information about the supervisor to be presented
   public String getSupervisor(){
     return supervisor.toString();
   }
   
   //should the parameter be a string or employee?
   public int compareToByName(Employee employee){
     return (getFirstName() + getLastName()).compareTo(employee.getFirstName()+employee.getLastName());
   }
   
   //should the parameter be a string or employee?
   public int compareToByEarnings(Employee employee){
     return (int)(getAmountEarned()-employee.getAmountEarned());
   }
   
   abstract void adjustPay(double percentage);
   abstract double getAmountEarned();
   
   @Override
   public boolean equals(Object employee){
     if(employee instanceof Employee){//find a better way to do this instead of instanceof
       Employee e = (Employee)employee;
       return ( (e.getFirstName()).equals(getFirstName()) && (e.getLastName()).equals(getLastName()) && (e.getNumber() == getNumber()) );
     }
     else
       return false;
   }
   
   @Override
   public String toString(){
     return getNumber() + ": " + getLastName() + ", " + getFirstName();
   }
   
}