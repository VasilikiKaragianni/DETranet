/** 
 * Employee
 */

package gr.aueb.dmst.DETranet;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
* This abstract class is responsible for the whole of employees.
* There are some methods such as getNews, setGoals, Goals, leaves,
* reloadLeaves, logIn which are inherited. In addition, there are
* the abstract methods getMenu and computeBonus. 
*/
public abstract class Employee {

  private String fullname;
  public static int count = 1;
  private int idEmployee;
  private String department;
  private String email;
  private double salary;
  private Date firstDate;
  private int leaves;
  private String password;
  private double overall;

  /* The lists employees and employees2 are parallel.
* The lists idEmployees and overalls are parallel.
*  this list contains the user name and the password of each employee as one String. 
*/
  public static ArrayList<Employee> employees = new ArrayList<Employee>();
  public static ArrayList<String> employees2 = new ArrayList<String>();
  public static ArrayList<Integer> idEmployees = new ArrayList<Integer>();
  public static ArrayList<Double> overalls = new ArrayList<Double>();

 /**
*  10-argument constructor. 
*/
  public Employee(String fullname, int idEmployee, String department, String email,double salary,
          Date firstDate,int leaves, String password, double overall) {
    super();
    this.fullname = fullname;
    this.idEmployee = count++;
    this.department = department;
    this.email = email;
    this.salary = salary;
    this.firstDate = firstDate;
    this.leaves = leaves;
    this.password = password;
    this.overall = overall;
    // The list is filled with all arguments.
    employees.add(this);
    // This list is filled with user name and password as one String.
    employees2.add(email + password);
    // This list is filled with auto-increasing id of Employee.
    idEmployees.add(idEmployee);
    // This list is filled with overall Employee.
    overalls.add(overall); 
  } // end 5-argument constructor.

  //return list employees.
  public static ArrayList<Employee> getEmployees() {
    return employees;
  } // end method getEmployees.

  //return list idEmployees.
  public static ArrayList<Integer> getIdEmployees() {
    return idEmployees;
  } // end method getIdEmployees.

  //return list overalls.
  public static ArrayList<Double> getOveralls() {
    return overalls;
  } // end method getOveralls.

  // return full name of Employee.
  public String getFullname() {
    return fullname;
  } // end method getFullanme.

  // set full name of Employee.
  public void setFullname(String fullname) {
    this.fullname = fullname;
  } // end method setFullanme.

  // return id of Employee.
  public int getIdEmployee() {
    return idEmployee;
  } // end method getIdEmployee.

  // set id of Employee
  public void setIdEmployee(int idEmployee) {
    this.idEmployee = idEmployee;
  } // end method setIdEmployee.

  // return department of Employee.
  public String getDepartment() {
    return department;
  } // end method getDepartment.

  // set department of Employee.
  public void setDepartment(String department) {
    this.department = department;
  } // end method setDepartment.

  // return email of Employee.
  public String getEmail() {
    return email;
  } // end method getEmail.

  // set email of Employee.
  public void setEmail(String email) {
    this.email = email;
  } // end method setEmail.

  // return salary of Employee.
  public double getSalary() {
    return salary;
  } // end method getSalary.

  // set salary of Employee.
  public void setSalary(double salary) {
    this.salary = salary;
  } // end method setSalary.

  // return hire date of Employee.
  public Date getFirstDate() {
    return firstDate;
  } // end method getFirstDate.

  // set hire date of Employee.
  public void setFirstDate(Date firstDate) {
    this.firstDate = firstDate;
  } // end method setFirstDate.

  // return number of days of leave of Employee.
  public int getLeaves() {
    return leaves;
  } // end method getLeaves.

  // set number of days of leave of Employee.
  public void setLeaves(int leaves) {
    this.leaves = leaves;
  } // end method setLeaves.

  // return password of Employee.
  public String getPassword() {
    return password;
  } // end method getPassword.

  // set password of Employee.
  public void setPassword(String password) {
    this.password = password;
  } // end method setPassword.

  // return overall of Employee.
  public double getOverall() {
    return overall;
  } // end method getOverall.

  // set overall of Employee.
  public void setOverall(double overall) {
    this.overall = overall;
  } // end method setOverall.

  public abstract double computeBonus();
  public abstract void getMenu();

