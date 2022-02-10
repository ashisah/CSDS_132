import java.util.NoSuchElementException;
//import java.util.Iterator;

/**
 * EmployeeDatabase is a LinkedList of Employees
 * @author Ashley Sah
 * */
public class EmployeeDatabase extends LinkedList<Employee> implements Iterable<Employee>{
  
/**
 * adds an employee to the Employee Database
 * @param employee the employee we are adding to the Employee Database
 */
  public void add(Employee employee){
    this.addToFront(employee);
  }
  
  /**
   * NoSuchEmployeeException is NoSuchElementException specific to EmployeeDatabase
   * @author Ashley Sah
   */
  public class NoSuchEmployeeException extends NoSuchElementException{
    public NoSuchEmployeeException(){
      super();
    }
  }
  
  /**
   * removes an employee in employee database with a the input values of first name, last name, and number
   * @param firstName the first name of the employee we want to remove
   * @param lastName the last name of the employee we want to remove
   * @param number the number of the employee we want to remove
   * @return information on the employee removed
   * @exception NoSuchEmployeeException (if there is no employee with the first name, last name, and number inputs)
   */
  public String remove(String firstName, String lastName, String number){
	//iterator for the method
    EmployeeDatabaseIterator it = this.iterator();
    //while there are still values in the Employee Database to iterate to...
    while(it.hasNext()){
      //get the next employee
      Employee e = it.next();
      System.out.println("Current Employee being examined is: " + e.getFirstName() );
      //check if that employee has the same first name, last name, and number, and if it does...
      if( e.getLastName().equals(lastName) && e.getFirstName().equals(firstName) && e.getNumber().equals(number) ){
    	  //save the information about that employee
        String save = e.toString();
        //remove that employee
        it.remove();
        //return the employee info
        return save;
      }
    }
    //if it reaches this statement, we know there is no employee in employee database with the input first name, last name, and number
    throw new NoSuchEmployeeException();
  }
  
  /**
   * Finds and returns an employee with the input first name, last name, and number input 
   * @param firstName first name of the employee we're looking for
   * @param lastName last name of the employee we're looking for
   * @param number number of the employee we're looking for
   * @return the employee (if found)
   * @exception NoSuchEmployeeException (if there is no employee with the first name, last name, and number inputs)
   */
  public Employee find(String firstName, String lastName, String number){
    
	 //creates an iterator to iterate through the Employee Database
    EmployeeDatabaseIterator it = this.iterator();
    //while there is still another employee in the database
    while(it.hasNext()){
      Employee em = it.next();
      //check if the employee matches the input first name, last name, and number
      if( em.getLastName().equals(lastName) && em.getFirstName().equals(firstName) && em.getNumber().equals(number) ){
        //if yes, return the employee
    	return em;
      }
    }
    //by the time we get here we know the Employee Database does not have an employee with the input first name, last name, or number, so it throws a NoSuchEmployee exception
      throw new NoSuchEmployeeException();
  }
 /**
  * returns the total amount earned by all the employees in the database (the payroll amount)
  * @return the total amount earned by all the employees in the database (the payroll amount)
  */
  public double getPayrollAmount(){
	  //creates an iterator to iterate through the Employee Database
    EmployeeDatabaseIterator it = this.iterator();
    //stores the amount payroll has to meet
    double payrollAmt = 0;
    //while there is still an employee that needs to be paid 
    while(it.hasNext()){
    	//stores the current employee 
      Employee e = it.next();
      //adds the amount Employee e earned to the payroll amount
      payrollAmt = payrollAmt + e.getAmountEarned();
    }
    //returns the payroll amount
    return payrollAmt;
  }
  
