package gr.aueb.dmst.DETranet;
/*This class is about one of the Business's employees 
 * and more specifically the one who deals mostly with the transaction processing.
 * He/She can see the list of customers,delete an old customer or add a new one.
 * He/She can also see the goals that he/she must accomplish, the bonus that he/she deserves, 
 * the remaining days of leaves or ask for a new leave and the news of the bank */

import java.util.Date;
import java.util.InputMismatchException;

/*Business Service Manager*/
public class BusinessServiceManager extends Employee {

  /**.
 * Constructor of the class*/
  public BusinessServiceManager(String fullname, 
      String department, String email, double salary,
      Date firstDate, int leaves, String password, double overall) {
    super(fullname, department, email, salary, 
        firstDate, leaves, password, overall);
        // TODO Auto-generated constructor stub
  }

    
  /** This method deletes an already existing customer based on the id.
* that the Business Service Manager gives
*/
  public void removeCust(int id) {
		boolean b = false;
		if (id < 0) System.out.println("The id you gave is not valid");
	    for(int i = 0 ; i < Business.bSM.size(); i++) {
	      int k = Business.bSM.get(i).getIdBusiness();
	      if (k == id) {
	    	Business.bSM.remove(Business.bSM.get(i));
	        Database.deleteBusinessCust(id);
	        System.out.println("The customer was deleted succesfuly");
	        b = true;
	        break;
	      }
	    }  
	    if (b == false ) {
	    	System.out.println("there is not such id");
	    }
	  }

  /**This method displays the menu to the employee.
* who has 3 options to choose between*/
  public void employeeList() {
    int select = 0;
    boolean value = true;
    do {
      try {
        System.out.println("Menu"
            + "\n1.Add customer"
            + "\n2.Delete customer"
            + "\n3.View list of customers");
        select = Main.sc.nextInt();
        if (select > 0 && select < 4) {
          value = false;
        } else {
          System.out.printf("You did't insert an integer between 1 and 3.Please try again..");
        }
      } catch (InputMismatchException inputmismatchexception) {
        System.err.printf("%nException%n: %s%n", inputmismatchexception);
        Main.sc.nextLine();
        System.out.printf("You did't insert an integer between 1 and 3.Please try again..");
      }
    } while (value);
    switch (select) {
      case 1:
        try {  
          System.out.println("Give your new customer's name");
          String name = Main.sc.next();
          Main.sc.nextLine();
          System.out.println("Give the type of his/hers business");
          String type = Main.sc.nextLine();
          System.out.println("Give the amount of his/hers depositions");
          double amount = Main.sc.nextDouble();
          System.out.println("Give the number of his/hers loans");
          int nmbrLoans = Main.sc.nextInt();
          Business newCust = new Business(name,type,amount,nmbrLoans);
          newCust.setName(name);
          newCust.setType(type);
          newCust.setAmount(amount);
          newCust.setNmbrLoans(nmbrLoans);
          newCust.addBusiness(1);
          Database.createBusinessCust(name, type, newCust.getIdBusiness(), amount, nmbrLoans, this.getIdEmployee());
          System.out.println("Your new customer was added succesfully");
        } catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n",inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("Please insert the right type of data.Try again...");
        }
        break;

      case 2:
        System.out.println("Type the customer's id you would like to delete");
        int id2 = Main.sc.nextInt();
        removeCust(id2);
        break;
      case 3:
    	int size = Business.bSM.size();
      	if(size == 0) {
      		System.out.println("Customers list: empty ");
      	}
          for (int i = 0; i < size ; i++) {
            Business b = Business.bSM.get(i);
            System.out.println(b.toString());
          }
          break;
      default:
        break;
    }
  }
 
  /*This method is inherited from Employee class and returns the bonus 
* that the manager has accomplished to take
*/
  @Override
public double computeBonus() {
    double rate = this.getOverall();
    return rate * 3;
  }
 
  /*This method shows all the functions that a Business Service Manager
* can have
*/
  @Override
public void getMenu() {
    for (;;) {  
      boolean value = true;
      int select = 0;
      do {
        try {
          System.out.println("Menu"
              + "\n1.Customer management"
              + "\n2.Display of goals"
              + "\n3.Bonus"
              + "\n4.Permission for absence"
              + "\n5.Display of bank's news"
              + "\n6.Log Out");
          select = Main.sc.nextInt();
          if (select > 0 && select < 7) {
            value = false;
          } else {
            System.out.printf("You did't insert an integer between 1 and 6.Please try again..");
          }
        } catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n", inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("You didn't insert an integer between 1 and 6.Please try again..");
        }
      } while (value);
      switch (select) {
        case 1:
          employeeList();
          break;
        case 2:
          goals("Business Service Manager Goals");
          break;
        case 3:
          System.out.println(computeBonus());
          break;
        case 4:
          leaves();
          break;
        case 5:
          Employee.getNews();
          break;
        case 6:
          Main.main(null);
          break;
        default:
          break;
      }
    }
  }
}