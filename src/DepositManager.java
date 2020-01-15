/**
* DepositManager
* 
*/
package gr.aueb.dmst.DETranet;
import java.util.Date;
import java.util.InputMismatchException;

/* Class for deposit Manager */
public class DepositManager extends Employee {

  public DepositManager(String fullname, 
      String department, String email, double salary,
      Date firstDate, int leaves, String password, double overall) {
    super(fullname, department, email, salary, 
        firstDate, leaves, password, overall);
  }

  /**
  * Method for selecting employees of deposit department.
  *  
  */
  public void selectDepartmentForSetGoals() {
    boolean endOfProcedure = true;
    do {
      boolean flag = true;
      int select = 0;
      do {
        try {
          System.out.printf("\nGoals sharing for depositors." 
              + "\n1.Private Customer Manager." + "\n2.Teller"
              + "\n3.Private Customer Manager Vip" + "\n4.Private Customer Manager Delays"
              + "\n5.Εnd of procedure\n");
          select = Main.sc.nextInt();
          if (select > 0 && select < 6) {
            flag = false;
          } else {
            System.out.printf("Please insert an integer between 1-5.Try again...");
          }
        } catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n", inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("Please insert an integer between 1-5.Try again...");
        }
      } while (flag);
      /* create or open and write a new file for chosen employee */
      switch (select) {
        case 1:
          System.out.printf("Private Customer Manager Goals:\n");
          setGoals("Private Customer Manager Goals");
          break;
        case 2:
          System.out.printf("Teller Goals:\n");
          setGoals("Teller Goals");
          break;
        case 3:
          System.out.printf("Private Customer Manager Vip Goals:\n");
          setGoals("Private Customer Μanager Vip Goals");
          break;
        case 4:
          System.out.printf("Private Customer Manager Delays Goals:\n");
          setGoals("Private Customer Manager Delays Goals");
          break;
        case 5:
          endOfProcedure = false;
          break;
        default:
          break;
      }
    } while (endOfProcedure);
  }

  /*
  * Method compute bonus for deposit manager based on the average overall of the
  * employees of the deposit department
  */
  @Override
  public double computeBonus() {
    int nmbrOfEmployees = 0;
    double sumOverall = 0;
    double rate = 0;
    double bonus = 0;
    /* Find deposit employees and sum overalls */
    for (int i = 0; i < employees.size(); i++) {
      Employee emp = employees.get(i);
      if (emp.getDepartment().equals("Private Customer Manager") || emp.getDepartment().equals("Teller")
          || emp.getDepartment().equals("Private Customer Manager Vip")
          || emp.getDepartment().equals("Private Customer Manager Delays")) {
        int idCurrEmployee = emp.getIdEmployee();
        nmbrOfEmployees += 1;
        for (int j = 0; j < idEmployees.size(); j++) { 
          /* The positions of employees are not the same in lists employees and idEmployees */
          if (idCurrEmployee == idEmployees.get(j)) { 
            sumOverall += overalls.get(j);
            break;
          }
        }
      }
    }
    rate = sumOverall / nmbrOfEmployees;
    if (rate > 75) {
      bonus = rate * 3;
    }
    return bonus;
  }

  /**
 * Add rate of deposit manager.
 * 
 */
  public void evaluationDepositManager() {
    double rate = (computeBonus() / 3);
    for (int j = 0; j < employees.size(); j++) {
      Employee empLM = employees.get(j);
      if (empLM.getDepartment().equals("Deposit Manager")) {
        empLM.setOverall(rate);
        Database.adjustOverall(empLM.getIdEmployee(), rate);
        idEmployees.add(empLM.getIdEmployee());
        overalls.add(rate);
        break;
      }
    }
  }

  public double finalSalary() {
	double finalsal = (this.getSalary() + computeBonus());
	Database.computeSalary(this.getIdEmployee(),finalsal);
	return finalsal;
  }

  /* Display the menu for deposit manager */
  @Override
  public void getMenu() {
    for (;;) {
      int select = 0;
      boolean flag = true;
      do {
        try {
          System.out.printf("\nWelcome to the Deposit Manager menu!"
                  + "\n1.Display department goals" + "\n2.Goals sharing" + "\n3.Leaves" 
                  + "\n4.Compute BONUS" + "\n5.News" + "\n6.Log Out\n");
          select = Main.sc.nextInt();
          if (select > 0 && select < 7) {
            flag = false;
          } else {
            System.out.printf("Please insert an integer between 1-6.Try again...");
          }
        } catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n", inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("Please insert an integer between 1-6.Try again...");
        }
      } while (flag);
      switch (select) {
        case 1:
          goals("Deposit Manager Goals");
          break;
        case 2:
          selectDepartmentForSetGoals();
          break;
        case 3:
          leaves();
          break;
        case 4:
          evaluationDepositManager();
          System.out.println("Goals achievment rate for deposit manager: " 
              + (computeBonus() / 3) + "%" + "\nBonus: " + computeBonus() + "$" 
              + "\nFinal Salary: " + finalSalary() + "$");
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