  /**
 * This method connects the program with urls from Internet.
 */
  public static void getNews() { 
    Desktop d = Desktop.getDesktop();
    try {
      System.out.println("Please choose a site: \n1. Forbes, \n2. Bloomberg,"
      		+ "\n3. Financial Times, \n4. CNN Business,\n5. Reuters ");
      int i = Main.sc.nextInt();
      if (i == 1 ) {
    	  d.browse(new URI("https://www.forbes.com"));
      } else if (i == 2) {
          d.browse(new URI("https://www.bloomberg.com/europe"));
      } else if (i==3) {
    	  d.browse(new URI("https://www.ft.com"));
      } else if (i==4) {
    	  d.browse(new URI("https://money.cnn.com/data/markets"));
      } else if (i==5) {
    	  d.browse(new URI("https://www.reuters.com"));
      } else {
    	  System.out.println("You didn't choose a site");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  /**
* This method is responsible for writing goals in txt files. 
*/
  public void setGoals(String goals) {
    File file = new File(goals + ".txt");
    int select = 0;
  
    boolean flag = true;
    /* select for adding more goals or writing goals in an empty file */
    do {
      try {
        System.out.print("1.Add goals"
            + "\n2.Set new goals\n");
        select = Main.sc.nextInt();
        if (select == 1 || select == 2) {
          flag = false;
        } else {
          System.out.printf("Please insert 1 or 2.Try again...");
        }
      } catch (InputMismatchException inputmismatchexception) {
        System.err.printf("%nException%n: %s%n", inputmismatchexception);
        Main.sc.nextLine();
        System.out.printf("Please insert an integer between 1-2.Try again...");
      }
    } while (flag);
    boolean newFile = true;
    if (select == 2) {
      newFile = false;
    }
    try {
      FileWriter out = new FileWriter(file, newFile);
      boolean addGoal = true;
      do {
        System.out.print("Add goal: ");
        String goal = "";
        goal = Main.sc.next() + ""; 
        try {
          out.write(goal);
          out.write(System.getProperty("line.separator")); /* go to next line */
        } catch (IOException e) {
          e.printStackTrace();
        }
        String ans="";
        do {
          System.out.printf("End of procedure? (Y/N)");
          ans = Main.sc.next();
          ans = ans.toUpperCase();
          if (!ans.equals("Y")  && !ans.equals("N")) {
        	  System.out.println("Please press only 'y' or 'n'!");
          }
        } while (!ans.equals("Y")  && !ans.equals("N"));
        if (ans.equals("Y")) {
          addGoal = false;
        }
      } while (addGoal == true);
      out.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  /**
* This method is for seeing the goals which are written
* by Manager or Loan Manager or Deposit Manager.
*/
  public void goals(String goal) {
    String filename = goal + ".txt";
    String path = filename;
    File file = new File(path);
    try {
      Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
          String data = sc.nextLine();
          System.out.println(data);
        }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("No goals exist!");
    }
  }

  /**
* This method is for seeing the remaining leave days or
* applying for leave.  
*/
  public void leaves() {
    int choice = 0;
    do {
      boolean loop = true;
      do {
        try {
          System.out.println("Please press 1 if you want to see the remaining leave days.\n "
                                     + "Please press 2 if you want to apply for leave. \n"
                                     + "Please press 3 if you want to return to menu.");
          choice = Main.sc.nextInt();
          if (choice < 1 || choice > 3) {
            System.out.println("Please insert either 1 or 2 or 3: ");
          }
          loop = false;
        } catch (InputMismatchException ex) {
          System.err.println("exception " + ex);
          Main.sc.nextLine();
          System.out.println("Please insert an integer number! ");
        }
      } while (loop);
    } while (choice < 1 || choice > 3);
    if (choice == 1) {
      System.out.println("You have " + this.getLeaves() + " days of leave!");
    }
    if (choice == 2) {
      boolean loop = true;
      while (loop) {
        int days = 0;
        if (this.getLeaves() > 0) {
          boolean flag = true;
          do {
            try {
              System.out.print("Please insert the number of days of leave you want to take: ");
              days = Main.sc.nextInt();
              flag = false;
            } catch (InputMismatchException ex) {
              System.err.println("exception " + ex);
              Main.sc.nextLine();
              System.out.println("Please insert an integer number! ");
            }
          } while (flag);
          if (days > this.getLeaves()) {
            System.out.println("You have just " + this.getLeaves() + " days of leave!");
            int ans = 0;
            do {
              boolean cloop = true;
              do {
                try {
                  System.out.println("Please press 4 if you want to apply for leave again"
                                           + " or press 5 if you want to return to menu.");
                  ans = Main.sc.nextInt();
                  if (ans != 4 && ans != 5) {
                    System.out.println("Please insert either 4 or 5: ");
                  }
                  cloop = false;
                } catch (InputMismatchException ex) {
                  System.err.println("exception " + ex);
                  Main.sc.nextLine();
                  System.out.println("Please insert an integer number! ");
                }
              } while (cloop);
            } while (ans < 4 || ans > 5);
            if (ans == 4) {
              System.out.print("Please insert the number of days of leave you want to take: ");
            } else {
              getMenu();
            }
          } else {
            this.setLeaves(this.getLeaves() - days);
            int k = this.getLeaves();
            Database.updateLeaves(this.getIdEmployee(), k);
            System.out.println("Your request has been accepted! \n"
                                   + "remaining days of leave: " + this.getLeaves());
            loop = false;
            getMenu();
          }
        } else {
          System.out.println("Yoy have not remaining days of leave!");
          loop = false;
          getMenu();
        }
      }
    }
    if (choice == 3) {
      getMenu();
    }
  }

  /**
* This method is for reloading the days of leaves every year. 
*/
  public static void reloadLeaves() {
    Calendar cal = Calendar.getInstance();
    if ((cal.get(Calendar.MONTH) == 0) && (cal.get(Calendar.DAY_OF_MONTH) == 4)) {
      for (Employee emp : employees) {
        emp.setLeaves(31);
        Database.updateLeaves(emp.getIdEmployee(), 31);
      }
    }
  }

  /**
* This method is for login of employees. 
*/
  public static Employee logIn(String email, String password) {
    if (employees2.contains(email + password)) {
      return employees.get(employees2.indexOf(email + password));
    } else {
      return null;
    }
  }
}