  /**
   * returns the employee that earns the least in the database
   * @return the employee that earns the least in the database
   */
  public Employee getMinimumEarned(){
    
	  //stores the earnings of the employee with the smallest income
    double minEarned = Double.MAX_VALUE;
    
    //stores the employee making the least income
    Employee underAppreciated = null;
   
    //for each employee in the Employee Database..
    for(Employee e: this){
    	//if the amount the employee earns is less than the minEarned
      if(e.getAmountEarned()<minEarned){
    	  //set minEarned to what the employee makes
        minEarned = e.getAmountEarned();
        //set the employee making the least income to the employee
        underAppreciated = e;
      }
    }
    
    return underAppreciated;
  }
  
  /**
   * returns the employee in the database that earns the mose
   * @return the employee in the database that earns the most
   */
  public Employee getMaximumEarned() {
	//stores the earnings of the employee with the largest income
	  double maxEarned = Double.MIN_VALUE;
	  //stores the employee with the greatest income
	  Employee overAppreciated = null;
	  //for each employee in the Employee Database...
	  for(Employee e: this){
		  //if the amount the employee earns is greater than maxEarned...
		  if(e.getAmountEarned()>maxEarned){
			  //set maxEarned the earnings of the employee
	        maxEarned = e.getAmountEarned();
	        //set the employee making the most income to employee e
	        overAppreciated = e;
	      }
	  }
	    return overAppreciated;
  }
  /**
   * returns the employee in the database working the least number of hours
   * @return the employee in the database working the least number of hours
   */
  public Employee getMinHoursWorked(){
	//returns the hours of the employee that worked the least hours
    double minHours = Double.MAX_VALUE;
    //stores the employee that worked the least hours
    Employee lazyButt = null;
    //for each employee in the database...
    for(Employee e: this){
    	//if the hours worked by the current employee are less than minHours...
    	if(e instanceof HourlyEmployee) {
    		if(((HourlyEmployee)e).getHoursWorked()<minHours){
    			//set the employee working the least hours to employee e
    			lazyButt = e;
    			//set the minHours to the hours employee e worked
    			minHours = ((HourlyEmployee)e).getHoursWorked();
    		}
    	}
    }
    
    try {
    	lazyButt.getSalary();
    }
    catch(NullPointerException e) {
    	throw new NoSuchEmployeeException();
    }
    return lazyButt;
  }
  
  /**
   * returns the employee in the database that worked the most hours
   * @return the employee in the database that worked the most hours
   */
  public Employee getMaxHoursWorked(){
	  //stores the hours of the employee that worked the most hours
    double maxHours = Double.MIN_VALUE;
    //stores the employee that worked the most hours
    Employee workerBee = null;
    //for each employee in the employee database...
    for(Employee e: this){
    	if(e instanceof HourlyEmployee) {
    	//if the current employee works more hours than what is stores in maxHours
    		if(((HourlyEmployee)e).getHoursWorked()>maxHours){
    			//set the employee that works the most hours to employee e
    			workerBee = e;
    			//set maxHours to the hours employee e works
    			maxHours = ((HourlyEmployee)e).getHoursWorked();
    		}
    	}
      }
    try {
    	workerBee.getSalary();
    }
    catch(NullPointerException e) {
    	throw new NoSuchEmployeeException();
    }
    return workerBee;
    }

  
  /**
   * returns the Sales Employee that completed the most sales
   * @return the Sales Employee that completed the most sales
   */
  public SalesEmployee getMaxSales(){
	  //stores the sales employee that made the most sales
    SalesEmployee seller = null;
    //stores the sales of the most successful sales employee
    int sales = Integer.MIN_VALUE;
    
    //for each employee in the database...
    for(Employee e: this){
    	//if the employee is a SalesEmployee...
      if(e instanceof SalesEmployee){
    	  //and if the number of sales completed is greater than what is stored in sales
        if(((SalesEmployee)e).getNumSales()>sales){
        	//set the Sales Employee who made the most sales to employee e
          seller = (SalesEmployee)e;
          //set the sales to the number of sales employee e made
          sales = ((SalesEmployee) e).getNumSales();
        }
      }
    }
    try{
       seller.getNumSales();
    }
    catch(NullPointerException e){
      throw new NoSuchEmployeeException();
    }
    
    return seller;
  }
  
