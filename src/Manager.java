/**
 * Manager
 * 
 */

package gr.aueb.dmst.DETranet;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

/**
 *  This class is for Manager of bank. He is responsible for setting goals
 *  to the Loan Manager and Deposit Manager. Moreover, he is able to evaluate 
 *  the whole of employees and to make redundants.
 */

public class Manager extends Employee {

  public static ArrayList<Integer> managersOverall = new ArrayList<Integer>();

  //10-argument constructor
  public Manager(String fullname, int idEmployee, String department, String email, double salary,
          Date firstDate, int leaves, String password, double overall) {
    super(fullname, idEmployee, department, email, salary,
            firstDate, leaves, password, overall);
  }
 
  /**
 * This method is for filling arrayLists idEmployees, overalls and
 * evaluating the employees.
 */
  public void evaluation() {
    int id = -1;
    double overall = -1.0;
    boolean loops = true;
    while (loops) {
      do {
        boolean flag = true;
        do {
          try {
            System.out.println("Please enter the id of employee you want to evaluate: ");
            id = Main.sc.nextInt();
            if (id < 0 || id > (idEmployees.size() - 1)) {
              System.out.println("Please insert an integer number which matches with an ID.");
            }
            flag = false;
          } catch (InputMismatchException ex) {
            System.err.println("exception " + ex);
            Main.sc.nextLine();
            System.out.println("Please insert an integer number! ");
          }
        } while (flag);
      } while (id < 0 || id > (employees.size() - 1));
      int p = 0;
      boolean cloop = true;
      int count = 0;
      while ((cloop == true) && (p <= idEmployees.size() - 1)) {
        if (idEmployees.get(p) == id) {
          count++;
          cloop = false;
        }
        p++;
      }
      if (count == 0) {
        System.out.println("This ID:" + id + " does not exist!");
      } else {
        loops = false;
      }
    }
    do {
      boolean loop = true;
      do {
        try {
          System.out.println("Please enter the overall of employee: ");
          overall = Main.sc.nextDouble();
          if (overall < 0 || overall > 100) {
            System.out.println("Please insert an integer number between 0 and 100.");
          }
          loop = false;
        } catch (InputMismatchException ex) {
          System.err.println("exception " + ex);
          Main.sc.nextLine();
          System.out.println("Please insert a double number! ");
        }
      } while (loop);
    } while (overall < 0 || overall > 100);
    int h = idEmployees.indexOf(id);
    idEmployees.set(h, id);
    overalls.set(h, overall);
    Database.adjustOverall(h, overall);
  } // end of method evaluations

  /**
 * This method is for making redundants removing the 
 * elements from lists.
 */
  public void dismissals() {
    int answear2 = -1;
    do {
      boolean loop = true;
      do {
        try {
          System.out.println("Please press the id of employee you want to make redundant: ");
          answear2 = Main.sc.nextInt();
          if (answear2 < 0 || answear2 > employees.size()) {
            System.out.println("Please insert an integer number between 0 and "
                + (employees.size() - 1) + ".");
          }
          loop = false;
        } catch (InputMismatchException ex3) {
          System.err.println("exception " + ex3);
          Main.sc.nextLine();
          System.out.println("Please insert an integer number! ");
        }
      } while (loop);
    } while (answear2 < 0 || answear2 > employees.size());
    int k = 0;
    int l = -1;
    int w = -1;
    boolean loop = true;
    boolean cloop = true;
    Employee mn;
    while (loop) {
      int empMn = idEmployees.get(k);
      if (answear2 == empMn) {
        l = idEmployees.indexOf(answear2);
        while (cloop) {
          mn = employees.get(k);
          if (mn.getIdEmployee() == l) {
            w = mn.getIdEmployee();
            cloop = false;
          }
          loop = false;
        }
      }
      k++;
    }
    employees.remove(w);
    employees2.remove(w);
    idEmployees.remove(l);
    overalls.remove(l);
    Database.deleteEmployee(w);
    System.out.println("Successful removal!");
  } // end of method

  /**
* This method is for sorting lists idEmployees and overalls
* based on overalls of employees.
*  
*/
  public void sorting() {
    for (int i = 0; i < employees.size(); i++) {
      for (int j = (employees.size() - 1); j > i; j--) {
        if (overalls.get(j) > overalls.get(j - 1)) {
          double temp = overalls.get(j);
          overalls.set(j, overalls.get(j - 1));
          overalls.set((j - 1), temp);
          int temp1 = idEmployees.get(j);
          idEmployees.set(j, idEmployees.get(j - 1));
          idEmployees.set((j - 1), temp1);
        }
      }
    }
    for (int i = 0; i < idEmployees.size(); i++) {
      System.out.println("The overall of employee with id "
          + idEmployees.get(i) + " is:" + overalls.get(i));
    }
  } //end of method

  //method for computing bonus of Manager based on average of overalls of loan and deposit manager
  @Override
  public double computeBonus() {
    double rate;
    double bonus = 0;
    int num = 0;
    double sum = 0;
    int i = 0;
    while (num < 2) {
      Employee man = employees.get(i);
      if (man.getDepartment() == "Loan manager"
           || man.getDepartment() == "Deposit manager") {
        int idCurrEmployee = man.getIdEmployee();
        num += 1;
        for (int j = 0; j < idEmployees.size(); j++) {
          if (idCurrEmployee == idEmployees.get(j)) {
            sum += overalls.get(j);
          }
        }
      }
      i++;
    }
    rate = (sum / num);
    if (rate > 75) {
      bonus = rate * 3;
    }
    return bonus;
  }  // end of method

