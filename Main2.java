package gr.aueb.dmst.DETranet;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;
import java.util.*;

public class Main {
	public static void loadSQLDatabaseConnection() {
	        String url =
	                "jdbc:sqlserver://sqlserver.dmst.aueb.gr:1433;"
	                        + "database=DB79;"
	                        + "user=G579;"
	                        + "password=3webh0534;";
	        
	        Connection dbcon ;
	    	Statement stmt;
		      	try {
	      		dbcon = DriverManager.getConnection(url);
	      		stmt = dbcon.createStatement();
	      		
	      		String s = "SELECT * FROM Employee";
	            ResultSet rs = stmt.executeQuery(s);
	      	    while (rs.next()) {
	      	    	String f = rs.getString("fullname");
	      	    	int id = rs.getInt("idEmployee");
	      	    	String d = rs.getString("department");
	      	    	String e = rs.getString("email");
	      	    	double sa = rs.getDouble("salary");
	      	    	Date da = rs.getDate("firstDate");
	      	    	int l = rs.getInt("leaves");
	      	    	String u = rs.getString("username");
	      	    	String p = rs.getString("password");
	      	    	double o = rs.getDouble("overall");
	      	    	//System.out.print(o + f);
	      	    	if (d == "Manager") {
	      	    		new Manager (f, id, d, e, sa, da, l, u, p, o);
	      	    	} else if (d == "Loan Manager") {
	      	    		new LoanManager(f, id, d, e, sa, da, l, u, p, o);	      	    	
	      	    	} else if (d == "Deposit Manager") {
	      	    		new DepositManager(f, id, d, e, sa, da, l, u, p, o);
	      	    	} else if (d == "Private Customer Manager" ) {
	      	    		new PrivateCustomerManager(f, id, d, e, sa, da, l, u, p, o);	      	    		
	      	    	} else if (d == "Teller") {
	      	    		new Teller(f, id, d, e, sa, da, l, u, p, o);
	      	    	} else if (d == "Private Customer Manager Vip") {
	      	    		new PrivateCustomerManagerVip(f, id, d, e, sa, da, l, u, p, o);
	      	    	} else if (d == "Private Customer Manager Delays") {
	      	    		new PrivateCustomerManagerDelays (f, id, d, e, sa, da, l, u, p, o);
	      	    	} else if (d == "Business Customer Manager Vip") {
	      	    		new BusinessCustomerManagerVip (f, id, d, e, sa, da, l, u, p, o);	      	    		
	      	    	} else if (d == "Business Customer Manager Delays") {
	      	    		new BusinessCustomerManagerDelays (f, id, d, e, sa, da, l, u, p, o);	      	    		
	      	    	} else if (d == "Business Service Manager") {
	      	    		new BusinessServiceManager (f, id, d, e, sa, da, l, u, p, o);	      	    		
	      	    	} 
	      	    }
	      	    
	      	    s = "SELECT * FROM BusinessCustomer"; // +Business
	            rs = stmt.executeQuery(s);
	            while (rs.next()) {
	            	int i = rs.getInt("idBusiness");
	            	String n = rs.getString("name");
	            	int nl = rs.getInt("nmbrLoans");
	            	double a = rs.getDouble("amount");
	            	int id = rs.getInt("idEmployee");
	            	String t = rs.getString("type");
	            	Business b = new Business(n, t, i, a, nl);
	            	for(int c=0; c < Employee.getEmployees().size(); c++) {
	            		Employee e = Employee.getEmployees().get(c);
	            		if( e.getIdEmployee()== id &&
	            				Employee.getEmployees().get(c).getDepartment() == "Business Customer Manager") {
	            			Business.cSM.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment() == "Business Customer Manager Vip") {
	            			Business.cSMVip.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment() == "Business Customer Manager Delays") {
	            			Business.cSMDelays.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
        				Employee.getEmployees().get(c).getDepartment() == "Business Service Manager") {
	            			Business.bSM.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment() == "Teller") {
	            			Business.tellerBusiness.add(b);
	            		}
	            	}
	            }
	            
	            s = "SELECT * FROM PrivateCustomer"; // +Business
	            rs = stmt.executeQuery(s);
	            while (rs.next()) {
	            	int i = rs.getInt("idPrivate");
	            	String n = rs.getString("name");
	            	int nl = rs.getInt("nmbrLoans");
	            	double a = rs.getDouble("amount");
	            	int id = rs.getInt("idEmployee");
	            	int ca = rs.getInt("cards");
	            	Private p = new Private(n, i, a, ca, nl);
	            	for(int c=0; c < Employee.getEmployees().size(); c++) {
	            		Employee e = Employee.getEmployees().get(c);
	            		if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment() == "Private Customer Manager") {
	            			Private.pCM.add(p);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment() == "Private Customer Manager Vip") {
	            			Private.pCMVip.add(p);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment() == "Private Customer Manager Delays") {
	            			Private.pCMDelays.add(p);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
        				Employee.getEmployees().get(c).getDepartment() == "Teller") {
	            			Private.tellerPrivate.add(p);
	            			break;
	            		}
	            	}
	            }
		      	rs.close();
		      	System.out.println();
		      	stmt.close();
		        dbcon.close();
	            }
	      	catch(SQLException e) {
	      		System.out.print("SQLException: ");
	      		System.out.println(e.getMessage());
	      	}
		}

	    
	public static void main (String args []) {
		loadSQLDatabaseConnection();
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