  /**
   * returns the Sales Employee in the Employee Database that completed the least number of sales
   * @return the Sales Employee in the Employee Database that completed the least number of sales
   */
  public SalesEmployee getMinSales(){
	//stores the sales employee that made the least sales
    SalesEmployee seller = null;
  //stores the sales of the least successful sales employee
    int sales = Integer.MAX_VALUE;
  
    //for each employee in the database...
    for(Employee e: this){
    	//if the employee is a SalesEmployee...
      if(e instanceof SalesEmployee){
    	//and if the number of sales completed is less than what is stored in sales
        if( ((SalesEmployee) e).getNumSales()<sales) {
        	//set the Sales Employee who made the least sales to employee e
        	seller = (SalesEmployee)e;
        	//set the sales to the number of sales employee e made
        	sales = ((SalesEmployee) e).getNumSales();
        }
      }
    }
    
    try{
       seller.getNumSales();
    }
    catch(NullPointerException e){
      throw new NoSuchEmployeeException();
    }
    
    return seller;
  }
  
  /**
   * returns an array of employees who have the input supervisor
   * @param supervisor the employees returned in the employee array will all have this supervisor
   * @return an array of employees that have the input supervisor  
   */
  public Employee[] getSupervisees(Supervisor supervisor){
	  //stores the number of employees with the Supervisor supervisor
	  int numSupervisees = 0;
	  //for each employee in teh database...
	  for(Employee e: this) {
		  //if the employee has the supervisor
		  if(e.getSupervisor() == supervisor.toString()) {
			  //increase the count of the supervisees by 1
			  numSupervisees++;
		  }
	  }
	  //stores the array of all the employees with the input supervisor
	  Employee[] supervisees = new Employee[numSupervisees];
	  //represents the current index we're on in the Employee array supervisees
	  int index = 0;
	  //creates an iterator to iterate through the database
	  EmployeeDatabaseIterator it = this.iterator();
	  //while there are still employees in the database...
	  while(it.hasNext()) {
		  //stores the current employee we're on
		  Employee e = it.next();
		  //if that employee has the Supervisor supervisor...
		  if(e.getSupervisor()==supervisor.toString()) {
			  //add it to the array of supervisees
			  supervisees[index] = e;
			  //add one to the index
			  index++;
		  }
	  }
	  
	  return supervisees;
	  
  }
  /**
   * Overrides the iterator method of Iterable to return an EmployeeDatabaseIterator
   * @return a new EmployeeDatabaseIterator
   */
  public EmployeeDatabaseIterator iterator(){
    return new EmployeeDatabaseIterator(this);
  }
  
  
  public static void main(String[] args){
    
	  EmployeeDatabase database = new EmployeeDatabase();
  //Salaired Employee Ashley Sah
  	SalariedEmployee e1 = new SalariedEmployee("Ashley", "Sah", "ajs511", 600000);
  	//Hourly Employee Alison Berry
  	HourlyEmployee e2 = new HourlyEmployee("Alison", "Berry", "asb235", 600);
  	//Sales Employee Harold Connamacher
  	SalesEmployee e3 = new SalesEmployee("Harold", "Connamacher", "hsc21", 0, 0);
  	//Salaried Supervisor Brigitte Parejas
  	SalariedSupervisor e4 = new SalariedSupervisor("Brigitte", "Parejas", "blp32", 500000);
  	//Hourly Supervisor Ritika Deverakonda
  	HourlySupervisor e5 = new HourlySupervisor("Ritika", "Deverakonda", "rxd427", 500);
  	//Sales Supervisor Sirini Vanam
  	SalesSupervisor e6 = new SalesSupervisor("Sirini", "Vanam", "sxv354", 600000, 600);
  	SalesSupervisor e7 = new SalesSupervisor("Pranav", "Dhinikar", "pxd222", 600000, 600);
  	SalesSupervisor e8 = new SalesSupervisor("Ally", "Kally", "axk444", 600000, 600);
  	
  	database.add(e8);
  	database.add(e7);
  	database.add(e6);
	database.add(e5);
	database.add(e4);
	database.add(e3);
	database.add(e2);
	database.add(e1);
    
    
    LinkedList.printList(database);
    System.out.println(" ");
    
    database.remove("Harold", "Connamacher", "hsc21");
    LinkedList.printList(database);
    
    //System.out.println(" ");
    //System.out.println( database.getFirstNode().getNext().getElement());
    //System.out.println( database.getFirstNode().getNext().getNext().getElement());

    
    /*results of remove test:
     * sucessfully removes Ashley (last element of list)
     * removes Pranav when it's supposed to remove Sirini (first element of the list)
     * removes Ashley when it's supposed to remove Pranav (middle element of the list)
     * if element does not exist it throws a NoSuchEmployeeException
     * 
    System.out.println(" ");
    
    double payrollAmt = database.getPayrollAmount();
    System.out.println("Payroll amt:" + payrollAmt);//should be 12000
    
    System.out.println(" ");
    
    Employee e = database.getMinimumEarned();
    System.out.println(e);
    
    System.out.println(" ");
    
    e = database.getMaximumEarned();
    System.out.println(e);
    
    System.out.println(" ");
    
    System.out.println("Find method tests:");
    e = database.find("Ashley", "Sah", "ajs511");
    System.out.println(e);
    e = database.find("Pranav", "Dhinikar", "pxd222");
    System.out.println(e);
    e = database.find("Sirini", "Vanam", "sxv666");
    System.out.println(e);
    
    System.out.println(" ");
    */
    
  }
  /**
   * This class is an iterator for Employee Database
   * @author Ashley Sah
   *
   */
  public static class EmployeeDatabaseIterator extends LinkedListIterator<Employee>{
	//stores the EmployeeDatabase the iterator will iterate thorugh
    private EmployeeDatabase database = new EmployeeDatabase();
    //stores the 'current' node we're looking at
    private LLNode<Employee> nextNode;
    //stores the node that comes before the current node
    private LLNode<Employee> currentNode;
    