  public double finalSalary(double salary) {
    return salary + computeBonus();
  }
 
  /**
 * This method is responsible for evaluating Manager
 * and filling the arrayList overalls on position of Manager. 
 */
  public void evaluationManager() {
    double rate;
    rate = (computeBonus() / 3);
    int j = 0;
    int num = 0;
    while (num < 1) {
      Employee empM = employees.get(j);
      if (empM.getDepartment() == "Manager") {
        empM.setOverall(rate);
        idEmployees.add(empM.getIdEmployee());
        overalls.add(rate);
        num++;
      }
      j++;
    }
  } // end of method

  // method for printing the menu
  @Override
  public void getMenu() {
    System.out.println("Welcome to the Manager Menu!");
    int answear = 0;
    do {
      boolean loop = true;
      do {
        try {
          System.out.println("Please press 1 if you want to manage employees. \n"
                           +  "Please press 2 if you want to set goals. \n"
                           +  "Please press 3 if you want to see the goals. \n"
                           +  "Please press 4 if you want to apply for a leave or to see "
                           +  "the remaining number of leaves. \n"
                           +  "Please press 5 if you want to compute bonuses. \n"
                           +  "Please press 6 if you want to see the news. \n"
                           +  "Please press 7 if you want to log out. \n ");
          answear = Main.sc.nextInt();
          if (answear < 1 || answear > 7) {
            System.out.println("Please insert either 1 or 2 or 3 or 4 or 5 or 6 or 7: ");
          }
          loop = false;
        } catch (InputMismatchException ex) {
          System.err.println("exception " + ex);
          Main.sc.nextLine();
          System.out.println("Please insert an integer number! ");
        }
      } while (loop);
    } while (answear < 1 || answear > 7);
    int ans = 0;
    boolean loop1 = true;
    while (loop1) {
      switch (answear) {
        case 1:
          do {
            boolean loop = true;
            do {
              try {
                System.out.println("Please press 1 if you want to evaluate. \n"
                                +  "Please press 2 if you want to make redudnants. \n"
                                +  "Please press 3 if you want to see efficiencies. \n"
                                +  "Please press 4 if you want to return to menu.");
                ans = Main.sc.nextInt();
                if (ans < 1 || ans > 3) {
                  System.out.println("Please insert either 1 or 2 or 3 or 4: ");
                }
                loop = false;
              } catch (InputMismatchException ex2) {
                System.err.println("exception " + ex2);
                Main.sc.nextLine();
                System.out.println("Please insert an integer number! ");
              }
            } while (loop);
          } while (ans < 1 || ans > 4);
          switch (ans) {
            case 1:
              evaluation();
              break;
            case 2:
              dismissals();
              break;
            case 3:
              sorting();
              break;
            default:
              getMenu();
              break;
          }
          break;
        case 2:
          System.out.println("set goals for:  ");
          int select = 0;
          do {
            boolean loop = true;
            do {
              try {
                System.out.println("Please press 1 if you want to set goals for deposit Manager \n"
                                +  "Please press 2 if you want to set goals for loan Manager. \n"
                                +  "Please press 3 if you want to return to menu. \n");
                select = Main.sc.nextInt();
                if (select < 1 || select > 3) {
                  System.out.println("Please insert either 1 or 2 or 3 ");
                }
                loop = false;
              } catch (InputMismatchException ex) {
                System.err.println("exception " + ex);
                Main.sc.nextLine();
                System.out.println("Please insert an integer number! ");
              }
            } while (loop);
          } while (select < 1 || select > 3);
          switch (select) {
            case 1:
              setGoals("Deposit manager goals");
              break;
            case 2:
              setGoals("Loan manager goals");
              break;
            default:
              getMenu();
              break;
          }
          getMenu();
          break;
        case 3:
          System.out.println("set goals for:  ");
          int selection = 0;
          do {
            boolean loop = true;
            do {
              try {
                System.out.println("Please press 1 if you want to see goals for deposit Manager \n"
                                +  "Please press 2 if you want to see goals for loan Manager. \n"
                                +  "Please press 3 if you want to return to menu. \n");
                selection = Main.sc.nextInt();
                if (selection < 1 || selection > 3) {
                  System.out.println("Please insert either 1 or 2 or 3");
                }
                loop = false;
              } catch (InputMismatchException ex) {
                System.err.println("exception " + ex);
                Main.sc.nextLine();
                System.out.println("Please insert an integer number! ");
              }
            } while (loop);
          } while (selection < 1 || selection > 3);
          switch (selection) {
            case 1:
              goals("Deposit manager goals");
              break;
            case 2:
              goals("Loan manager goals");
              break;
            default:
              getMenu();
              break;
          }
          getMenu();
          break;
        case 4:
          leaves();
          break;
        case 5:
          evaluationManager();
          System.out.println("The bonus is: " + computeBonus());
          System.out.println("The final salary is: " + finalSalary(getSalary()));
          getMenu();
          break;
        case 6:
          getNews();
          getMenu();
          break;
        default:
          Main.main(null);
          break;
      }
    }
  }  // end of method
} //end of class