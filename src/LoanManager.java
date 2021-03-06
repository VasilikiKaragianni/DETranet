/**
 * LoanManager
 * 
 */
package gr.aueb.dmst.DETranet;
import java.util.Date;
import java.util.InputMismatchException;

/* Class for loan Manager */
public class LoanManager extends Employee {

  public LoanManager(String fullname, 
      String department, String email, double salary, Date firstDate,
      int leaves, String password, double overall) {
    super(fullname, department, email, 
        salary, firstDate, leaves, password, overall);
  }

  /**
 * Method for select employees of loan department. 
 * 
 */
  public void selectDepartmentForSetGoals() {
    boolean endOfProcedure = true;
    do {
      boolean flag = true;
      int select = 0;
      do {
        try {
          System.out.printf("\nGoals sharing for loans." + "\n1.Business Sevice Manager"
              + "\n2.Business Customer Manager" + "\n3.Business Customer Manager Vip"
              + "\n4.Business Customer Manager Delays" + "\n5.Εnd of procedure\n");
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
          System.out.printf("Business Service Manager Goals:\n");
          setGoals("Business Service Manager Goals");
          break;
        case 2:
          System.out.printf("Busineess Customer Manager Goals:\n");
          setGoals("Busineess Customer Manager Goals");
          break;
        case 3:
          System.out.printf("Business Customer Manager Vip Goals:\n");
          setGoals("Business Customer Manager Vip Goals");
          break;
        case 4:
          System.out.printf("Business Customer Manager Delays Goals:\n");
          setGoals("Business Customer Manager Delays Goals");
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
* Method compute bonus for loan manager based on the average overall of the
* employees of the loan department
*/
  @Override
  public double computeBonus() {
    int nmbrOfEmployees = 0;
    double sumOverall = 0;
    double rate = 0;
    double bonus = 0;
    /* Find loan employees and sum overalls */
    for (int i = 0; i < employees.size(); i++) {
      Employee emp = employees.get(i);
      if (emp.getDepartment().equals("Business Service Manager") 
          || emp.getDepartment().equals("Business Customer Manager")
          || emp.getDepartment().equals("Business Customer Manager Vip")
          || emp.getDepartment().equals("Business Customer Manager Delays")) {
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
  * Add rate of loan manager.
  */
  public void evaluationLoanManager() {
    double rate = (computeBonus() / 3);
    for (int j = 0; j < employees.size(); j++) {
      Employee empLM = employees.get(j);
      if (empLM.getDepartment().equals("Loan Manager")) {
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

  /* Display the menu for loan manager */
  @Override
  public void getMenu() {
    for (;;) {
      int select = 0;
      boolean flag = true;
      do {
        try {
          System.out.printf("\nWelcome to the Loan Manager menu!" + "\n1.Display department goals"
              + "\n2.Goals sharing" + "\n3.Leaves" 
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
          goals("Loan Manager Goals");
          break;
        case 2:
          selectDepartmentForSetGoals();
          break;
        case 3:
          leaves();
          break;
        case 4:
          evaluationLoanManager();
          System.out.println("Goals achievement rate for loan manager: "
              + (computeBonus() / 3) + "%" + "\nBonus: "
              + computeBonus() + "$" + "\nFinal salary: " + finalSalary() + "$");
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