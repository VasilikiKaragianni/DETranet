package detranet;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	

	public static void loadObjects() {
		Date date= new Date(2000,8,12);
		 Manager manager = new Manager ("alex", 0, "Manager" ,"alex@gmail.com",2000,date,31,"alex","ab1",0);
		 LoanManager lnmngr= new LoanManager("anna", 1, "Loan Manager" ,"anna@hotmail.com",1700,"32-09-2011",31,"anna","ab2",0);
		 DepositManager dpmngr= new DepositManager("george", 2, "Deposit Manager" ,"g@gmail.com",1700,"31-01-2009",31,"george","ac23",0);
		 PrivateCustomerManager prcumn= new PrivateCustomerManager("max", 3, "Private Customer Manager" ,"max23@gmail.com",1400,"22-09-2008",31,"max23","ma21",0);
		 Teller teller = new Teller("adrian", 4, "Private Customer Manager" ,"ad_po@gmail.com",1400,"22-03-2008",31,"adri","abc",0);
		 PrivateCustomerManagerVip prcumngold = new PrivateCustomerManagerVip("leo",5, "Private Customer Manager" ,"leoo@gmail.com",1400,"22-09-2012",31,"leoo","op21",0); 
		 PrivateCustomerManagerDelays prcumndel = new PrivateCustomerManagerDelays ("kate", 6, "Private Customer Manager" ,"kk@gmail.com",1400,"22-09-2018",31,"kate","345g",0);
	}
	
	
	
	public static void main(String[] args) {
		loadObjects();
		Employee.reloadLeaves();		
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
		String inputPassword="";
		if(select==1) {
			int selectDep=0;
			flag=true;
			do {
				try {
					System.out.println("Welcome to the bank! Select your department:" 
							+ "\n1.Manager"
							+ "\n2.Deposit manager"
							+ "\n3.Loan manager"
							+ "\n4.Private customer manager"
							+ "\n5.Teller"
							+ "\n6.Private customer manager GOLD"
							+ "\n7.Private customer manager delays"
							+ "\n8.Business service manager"
							+ "\n9.Customer service manager"
							+ "\n10.Customer service manager GOLD"
							+ "\n11.Customer service manager delays");
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
			String inputFullname =sc.nextLine();
			sc.nextLine(); 
			System.out.printf("\nE-mail: ");
			String inputEmail = sc.next();
			System.out.printf("\nFirst day: ");
			int year = 2009;
			int month = 0; // January
			int date = 1;
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DATE, date);
			java.util.Date firstday = cal.getTime();
			System.out.printf("\nUsername: ");
			String inputUsername = sc.next();
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
				Manager manager = new Manager (inputFullname, id, "Manager" ,inputEmail,2000,firstday,startLeaves,inputUsername,inputPassword,0);
				break;
			case 2:
				LoanManager lnmngr= new LoanManager(inputFullname, id, "Loan Manager" ,inputEmail,1700,firstday,startLeaves,inputUsername,inputPassword,0);
				break;
			case 3:
				DepositManager dpmngr= new DepositManager(inputFullname, id, "Deposit Manager" ,inputEmail,1700,firstday,startLeaves,inputUsername,inputPassword,0);
				break;
			case 4:
				PrivateCustomerManager prcumn= new PrivateCustomerManager(inputFullname, id, "Private Customer Manager" ,inputEmail,1400,firstday,startLeaves,inputUsername,inputPassword,0);
				break;
			case 5:
				Teller teller = new Teller(inputFullname, id, "Teller" ,inputEmail,1500,firstday,startLeaves,inputUsername,inputPassword,0);
				break;
			case 6:
				PrivateCustomerManagerVip prcumngold = new PrivateCustomerManagerVip(inputFullname, id, "Private costumer manager Vip" ,inputEmail,1300,firstday,startLeaves,inputUsername,inputPassword,0); 
				break;
			case 7:
				PrivateCustomerManagerDelays prcumndel = new PrivateCustomerManagerDelays (inputFullname, id, "Private customer manager delays" ,inputEmail,1300,firstday,startLeaves,inputUsername,inputPassword,0);
				break;
			case 8:
				BusinessCustomerManager bucumn = new BusinessCustomerManager (inputFullname, id, "Business customer manager" ,inputEmail,1400,firstday,startLeaves,inputUsername,inputPassword,0);
				break;
			case 9:
				BusinessCustomerManagerDelays bucumndel = new BusinessCustomerManagerDelays (inputFullname, id, "Business customer manager delays" ,inputEmail,1400,firstday,startLeaves,inputUsername,inputPassword,0);
				break;
			case 10:
				BusinessCustomerManagerVip bucumnvip = new BusinessCustomerManagerVip (inputFullname, id, "Business customer manager Vip " ,inputEmail,1500,firstday,startLeaves,inputUsername,inputPassword,0);
				break;
			case 11:
				BusinessServiceManager busermn = new BusinessServiceManager (inputFullname, id, "Business service manager" ,inputEmail,1500,firstday,startLeaves,inputUsername,inputPassword,0);
			}		
		}else {
			boolean successLogIn = true;
			do {
				System.out.println("Log In"
						+ "\nUsername: ");
				String inputUsername = sc.next();
				System.out.println("Password: ");
				String logInInputPassword = sc.next();
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
					Employee.logIn(inputUsername, logInInputPassword).getMenu();
				}
			}while (successLogIn);	
		}
		Main.main(null);
	}
}