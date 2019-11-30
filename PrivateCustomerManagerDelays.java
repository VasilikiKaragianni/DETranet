package detranet;

import java.util.Scanner;

import java.util.InputMismatchException;

public class PrivateCustomerManagerDelays extends Employee {
	
	public PrivateCustomerManagerDelays(String fullname, int idEmployee, String department, String email, double salary,
			String firstDate, int leaves, String username, String password, int overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
		// TODO Auto-generated constructor stub
	}

		public void removePrivate(int id) {
			int index=Private.pCMDelays.indexOf(id);
			if (index== -1) {
				System.out.println("The id you gave is not valid");
			} else {
				Private.pCMDelays.remove(index);
				System.out.println("The customer is deleted successfully");
			}
		}
		
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
						for(int i=0; i<=Private.pCMDelays.size(); i++)
						toString();
					}
					sc.close();
		}
		
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
			sc.close();
		}
		
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
						DepositManager.goals();
					case 3:
						DepositManager.computeBonus();
					case 4:
						leaves();
					case 5:
						PrivateCustomerManager.getNews();
					case 6:
						Main.main();
						
			}
		}
}
