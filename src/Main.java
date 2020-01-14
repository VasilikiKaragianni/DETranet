package gr.aueb.dmst.DETranet;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	// variable that counts how many times Main.main has been loaded and makes sure database has been loaded once
	static int loadMain = 0; 
	
	final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		if (loadMain == 0) {
			Database.loadmySQLDatabase();
		}
		Employee.reloadLeaves();
		/*if (loadMain != 0) {
			Database.savemysqlDatabase();
		}		
		loadMain++;*/
		int select=0;
		boolean flag=true;
		do {
			try {
				System.out.println("\nWelcome! "
						+ "\n1.Create new user"
						+ "\n2.Log in");
				select = sc.nextInt();
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
		String inputPassword="";
		if(select==1) {
			int selectDep=0;
			flag=true;
			do {
				try {
					System.out.println("Welcome to the bank! Select your department:" 
							+ "\n1.Manager"
							+ "\n2.Loan manager"
							+ "\n3.Deposit manager"
							+ "\n4.Private customer manager"
							+ "\n5.Teller"
							+ "\n6.Private customer manager VIP"
							+ "\n7.Private customer manager delays"
							+ "\n8.Business customer manager"
							+ "\n9.Business customer manager delays"
							+ "\n10.Business customer manager VIP"
							+ "\n11.Business service manager");
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
			String inputFullname =sc.next();
			sc.nextLine(); 
			System.out.printf("\nE-mail: ");
			String inputEmail = sc.next();
			int year = 2009;
			int month = 0; // January
			int date = 1;
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DATE, date);
			java.util.Date firstday = cal.getTime();
			flag=true;
			System.out.printf("\nPassword: ");
			do {
				String pswrd1=sc.next();
				System.out.printf("\nConfirm password: ");
				String pswrd2=sc.next();
				if (pswrd1.equals(pswrd2)) {
					flag=false;
					inputPassword = pswrd1;
				}else {
					System.err.printf("\nPasswords are not the same.Try again...");
					System.out.printf("\nPassword: ");
				}
			}while(flag);
			switch (selectDep) {
			case 1:
				new Manager (inputFullname, id, "Manager" ,inputEmail,2000,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Manager", inputEmail, 2000, startLeaves, inputPassword, 0);
				break;
			case 2:
				new LoanManager(inputFullname, id, "Loan Manager" ,inputEmail,1700,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Loan Manager", inputEmail, 1700, startLeaves, inputPassword, 0);
				break;
			case 3:
				new DepositManager(inputFullname, id, "Deposit Manager" ,inputEmail,1700,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Deposit Manager", inputEmail, 1700, startLeaves, inputPassword, 0);
				break;
			case 4:
				new PrivateCustomerManager(inputFullname, id, "Private Customer Manager" ,inputEmail,1400,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Private Customer Manager", inputEmail, 1400, startLeaves, inputPassword, 0);
				break;
			case 5:
				new Teller(inputFullname, id, "Teller" ,inputEmail,1500,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Teller", inputEmail, 1500, startLeaves, inputPassword, 0);
				break;
			case 6:
				new PrivateCustomerManagerVip(inputFullname, id, "Private costumer manager Vip" ,inputEmail,1300,firstday,startLeaves,inputPassword,0); 
				Database.saveEmployee(inputFullname, id, "Private costumer manager Vip", inputEmail, 1300, startLeaves, inputPassword, 0);
				break;
			case 7:
				new PrivateCustomerManagerDelays (inputFullname, id, "Private customer manager delays" ,inputEmail,1300,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Private customer manager delays", inputEmail, 1300, startLeaves, inputPassword, 0);
				break;
			case 8:
				new BusinessCustomerManager (inputFullname, id, "Business customer manager" ,inputEmail,1400,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Business customer manager", inputEmail, 1400, startLeaves, inputPassword, 0);
				break;
			case 9:
				new BusinessCustomerManagerDelays (inputFullname, id, "Business customer manager delays" ,inputEmail,1400,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Business customer manager delays", inputEmail, 1400, startLeaves, inputPassword, 0);
				break;
			case 10:
				new BusinessCustomerManagerVip (inputFullname, id, "Business customer manager Vip " ,inputEmail,1500,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Business customer manager Vip", inputEmail, 1500, startLeaves, inputPassword, 0);
				break;
			case 11:
				new BusinessServiceManager (inputFullname, id, "Business service manager" ,inputEmail,1500,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, id, "Business service manager", inputEmail, 1500, startLeaves, inputPassword, 0);
			}		
		}else {
			boolean successLogIn = true;
			do {
				System.out.println("Log In"
						+ "\nEmail: ");
				String inputEmail = sc.next();
				System.out.println("Password: ");
				String logInInputPassword = sc.next();
				if (Employee.logIn(inputEmail, logInInputPassword) == null) {
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
					Employee.logIn(inputEmail, logInInputPassword).getMenu();
				}
			}while (successLogIn);	
		}
		Main.main(null);
	}
}