/**.
 * Business Customer Manager Vip
 */

/*This class is about one of the Business's employees 
 * and more specifically the one that manages the business's VIP customers.
 * He/She can see the list of customers,delete an old customer or add a new one.
 * He/She can also see the goals that he/she must accomplish, the bonus that he/she deserves, 
 * the remaining days of leaves or ask for a new leave, the news of the bank 
 * and he/she can manages any complaints 
 * given by the VIPcustomers*/
package gr.aueb.dmst.DETranet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BusinessCustomerManagerVip extends Employee {

  /**.
 * Constructor of class
 **/
  public BusinessCustomerManagerVip(String fullname,  
      String department, String email, double salary,
      Date firstDate, int leaves, String password, double overall) {
    super(fullname, department, email, salary, 
        firstDate, leaves, password, overall);
        // TODO Auto-generated constructor stub
  }

  /** This method deletes an already existing customer based on the id.
 * that the Business Customer Manager of Vip clients gives
 **/
  
  public void removeCust(int id) {
    boolean b = false;
    if (id < 0) {
      System.out.println("The id you gave is not valid");
    }
    for (int i = 0; i < Business.cSMVip.size(); i++) {
      int k = Business.cSMVip.get(i).getIdBusiness();
      if (k == id) {
        Business.cSMVip.remove(Business.cSMVip.get(i));
        Database.deleteBusinessCust(id);
        System.out.println("The customer was deleted succesfuly");
        b = true;
        break;
      }
    }  
    if (b == false) {
      System.out.println("there is not such id");
    }
  }
  

  /**This method displays the menu to the employee.
* who has 3 options to choose between**/
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
          newCust.addBusiness(3);
          Database.createBusinessCust(name, type, newCust.getIdBusiness(), amount, nmbrLoans, this.getIdEmployee() + 2);
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
        int size = Business.cSMVip.size();
        if (size == 0) {
          System.out.println("Customers list: empty ");
        }
        for (int i = 0; i < size; i++) {
          Business b = Business.cSMVip.get(i);
          System.out.println(b.toString());
        }
        break;
      default:
        break;
    }
  }

  /** This method reads a CSV file.
* which contains some complaints from the Vip clients*/
  public void ReadComplaints(String location) {
    String Filename = "Complains.csv";
    String path = location + Filename;
    File file = new File(path);
    try {
      Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
        String data = sc.nextLine();
        System.out.println(data);
      }
      sc.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    getMenu();
  }

  /*This method is inherited from Employee class and returns the bonus 
 * that the manager has accomplished to take
 */
  @Override
public double computeBonus() {
    double rate = this.getOverall();
    return rate * 3;
  }


  /*This method shows all the functions that a Business Customer Manager of Vip clients
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
              + "\n6.Management of complaints"
              + "\n7.Log Out");
          select = Main.sc.nextInt();
          if (select > 0 && select < 8) {
            value = false;
          } else {
            System.out.printf("You didn't insert an integer between 1 and 7.Please try again...");
          }
        }  catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n", inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("You did't insert an integer between 1 and 7.Please try again");
        }  
      } while (value);
      switch (select) {
        case 1:
          employeeList();
          break;
        case 2:
          goals("Business Customer Manager Vip Goals");
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
          System.out.println("Give file's path");
          String path = Main.sc.next();
          ReadComplaints(path);
          break;
        case 7:
          Main.main(null);
          break;
        default:
          break;
      } 
    }
  }
}