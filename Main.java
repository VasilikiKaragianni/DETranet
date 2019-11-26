package detranet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int select=0;
		boolean flag=true;
		do {
			try {
				System.out.println("\nWelcome! "
						+ "\n1.Create new user"
						+ "\n2.Log in");
				select=sc.nextInt();
				if (select==1 || select==2) {
					flag=false;
				}else {
					System.out.printf("Please enter 1 for create user or 2 for log in.Try again...");
				}
			}
			catch (InputMismatchException inputmismatchexception) {
				System.err.printf("%nException%n: %s%n" , inputmismatchexception);
				sc.nextLine();
				System.out.printf("Please enter 1 for create user or 2 for log in.Try again...");
			}
		}while(flag);	
		int startLeaves = 31;
		int id=Employee.count;
		String inputPassword;
		if(select==1) {
			int selectDep=0;
			flag=true;
			do {
				try {
					System.out.println("\nWelcome to the bank! Select your department:" 
							+ "\n1.Manager"
							+ "\n2.Deposit manager"
							+ "\n3.Loan manager"
							+ "\n4.Private costumer manager"
							+ "\n5.Teller"
							+ "\n6.Private costumer manager GOLD"
							+ "\n7.Private costumer manager delays"
							+ "\n8.Business service manager"
							+ "\n9.Costumer service manager"
							+ "\n10.Costumer service manager GOLD"
							+ "\n11.Costumer service manager delays");
					selectDep=sc.nextInt();
					if (selectDep>0 && selectDep<12) {
						flag=false;
					}else {
						System.out.println("Please insert an integer between 1-11.Try again...");
					}
				}
				catch (InputMismatchException inputmismatchexception) {
					System.err.printf("%nException%n: %s%n" , inputmismatchexception);
					sc.nextLine();
					System.out.println("Please insert an integer between 1-11.Try again...");
				}
			}while(flag);
			System.out.printf("\nRegister form!"
								+ "Fullname: ");
			String inputFullname = sc.next();
			System.out.printf("\nE-mail: ");
			String inputEmail = sc.next();
			System.out.printf("\nFirst day: ");
			String firstday = sc.next();
			System.out.printf("\nUsername: ");
			String inputUsername = sc.next();
			flag=true;
			do {
				System.out.printf("\nPassword: ");
				String pswrd1=sc.nextLine();
				System.out.printf("\nConfirm password: ");
				String pswrd2=sc.nextLine();
				if (pswrd1.equals(pswrd2)) {
					flag=false;
					inputPassword = pswrd1;
				}else {
					System.err.printf("\nPasswords are not the same.Try again...");
				}
			}while(flag);
			switch (selectDep) {
			case 1:
				Manager manager = new Manager (inputFullname, id, "Manager" ,inputEmail,2000,firstday,startLeaves,inputUsername,inputPassword,0); 
			case 2:
				
			case 3:
				
			case 4:
				
			case 5:
				
			case 6:
				
			case 7:
				
			case 8:
				
			case 9:
				
			case 10:
				
			case 11:
				
			}		
		}else {
			boolean successLogIn = true;
			do {
				System.out.println("Log In"
						+ "\nUsername: ");
				String inputUsername = sc.nextLine();
				System.out.println("Password: ");
				String logInInputPassword = sc.nextLine();
				if (Employee.logIn(inputUsername, logInInputPassword) == null) {
					System.out.println("Incorrect username or password. Try again..."
							+ "\nDo you want to crate new user;(Y/N) ");
					String ans;
					do {
						ans = sc.nextLine();
						ans=ans.toUpperCase();
					} while(!ans.equals("Y")  && !ans.equals("N"));
					if (ans.equals("Y")) successLogIn=false;
				} else {
					successLogIn=false;
					Employee.logIn(inputUsername, inputPassword).getMenu();
				}
			}while (successLogIn);	
			main(args);
		}
	}
}	
