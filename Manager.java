package detranet;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Manager extends Employee {
	
	public static ArrayList <Integer> managersOverall= new ArrayList<Integer>();
	private static String goalsLoanManager;
	private static String goalsDepositManager;
	
	public Manager(String fullname, int idEmployee, String department, String email, double salary, String firstDate,
				   int leaves, String username, String password, double overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
	}

	@Override
	public void getMenu(){
		System.out.println("Welcome to the Manager Menu!");
		Scanner sc=new Scanner(System.in);
		int answear=0;
		do {
			boolean loop=true;
			do {
				try {
					System.out.println("Please press 1 if you want to manage employees. \n"
									+  "Please press 2 if you want to set goals. \n"
									+  "Please press 3 if you want to see the goals. \n"
									+  "Please press 4 if you want to apply for a leave. \n"
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
		int ans=0;
		boolean loop1=true;
		while(loop1) {
			switch (answear) {
				case 1:
					do {
						boolean loop=true;
						do {
							try {	
								System.out.println("Please press 1 if you want to evaluate. \n"
											    +  "Please press 2 if you want to make redudnants. \n"
											    +  "Please press 3 if you want to see efficiencies. \n");
								ans=sc.nextInt();
								if (ans<1 || ans>3)
									System.out.println("Please insert either 1 or 2 or 3: ");
								loop=false;
							}
							catch (InputMismatchException ex2) {
								System.err.println("exception "+ ex2);
								sc.nextLine();
								System.out.println("Please insert an integer number! ");
							}
						}while(loop);
					}while(ans<1 || ans>3);
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
						}
						break;
					
				case 2:
					setGoals();
					break;
				case 3:
					goals();
					break;
				case 4:
					leaves();
					break;
				case 5:
					double rate;
					rate=computeBonus()/ 3;
					int j=0;
					int num=0;
					while(num<1) {
						Employee empM = employees.get(j);
						if(empM.getDepartment()=="Manager") {
							empM.setOverall(rate);
							idEmployees.add(empM.getIdEmployee());
							overalls.add(rate);
							num++;
						}
						j++;
					}
					computeBonus();
					break;
				case 6:
					getNews();
					break;
				case 7:
					Main.main(null);
					break;
			}
		}
	}

	@Override
	public void goals() {
		System.out.println("The goals for Loan Manager are: "+  goalsLoanManager + ". \n"
				         +"The goals for Depositor Manager are: " +  goalsDepositManager + ".");
	}
	
	public static String getLoanGoals() {
		return goalsLoanManager;
	}
	
	public static String getDepositGoals() {
		return goalsDepositManager;
	}


	public void setGoals() {
		Scanner sc=new Scanner(System.in);
		int select=0;
		do {
			boolean loop=true;
			do {
				try {	
					System.out.println("Please press 1 if you want to set goals for loan Manager. \n"
							 		 + "Please press 2 if you want to set goals gor deposit Manager.");
					select=sc.nextInt();
					if (select<1 || select>2)
						System.out.println("Please insert either 1 or 2: ");
					loop=false;
				}
				catch (InputMismatchException ex) {
					System.err.println("exception "+ ex);
					sc.nextLine();
					System.out.println("Please insert an integer number! ");
				}
			}while(loop);
		}while(select<1 || select>2);
		switch (select) {
		case 1:
			goalsLoanManager= sc.nextLine();
			break;
		case 2:
			goalsDepositManager= sc.nextLine();
			break;
		}
	}

	@Override
	public double computeBonus() {
		double rate;
		double bonus=0;
		int num=0;
		double sum=0;
		int i=0;
		while (num<2){
			Employee man=employees.get(i);
			if(man.getDepartment()=="Loan Manager"
			|| man.getDepartment()== "Depositor Manager") {
				num+=1;
				sum+=overalls.get(i);
			}
			i++;
		}
		rate=sum/num;
		if (rate>75)
			bonus=rate*3;
		return bonus;
	}
	
	public void sorting() {
		for(int i=1; i<employees.size(); i++) {
			for(int j=employees.size() ; j>i; j-- ) {
				if(overalls.get(j)>overalls.get(j-1)) {
					double temp=overalls.get(j);
					overalls.add(j,overalls.get(j-1));
					overalls.add((j-1),temp);
					int temp1=idEmployees.get(j);
					idEmployees.add(j,idEmployees.get(j-1));
					idEmployees.add((j-1),temp1);
				}
			}
		}
	}
	
	public void dismissals() {
		Scanner sc=new Scanner(System.in);
		int answear2=-1;
		do {
			boolean loop=true;
			do {
				try{
					System.out.println("Please press the id of employee you want to make redundant: ");
					answear2=sc.nextInt();
					if (answear2<0 || answear2>employees.size())
						System.out.println("Please insert an integer number between 0 and"+ (employees.size()-1) + ".");
					loop=false;
				}
				catch (InputMismatchException ex3) {
						System.err.println("exception "+ ex3);
						sc.nextLine();
						System.out.println("Please insert an integer number! ");
					}
				}while(loop);
			}while(answear2<0 || answear2>employees.size());
		int k=0;
		int l=-1;
		int w=-1;
		boolean loop= true;
		while(loop) {
			int empMn=idEmployees.get(k);
			if(answear2==empMn) {
				l=idEmployees.indexOf(answear2);
				w=employees.indexOf(answear2);
				loop=false;
			}
			k++;
		}							
		employees.remove(w);
		employees2.remove(w);
		idEmployees.remove(l);
		overalls.remove(l);
	}

	public void evaluation() {
		Scanner sc=new Scanner(System.in);
		int ID=-1;
		double OVERALL=-1.0;
		boolean loop=true;
		do {
			try {
				System.out.println("Please enter the id of employee you want to evaluate: ");
				ID=sc.nextInt();
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
				OVERALL=sc.nextDouble();
			}
			catch (InputMismatchException ex) {
				System.err.println("exception "+ ex);
				sc.nextLine();
				System.out.println("Please insert a double number! ");
			}
		}while(loop);
		idEmployees.add(ID);
		overalls.add(OVERALL);							
	}
	
	@Override
	public void leaves() {
			Scanner sc=new Scanner(System.in);
			int choice=0;
			do {
				boolean loop=true;
				do {
					try {	
						System.out.println("Please press 1 if you want to see the remaining leave days. \n "
						 		 		 + "Please press 2 if you want to aply for leave.");
						choice=sc.nextInt();
						if (choice<1 || choice>2)
							System.out.println("Please insert either 1 or 2: ");
						loop=false;
					}
					catch (InputMismatchException ex) {
						System.err.println("exception "+ ex);
						sc.nextLine();
						System.out.println("Please insert an integer number! ");
					}
				}while(loop);
			}while(choice<1 || choice>2);
			if(choice==1) 
				System.out.println("You have " + this.getLeaves() + "days of leave!");
			if (choice==2) {
				boolean loop=true;
				while (loop) {
					if(this.getLeaves()>0) {
						System.out.print("Please insert the number of days of leave you want to take: ");
						int days = sc.nextInt();
						if (days>this.getLeaves()) {
							System.out.println("You have just" + this.getLeaves() + "days of leave!");
							int ans=0;
							do {
								boolean cloop=true;
								do {
									try {
										System.out.println("Please press 3 if you want to aply for leave again"
														 + "or press 4 if you want to return to menu.");
										ans=sc.nextInt();
										if (ans<3 || ans>4)
											System.out.println("Please insert either 3 or 4: ");
										cloop=false;
									}
									catch (InputMismatchException ex) {
										System.err.println("exception "+ ex);
										sc.nextLine();
										System.out.println("Please insert an integer number! ");
									}
								}while(cloop);
							}while(ans<3|| ans>4);
							if (ans==3)
								System.out.print("Please insert the number of days of leave you want to take: ");
							else
								getMenu();
						}else {
							this.setLeaves(this.getLeaves()-days);
							System.out.println("Your request has been accepted! \n"
											 + "remaining days of leave: " + this.getLeaves() );
							loop=false;
						}
					}else {
						System.out.println("Yoy have not remaining days of leave!");
						loop=false;
					}
				}
			}
	}
}


