package detranet;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
// class for manager of bank
public class Manager extends Employee {
	
	private static String depositManagerGoals;
	private static String loanManagerGoals;

	public static ArrayList <Integer> managersOverall= new ArrayList<Integer>();
	private static String goalsLoanManager;
	private static String goalsDepositManager;
	
	//10-argument constructor
	public Manager(String fullname, int idEmployee, String department, String email, double salary, Date firstDate,
				   int leaves, String username, String password, double overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
	}
	
	//  method for filling arrayLists idEmployees, overalls
	 public void evaluation() {
		Scanner sc = new Scanner(System.in);
		int ID = -1;
		double OVERALL = -1.0;
		boolean loop = true;
		do {
			try {
				System.out.println("Please enter the id of employee you want to evaluate: ");
				ID=sc.nextInt();
				loop=false;
			}
			catch (InputMismatchException ex) {
				System.err.println("exception "+ ex);
				sc.nextLine();
				System.out.println("Please insert an integer number! ");
			}
		}while(loop);
		do {
			try {
				System.out.println("Please enter the overall of employee: ");
				OVERALL = sc.nextDouble();
			}
			catch (InputMismatchException ex) {
				System.err.println("exception "+ ex);
				sc.nextLine();
				System.out.println("Please insert a double number! ");
			}
		}while(loop);
		idEmployees.add(ID);
		overalls.add(OVERALL);							
	}// end of method evaluations
	
	 // method for removing redundants from lists
	public void dismissals() {
		Scanner sc = new Scanner(System.in);
		int answear2 = -1;
		do {
			boolean loop = true;
			do {
				try{
					System.out.println("Please press the id of employee you want to make redundant: ");
					answear2 = sc.nextInt();
					if (answear2<0 || answear2>employees.size())
						System.out.println("Please insert an integer number between 0 and "+ (employees.size()-1) + ".");
					loop = false;
				}
				catch (InputMismatchException ex3) {
						System.err.println("exception "+ ex3);
						sc.nextLine();
						System.out.println("Please insert an integer number! ");
					}
				}while(loop);
			}while(answear2 < 0 || answear2 > employees.size());
		int k = 0;
		int l = -1;
		int w = -1;
		boolean loop = true;
		while(loop) {
			int empMn = idEmployees.get(k);
			if(answear2 == empMn) {
				l = idEmployees.indexOf(answear2);
				w = employees.indexOf(answear2);
				loop = false;
			}
			k++;
		}							
		employees.remove(w);
		employees2.remove(w);
		idEmployees.remove(l);
		overalls.remove(l);
	}// end of method
	
	// method for sorting lists idEmployees and overalls based on overalls of employees 
	public void sorting() {
		for(int i = 1; i < employees.size(); i++) {
			for(int j = employees.size() ; j > i; j-- ) {
				if(overalls.get(j) > overalls.get(j-1)) {
					double temp = overalls.get(j);
					overalls.add(j,overalls.get(j-1));
					overalls.add((j-1),temp);
					int temp1 = idEmployees.get(j);
					idEmployees.add(j,idEmployees.get(j-1));
					idEmployees.add((j-1),temp1);
				}
			}
		}
	}//end of method
	
	

	
	//method for computing bonus of Manager based on average of overalls of loan and deposit manager
	@Override
	public double computeBonus() {
		double rate;
		double bonus = 0;
		int num = 0;
		double sum = 0;
		int i = 0;
		while (num < 2){
			Employee man = employees.get(i);
			if (man.getDepartment() == "Loan Manager"
			|| man.getDepartment() == "Deposit Manager") {
				int idCurrEmployee=man.getIdEmployee();
				num += 1;
				for (int j=0; j<idEmployees.size(); j++) {
					if (idCurrEmployee == idEmployees.get(j)) {
						//fills the arrayList overalls 
						sum += overalls.get(j);
					}
				}
			}
		i++;
		}
		rate = (sum / num);
		if (rate > 75)
			bonus = rate * 3;
		return bonus;
	}// end of method
	
	public double finalSalary(double salary) {
		return salary + computeBonus();
	}
	//method for filling the arrayList overalls on position of manager
	public void evaluationManager() {
		double rate;
		rate = (computeBonus()/ 3);
		int j = 0;
		int num = 0;
		while(num < 1) {
			Employee empM = employees.get(j);
			if(empM.getDepartment() == "Manager") {
				empM.setOverall(rate);
				idEmployees.add(empM.getIdEmployee());
				overalls.add(rate);
				num++;
			}
			j++;
		}
	}// end of method
	
