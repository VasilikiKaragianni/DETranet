/**
 * Main class
 */
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
		for ( Employee e : Employee.getEmployees()) {
			System.out.printf(e.getEmail()+ e.getPassword());
			System.out.println(e.getIdEmployee());
		}
		Employee.reloadLeaves();
		loadMain++;
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
							+ "\n2.Loan Manager"
							+ "\n3.Deposit Manager"
							+ "\n4.Private Customer Manager"
							+ "\n5.Teller"
							+ "\n6.Private Customer Manager Vip"
							+ "\n7.Private Customer Manager Delays"
							+ "\n8.Business Customer Manager"
							+ "\n9.Business Customer Manager Delays"
							+ "\n10.Business Customer Manager Vip"
							+ "\n11.Business Service Manager");
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
				Manager m = new Manager (inputFullname, "Manager" ,inputEmail,2000,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, m.getIdEmployee(), "Manager", inputEmail, 2000, startLeaves, inputPassword, 0);
				break;
			case 2:
				LoanManager lm = new LoanManager(inputFullname, "Loan Manager" ,inputEmail,1700,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, lm.getIdEmployee(), "Loan Manager", inputEmail, 1700, startLeaves, inputPassword, 0);
				break;
			case 3:
				DepositManager dm = new DepositManager(inputFullname, "Deposit Manager" ,inputEmail,1700,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, dm.getIdEmployee(), "Deposit Manager", inputEmail, 1700, startLeaves, inputPassword, 0);
				break;
			case 4:
				PrivateCustomerManager pcm = new PrivateCustomerManager(inputFullname, "Private Customer Manager" ,inputEmail,1400,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, pcm.getIdEmployee(), "Private Customer Manager", inputEmail, 1400, startLeaves, inputPassword, 0);
				break;
			case 5:
				Teller t = new Teller(inputFullname, "Teller" ,inputEmail,1500,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, t.getIdEmployee(), "Teller", inputEmail, 1500, startLeaves, inputPassword, 0);
				break;
			case 6:
				PrivateCustomerManagerVip pcmv = new PrivateCustomerManagerVip(inputFullname, "Private Customer Manager Vip" ,inputEmail,1300,firstday,startLeaves,inputPassword,0); 
				Database.saveEmployee(inputFullname, pcmv.getIdEmployee(), "Private Customer Manager Vip", inputEmail, 1300, startLeaves, inputPassword, 0);
				break;
			case 7:
				PrivateCustomerManagerDelays pcmd = new PrivateCustomerManagerDelays (inputFullname, "Private Customer Manager Delays" ,inputEmail,1300,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, pcmd.getIdEmployee(), "Private Customer Manager Delays", inputEmail, 1300, startLeaves, inputPassword, 0);
				break;
			case 8:
				BusinessCustomerManager bcm = new BusinessCustomerManager (inputFullname, "Business Customer Manager" ,inputEmail,1400,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, bcm.getIdEmployee(), "Business Customer Manager", inputEmail, 1400, startLeaves, inputPassword, 0);
				break;
			case 9:
				BusinessCustomerManagerDelays bcmd = new BusinessCustomerManagerDelays (inputFullname, "Business Customer Manager Delays" ,inputEmail,1400,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, bcmd.getIdEmployee(), "Business Customer Manager Delays", inputEmail, 1400, startLeaves, inputPassword, 0);
				break;
			case 10:
				BusinessCustomerManagerVip bcmv = new BusinessCustomerManagerVip (inputFullname, "Business Customer Manager Vip" ,inputEmail,1500,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, bcmv.getIdEmployee(), "Business Customer Manager Vip", inputEmail, 1500, startLeaves, inputPassword, 0);
				break;
			case 11:
				BusinessServiceManager bsm = new BusinessServiceManager (inputFullname, "Business Service Manager" ,inputEmail,1500,firstday,startLeaves,inputPassword,0);
				Database.saveEmployee(inputFullname, bsm.getIdEmployee(), "Business Service Manager", inputEmail, 1500, startLeaves, inputPassword, 0);
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
					System.out.println("Incorrect email or password. Try again..."
							+ "\nDo you want to create new user;(Y/N) ");
					String ans;
					do {
						ans = sc.nextLine();
						ans=ans.toUpperCase();
					} while(!ans.equals("Y")  && !ans.equals("N"));
					if (ans.equals("Y") || ans.contentEquals("y")) successLogIn=false;
				} else {
					successLogIn=false;
					Employee.logIn(inputEmail, logInInputPassword).getMenu();
				}
			}while (successLogIn);	
		}
		Main.main(null);
	}
}