package detranet;

/*This class is about one of the Business's employees and more specifically the one that manages the business's VIP customers.
 * He/She can see the list of customers,delete an old customer or add a new one.
 * He/She can also see the goals that he/she must accomplish, the bonus that he/she deserves, 
 * the remaining days of leaves or ask for a new leave, the news of the bank and he/she can manages any complaints 
 * given by the VIPcustomers*/

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BusinessCustomerManagerVIP extends Employee {


	public BusinessCustomerManagerVIP(String fullname, int idEmployee, String department, String email, double salary,
			String firstDate, int leaves, String username, String password, Double overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
		// TODO Auto-generated constructor stub
	}

	public void removeCust(int id) {
		@SuppressWarnings("unlikely-arg-type")
		int index = Business.cSMGold.indexOf(id);
		if (index == -1) {
			System.out.println("The id you gave is not valid");
		}else {
			Business.cSMGold.remove(index);
			System.out.println("The customer was deleted succesfuly");
		}
	}

	public void employeeList() {
		int select=0;
		boolean value=true;
		Scanner sc= new Scanner(System.in);
		do {
			try {
			System.out.println("Menu"
				+ "\n1.Add customer"
				+ "\n2.Delete customer"
				+ "\n3.View list of customers");
	        select = sc.nextInt();

	        if (select>0 && select<3) {
			    value=false;
		    } else {
			    System.out.printf("You did't insert an integer between 1 and 3.Please try again");
			}
		}
	       catch (InputMismatchException inputmismatchexception) {
		      System.err.printf("%nException%n: %s%n" , inputmismatchexception);
		      sc.nextLine();
		      System.out.printf("You did't insert an integer between 1 and 3.Please try again");

	       }
		}while(value);

		switch (select) {
		case 1:
			System.out.println("Give your new customer's name");
			String name = sc.nextLine();
			System.out.println("Give the type of his/hers business");
			String type = sc.nextLine();
			System.out.println("Give your new customer's id");
			int businessId = sc.nextInt();
			System.out.println("Give the amount of his/hers depositions");
			double amount = sc.nextDouble();
			System.out.println("Give the number of his/hers loans");
			int nmbrLoans = sc.nextInt();
			Business newCust = new Business(name,type,businessId,amount,nmbrLoans);
			newCust.setName(name);
			newCust.setType(type);
			newCust.setAmount(amount);
			newCust.setNmbrLoans(nmbrLoans);
			newCust.addBusiness(3);
			System.out.println("Your new customer was added succesfully");

		case 2:
			System.out.println("Type the customer's id you would like to delete");
			int id2= sc.nextInt();
			removeCust(id2);

		case 3:
			for(int i=0; i<=Business.cSMGold.size(); i++)
				toString();
			}
		sc.close();
	}

	public void leaves() {
		Scanner sc = new Scanner(System.in);
		int select=0;
		boolean value=true;
		do {
			try {
				System.out.println("1.Display of days left for absence"
						+ "\n2.Permission for absence");
			select=sc.nextInt();
			if(select==1 ||select==2) {
				value=false;
			}else {
				System.out.println("You didn't enter 1 or 2.Please try again");
				}
			}
			catch (InputMismatchException inputmismatchexception) {
				System.err.printf("%nException%n: %s%n" , inputmismatchexception);
				sc.nextLine();
				System.out.println("You didn't enter 1 or 2.Please try again");
			}

		}while(value);
		int days=0;
		if(select==1) {
			System.out.println("Remaining days of absence: " + this.getLeaves() + "!");
		}else if (select==2) {
			if (this.getLeaves()>0) {
				boolean newRequest=true;
				do {
					value=true;
					do {
						try {
								System.out.printf("\nNumber of days needed: ");
								days = sc.nextInt();
								value=false;
						}catch (InputMismatchException inputmismatchexception) {
							System.err.printf("%nException%n: %s%n" , inputmismatchexception);
							sc.nextLine();
							System.out.printf("Please enter an integer.Try again...");
						}
					}while(value);
					if (days>this.getLeaves()) {
						System.out.println("\nI am sorry but you only have " + this.getLeaves() + " remaining days of absence!");
						String ans;
						do {
							System.out.println("\nDo you want to apply again?;(Y/N)");
							ans=sc.next();
							ans=ans.toUpperCase();
						}while(!ans.equals("Y")  && !ans.equals("N"));
						if(ans.equals("N")) newRequest=false;
					}else {
						newRequest=false;
						this.setLeaves(this.getLeaves()-days);
						System.out.println("?our request has been accepted!"
								+ "\nRemaining days of leave: " + this.getLeaves());
					}
				}while (newRequest);
			}else {
				System.out.println("\nThere are not remaining days of leave!");
			}
		}
		getMenu();
		sc.close();
	}

	public void ReadComplaints() {
		String Filename= "Complains.csv";
		File file = new File(Filename);
		try {
			Scanner sc=new Scanner(file);
			while (sc.hasNext()) {
				String data=sc.nextLine();
				System.out.println(data);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void goals() {
		Scanner sc = new Scanner(System.in);
		if (LoanManager.getcSMGoldGoals()==null) {
			System.out.println("No available goals!");
		}else {
			System.out.println("Department goals:\n" + LoanManager.getcSMGoldGoals());
		}
		sc.close();
		getMenu();	
	}
	
	@Override
	public double computeBonus() {
		double rate = this.getOverall() ;
		return rate * 3;
	}




	@Override
	public void getMenu() {
		boolean value=true;
		int select=0;
		Scanner sc=new Scanner(System.in);
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
				select=sc.nextInt();
				if (select>0 && select<7) {
					value=false;
				}else {
					System.out.printf("You did't insert an integer between 1 and 7.Please try again");
					}
				}
			catch (InputMismatchException inputmismatchexception) {
				System.err.printf("%nException%n: %s%n" , inputmismatchexception);
				sc.nextLine();
				System.out.printf("You did't insert an integer between 1 and 7.Please try again");
			}
		} while (value);
				switch (select) {
				case 1:
					employeeList();
					break;
				case 2:
					goals();
				case 3:
					computeBonus();
				case 4:
					leaves();
				case 5:
					Employee.getNews();
				case 6:
					ReadComplaints();
				case 7:
					Main.main(null);
		}
				sc.close();
	}

	}