	// method for printing the menu
	@Override
	public void getMenu(){
		System.out.println("Welcome to the Manager Menu!");
		Scanner sc = new Scanner(System.in);
		int answear=0;
		do {
			boolean loop=true;
			do {
				try {
					System.out.println("Please press 1 if you want to manage employees. \n"
									+  "Please press 2 if you want to set goals. \n"
									+  "Please press 3 if you want to see the goals. \n"
									+  "Please press 4 if you want to apply for a leave or to see the remaining number of leaves. \n"
									+  "Please press 5 if you want to compute bonuses. \n"
									+  "Please press 6 if you want to see the news. \n"
									+  "Please press 7 if you want to log out. \n ");
					answear=sc.nextInt();
					if (answear<1 || answear>7)
						System.out.println("Please insert either 1 or 2 or 3 or 4 or 5 or 6 or 7: ");
					loop=false;
				}
				catch (InputMismatchException ex) {
					System.err.println("exception "+ ex);
					sc.nextLine();
					System.out.println("Please insert an integer number! ");
				}
			}while(loop);
		}while(answear<1 || answear>7);
		int ans = 0;
		boolean loop1 = true;
		while(loop1) {
			switch (answear) {
				case 1:
					do {
						boolean loop=true;
						do {
							try {	
								System.out.println("Please press 1 if you want to evaluate. \n"
											    +  "Please press 2 if you want to make redudnants. \n"
											    +  "Please press 3 if you want to see efficiencies. \n"
											    +  "Please press 4 if you want to return to menu.");
								ans=sc.nextInt();
								if (ans<1 || ans>3)
									System.out.println("Please insert either 1 or 2 or 3 or 4: ");
								loop=false;
							}
							catch (InputMismatchException ex2) {
								System.err.println("exception "+ ex2);
								sc.nextLine();
								System.out.println("Please insert an integer number! ");
							}
						}while(loop);
					}while(ans<1 || ans>4);
					switch (ans) {
						case 1:
							evaluation();							
							break;
						case 2:
							dismissals();
							break;
						case 3:
							sorting();
							for(int i=0; i < idEmployees.size(); i++) {
								System.out.println(idEmployees.get(i) + "" + overalls.get(i));			
							}
							break;
						case 4:
							getMenu();
							break;
						}
						break;
					
				case 2:
					Scanner in = new Scanner(System.in);
					System.out.println("set goals for:  ");
					int select=0;
					do {
						boolean loop=true;
						do {
							try {
								System.out.println("Please press 1 if you want to set goals for deposit Manager \n"
												+  "Please press 2 if you want to set goals for loan Manager. \n");
								select=in.nextInt();
								if (select<1 || select>2)
									System.out.println("Please insert either 1 or 2 ");
								loop=false;
							}
							catch (InputMismatchException ex) {
								System.err.println("exception "+ ex);
								in.nextLine();
								System.out.println("Please insert an integer number! ");
							}
						}while(loop);
					}while(select<1 || select>2);
					switch(select) {
						case 1:
							setGoals("depositManager");
							break;
						case 2:
							setGoals("loanManager");
							break;
					}
					getMenu();
					break;
				case 3:
					Scanner scanner = new Scanner(System.in);
					System.out.println("set goals for:  ");
					int selection=0;
					do {
						boolean loop=true;
						do {
							try {
								System.out.println("Please press 1 if you want to see goals for deposit Manager \n"
												+  "Please press 2 if you want to see goals for loan Manager. \n");
								selection=scanner.nextInt();
								if (selection < 1 || selection > 2)
									System.out.println("Please insert either 1 or 2 ");
								loop=false;
							}
							catch (InputMismatchException ex) {
								System.err.println("exception "+ ex);
								scanner.nextLine();
								System.out.println("Please insert an integer number! ");
							}
						}while(loop);
					}while(selection<1 ||selection>2);
					switch(selection) {
					case 1:
						goals("depositManager");
						break;
					case 2:
						goals("loanManager");
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
				case 7:
					Main.main(null);
					break;
			}
		}
	}// end of method
	

}//end of class
