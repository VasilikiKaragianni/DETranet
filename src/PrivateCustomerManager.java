package gr.aueb.dmst.DETranet;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is for the Manager who is responsible for the Private Customers of the Bank
 * It includes a Menu that Manager chooses what actions want 
 * to make and by this way Manager can control his customers,his leaves and his bonuses.
 *
 * 
 */

public class PrivateCustomerManager extends Employee {

  /**
 * This is the constructor of the class.
 */
  public PrivateCustomerManager(String fullname, String department,
      String email, double salary,Date firstDate, int leaves, String password,
      double overall) {
    super(fullname, department, email, salary, firstDate, leaves,
        password, overall);
  }

  /**
 * This method removes the private clients from the list.
 */
  public void removePrivate(int id) {
    boolean b = false;
    if (id < 0) {
      System.out.println("The id you gave is not valid");
    }
    for (int i = 0;i < Private.pCM.size();i++) {
      int k = Private.pCM.get(i).getIdPrivate();
      if (k == id) {
        Private.pCM.remove(Private.pCM.get(i));
        Database.deletePrivateCust(id);
        System.out.println("The customer was deleted succesfuly");
        b = true;
        break;
      }
    }
    if (b == false) {
      System.out.println("there is not such id");
    }
  }

  /**
 * This method is inherited by the class Employee and it returns 
 * the bonus that the Private Customer Manager will gain.
 */
  @Override
  public double computeBonus() {
    double rate = this.getOverall();
    return rate * 3;
  } 

  /**
 * This method is responsible for showing the menu for the Customer's management 
 * and it has 3 options: addition, delete and display of customers.
 */
  public void custMenu() {
    boolean flag = true;
    int option = 0;
    do {
      try {
        System.out.println("Menu"
            + "\n1.Addition of Customer"
            + "\n2.Delete of Customer"
            + "\n3.Display of Customer");
        option = Main.sc.nextInt();
        if (option > 0 && option <= 3) {
          flag = false;
        } else {
          System.out.printf("Please insert an integer between 1-3.Try again...");
        }
      } catch (InputMismatchException inputmismatchexception) {
        System.err.printf("%nException%n: %s%n",inputmismatchexception);
        Main.sc.nextLine();
        System.out.printf("Please insert an integer between 1-3.Try again...");
      }
    } while (flag);
    switch (option) {
      case 1:
        try {
          System.out.println("Give customer's name");
          String name = Main.sc.next();
          System.out.println("Give customer's amount of deposit");
          double amount = Main.sc.nextDouble();
          System.out.println("Give customer's number of cards");
          int cards = Main.sc.nextInt();
          System.out.println("Give customer's number of loans");
          int loans = Main.sc.nextInt();
          Private p = new Private(name,amount,cards,loans);
          p.setName(name);
          p.setAmount(amount);
          p.setCards(cards);
          p.setNmbrLoans(loans);
          p.addPrivate(1);
          Database.createPrivateCust(name, p.getIdPrivate(), amount, cards, loans, this.getIdEmployee());
        } catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n",inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("Please insert the right type of data.Try again...");
        }
        break;
      case 2:
        System.out.println("Give customer's id that you want to delete");
        int delid = Main.sc.nextInt();
        removePrivate(delid);
        break;
      case 3:
        int size = Private.pCM.size();
        if (size == 0) {
          System.out.println("Customers list: empty ");
        }
        for (int i = 0; i < size; i++) {
          Private p = Private.pCM.get(i);
          System.out.println(p.toString());
        }
        break;
      default:
        break;
    }
  }
  
  /**
 * This method is responsible for opening and reading a csv file which includes the complains
 *  that the customers may have.
 */
  public void readComplains(String location) {
    String filename =  "Complains.csv";
    String path = location + filename;
    File file = new File(path);
    try {
      Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
        String data = sc.nextLine();
        System.out.println(data);
      }
      sc.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
    getMenu();
  }

  /**
 *This method includes the main menu which shows all the functions that Private Customer Manager 
 *can have.
 */
  @Override
  public void getMenu() {
    for (;;) {
      boolean flag = true;
      int option = 0;
      do {
        try {
          System.out.println("Menu"
              + "\n1.Customers' management"
              + "\n2.Goals'display"
              + "\n3.Bonus"
              + "\n4.Leaves"
              + "\n5.Display of the bank's news"
              + "\n6.Complaints'management"
              + "\n7.Log Out");
          option = Main.sc.nextInt();
          if (option > 0 && option <= 7) {
            flag = false;
          } else {
            System.out.printf("Please insert an integer between 1-7.Try again...");
          }
        } catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n", inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("Please insert an integer between 1-7.Try again...");
        }
      } while (flag);
      switch (option) {
        case 1:
          custMenu();
          break;
        case 2:
          goals("Private Customer Manager Goals");
          break;
        case 3:
          System.out.println(computeBonus());
          break;
        case 4:
          leaves();
          break;
        case 5:
          getNews();
          break;
        case 6:
          System.out.println("Give file's path");
          String path = Main.sc.next();
          readComplains(path);
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