package detranet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DepositManager extends Employee {
	
	private static String pCMGoals;
	private static String tellerGoals;
	private static String pCMGoldGoals;
	private static String pCMDelaysGoals;
	
	public DepositManager(String fullname, int idEmployee, String department, String email, double salary,
			String firstDate, int leaves, String username, String password, double overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
	}

	@Override
	public void goals() {
		Scanner sc = new Scanner(System.in);
		if (Manager.getDepositGoals()==null) {
			System.out.println("No available goals!");
		}else {
			System.out.println("Department goals:\n" + Manager.getDepositGoals());
		}
		String goBack = sc.next();
		getMenu();	
	}
	
	public void setGoals() {
		for(;;) {
			boolean flag=true;
			Scanner sc = new Scanner (System.in);
			int select=0;;
			do {
				try {
					System.out.println("\nGoals sharing for depositors."
							+ "\n1.Private costumer manager."
							+ "\n2.Teller"
							+ "\n3.Private costumer manager GOLD"
							+ "\n4.Private costumer manager delays"
							+ "\n5.Εnd of procedure");
					select=sc.nextInt();
					if (select>0 && select<6) {
						flag=false;
					}else {
						System.out.printf("Please insert an integer between 1-5.Try again...");
						}
					}
				catch (InputMismatchException inputmismatchexception) {
					System.err.printf("%nException%n: %s%n" , inputmismatchexception);
					sc.nextLine();
					System.out.printf("Please insert an integer between 1-5.Try again...");
				}
			}while (flag);
			
			switch (select) {
			case 1:
				pCMGoals = sc.nextLine();
				break;
			case 2:
				tellerGoals = sc.nextLine();
				break;
			case 3:
				pCMGoldGoals = sc.nextLine();
				break;
			case 4:
				pCMDelaysGoals = sc.nextLine();
				break;
			case 5:
				getMenu();
				break;
				}
			}
	}
	public static String getpCMGoals() {
		return pCMGoals;
	}
	public static String gettellerGoals() {
		return tellerGoals;
	}
	public static String getpCMGoldGoals() {
		return pCMGoldGoals;
	}
	public static String getpCMDelaysGoals() {
		return pCMDelaysGoals;
	}
	
	
	
	@Override
	public double computeBonus() {
		int nmbrOfEmployees=0;
		double sumOverall=0;
		double rate = 0;
		double bonus=0;
		for(int i=0; i<employees.size(); i++) {
			Employee emp = employees.get(i);
			if (emp.getDepartment()=="PrivateCostumerManager" 
					|| emp.getDepartment()=="Teller" 
					|| emp.getDepartment()=="PrivateCostumerManagerGold" 
					|| emp.getDepartment()=="PrivateCostumerManagerDelays") {
				nmbrOfEmployees =+ 1;
				sumOverall =+ overalls.get(i);
			}
		}	
		for(int j=0; j<employees.size(); j++) {
			Employee empDM = employees.get(j);
			if(empDM.getDepartment()=="DepositManager") {
				rate=sumOverall/nmbrOfEmployees;
				empDM.setOverall(rate);
				idEmployees.add(empDM.getIdEmployee());
				overalls.add(rate);
			}
		}	 
		if(rate>75) {
			bonus = rate * 3;
		}
		return bonus;
	}
	@Override
	public void leaves() {
		Scanner sc = new Scanner(System.in);
		int select=0;
		boolean flag=true;
		do {
			try {
				System.out.println("1.Display remaining leaves"
									+ "\n2.Request for leave");
			select=sc.nextInt();
			if(select==1 ||select==2) {
				flag=false;	
			}else {
				System.out.println("Please enter 1 for display or 2 for register.Try again...");
				}
			}
			catch (InputMismatchException inputmismatchexception) {
				System.err.printf("%nException%n: %s%n" , inputmismatchexception);
				sc.nextLine();
				System.out.println("Please enter 1 for display or 2 for register.Try again...");
			}
			
		}while(flag);
		int days=0;
		if(select==1) {
			System.out.println("Remaining days of leave: " + this.getLeaves() + "!");
		}else if (select==2) {
			if (this.getLeaves()>0) {
				boolean newRequest=true;
				do {
					flag=true;
					do {
						try {
								System.out.printf("\nNumber of days needed: ");
								days = sc.nextInt();
								flag=false;
						}catch (InputMismatchException inputmismatchexception) {
							System.err.printf("%nException%n: %s%n" , inputmismatchexception);
							sc.nextLine();
							System.out.printf("Please enter an integer.Try again...");
						}
					}while(flag);
					if (days>this.getLeaves()) {
						System.out.println("\nSorry.Only " + this.getLeaves() + " remaining days of leave!");
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
						System.out.println("Υour request has been accepted!"
								+ "\nRemaining days of leave: " + this.getLeaves());
					}
				}while (newRequest);
			}else {
				System.out.println("\nThere are not remaining days of leave!");
			}
		}
		String goBack = sc.next();
		getMenu();
	}
	
	@Override
	public void getMenu() {
		Scanner sc = new Scanner (System.in);
		int select=0;
		boolean flag=true;
		do {
			try  {
				System.out.println("\n Welcome to the Deposit Manager menu!"
						+ "\n1.Display department goals"
						+ "\n2.Goals sharing"
						+ "\n3.Leaves"
						+ "\n4.Compute BONUS"
						+ "\n5.News"
						+ "\n6.Log Out");
				select=sc.nextInt();
				if (select>0 && select<6) {
					flag=false;
				}else {
					System.out.printf("Please insert an integer between 1-5.Try again...");
					}
				}
			catch (InputMismatchException inputmismatchexception) {
				System.err.printf("%nException%n: %s%n" , inputmismatchexception);
				sc.nextLine();
				System.out.printf("Please insert an integer between 1-5.Try again...");
			}
		}while (flag);
		
		switch (select) {
		case 1:
			goals();
			break;
		case 2:
			setGoals();
			break;
		case 3:
			leaves();
			break;
		case 4:
			System.out.println("Goals achievment rate for depositors: " + this.getOverall() +
					"\nBonus: " + computeBonus() + "$");
			break;
		case 5:
			this.getNews();
			break;
		case 6:
			Main.main(null);
			break;
		}
	}
}