    /**
     * Initalizes the EmployeeDatabaseIterator with an Employee Database
     * @param database the Employee Database the iterator iterates through
     */
    public EmployeeDatabaseIterator(EmployeeDatabase database){
      super(database);
      this.database = database;
      nextNode = this.database.getFirstNode();
      currentNode = this.database.getFirstNode();
    }
    
    @Override
    /**
     * Overides the next() method to return the Employee in the current node 
     * It also sets the current node to the next node, and the previous node the current node
     */
    public Employee next(){
      currentNode = nextNode;
      nextNode = nextNode.getNext();
      return currentNode.getElement();
    }
    
    @Override
    /**
     * checks if there is still values in the database to iterate through
     */
    public boolean hasNext(){
      return nextNode != null;
    }
    /**
     * returns the current node
     * @return the current node
     */
    public LLNode<Employee> getNextNode(){
      return nextNode;
    }
    
    @Override
    /**
     * removes the current node from the database
     * overrides the Iterator remove method
     */
    public void remove(){ 
        int index = 0;
        LLNode<Employee> previousNode = database.getFirstNode();
       
        //if we're removing the first node of the database...
        if(currentNode == previousNode){
        	//just remove from the front
          database.removeFromFront();
          
        }
        //if we're removing a middle node of the database
        else{
          //find the index of currentNode
          while(previousNode != nextNode){
        	previousNode = previousNode.getNext();
            index++;
          }
          //set the nodeptr to the very first node
          previousNode = database.getFirstNode();
          
          //sets nodeptr to the node just before the previousNode
          for(int i = 0; i<index; i++){
        	  previousNode = previousNode.getNext();
          }
          //set the node before previous node to point to the node after previous node (effectively removing it from the database) 
          previousNode.setNext( previousNode.getNext().getNext() );
        }
    }
    
    
  }
  
}
