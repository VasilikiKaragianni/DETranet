/*
 * PrivateCustomerManagerVip
 */
package detranet;

import java.util.Scanner;

import java.util.InputMismatchException;

/**
 * This class is for the Manager who is responsible for the Private Customers of the Bank who are Vip,which means that they have some more benefits that the other clients.
 * It includes a Menu that Manager chooses what actions want to make and by this way Manager can control his customers,his leaves and his bonuses.
 *
 * 
 */

public class PrivateCustomerManagerVip extends Employee {
	
/**
 * This is the constructor of the class.
 * @param fullname
 * @param idEmployee
 * @param department
 * @param email
 * @param salary
 * @param firstDate
 * @param leaves
 * @param username
 * @param password
 * @param overall
 */
		public PrivateCustomerManagerVip(String fullname, int idEmployee, String department, String email, double salary,
			String firstDate, int leaves, String username, String password, double overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
		}

/*
* This method removes the private Vip clients from the list.
*/
		public void removePrivate(int id) {
			@SuppressWarnings("unlikely-arg-type")
			int index=Private.pCMDelays.indexOf(id);
			if (index== -1) {
				System.out.println("The id you gave is not valid");
			} else {
				Private.pCMGold.remove(index);
				System.out.println("The customer is deleted successfully");
			}
		}

/*
 * This method is inherited from the class Employee and shows the goals that the Private Customer Manager of Vip clients has.
 * 
 */
		@Override
		public void goals() {
			Scanner sc = new Scanner(System.in);
			if (DepositManager.getpCMGoldGoals()==null) {
				System.out.println("No available goals!");
			}else {
				System.out.println("Department goals:\n" + DepositManager.getpCMGoldGoals());
			}
			sc.close();
			getMenu();	
		}
		
/* 
 * This method is inherited by the class Employee and it returns the bonus that the Private Customer Manager of Vip clients will gain.
 */
		@Override
		public double computeBonus() {
			double rate = this.getOverall() ;
			return rate * 3;
		}
		
/*
 * This method is responsible for showing the menu for the Customer's management and it has 3 options: addition, delete and display of customers.	
 */		
		public void custMenu() {
			boolean flag=true;
			Scanner sc= new Scanner (System.in);
			int option=0;
			do {
				try {
					System.out.println("Menu"
							+ "\n1.Addition of Customer"
							+ "\n2.Delete of Customer"
							+ "\n3.Display of Customer");
					option=sc.nextInt();
					if (option>0 && option<3) {
						flag=false;
					}else {
						System.out.printf("Please insert an integer between 1-3.Try again...");
						}
					}
				catch (InputMismatchException inputmismatchexception) {
					System.err.printf("%nException%n: %s%n" , inputmismatchexception);
					sc.nextLine();
					System.out.printf("Please insert an integer between 1-3.Try again...");
				}
			}while (flag);
					switch(option) {
					case 1:
						System.out.println("Give customer's name");
						String name= sc.nextLine();
						System.out.println("Give customer's id");
						int id = sc.nextInt();
						System.out.println("Give customer's amount of deposit");
						double amount = sc.nextDouble();
						System.out.println("Give customer's number of cars");
						int cards = sc.nextInt();
						System.out.println("Give customer's number of loans");
						int loans = sc.nextInt();
						Private p=new Private(name,id,amount,cards,loans);
						p.setName(name);
						p.setAmount(amount);
						p.setCards(cards);
						p.setNmbrLoans(loans);
						p.addPrivate(1);
						
					case 2:
						System.out.println("Give customer's id that you want to delete");
						int delid = sc.nextInt();
						removePrivate(delid);
					case 3:
						for(int i=0; i<=Private.pCMGold.size(); i++)
						toString();
					}
					sc.close();
		}
		
	
/*
 *This method includes the main menu which shows all the functions that Private Customer Manager of Vip clients can have.
 */		
		@Override
		public void getMenu() {
			boolean flag=true;
			int option=0;
			Scanner sc=new Scanner(System.in);
			do {
				try {
					System.out.println("Menu"
							+ "\n1.Customers' management"
							+ "\n2.Goals'display"
							+ "\n3.Bonus"
							+ "\n4.Leaves"
							+ "\n5.Display of the bank's news"
							+ "\n6.Log Out");
					option=sc.nextInt();
					if (option>0 && option<=6) {
						flag=false;
					}else {
						System.out.printf("Please insert an integer between 1-6.Try again...");
						}
					}
				catch (InputMismatchException inputmismatchexception) {
					System.err.printf("%nException%n: %s%n" , inputmismatchexception);
					sc.nextLine();
					System.out.printf("Please insert an integer between 1-6.Try again...");
				}
			} while (flag);
					switch (option) {
					case 1:
						custMenu();
					case 2:
						goals();
					case 3:
						computeBonus();
					case 4:
						leaves();
					case 5:
						PrivateCustomerManager.getNews();
					case 6:
						Main.main(null);
						
			}
		sc.close();
		}
}
