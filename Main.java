package detranet;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.mysql.*;
import java.sql.*;

public class Main {
	
	public static void loadmySQLDatabase() {
		try{  
			Class.forName("com.mysql.jdbc.Driver"); //if it doesn't work try com.mysql.cj.jdbc.Driver
		
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
	      	Statement stmt = con.createStatement();
	      		
	            String s = ("SELECT * FROM Employee");
	            ResultSet rs = stmt.executeQuery(s);
	            while (rs.next()) {
	      	    	String f = rs.getString("fullname");
	      	    	int id = rs.getInt("idEmployee");
	      	    	String d = rs.getString("department");
	      	    	String e = rs.getString("email");
	      	    	double sa = rs.getDouble("salary");
	      	    	Date da = rs.getDate("firstDate");
	      	    	int l = rs.getInt("leaves");
	      	    	String p = rs.getString("password");
	      	    	double o = rs.getDouble("overall");
	      	    
	      	    	if (d.equals("Manager")) {
	      	    		new Manager (f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Loan Manager")) {
	      	    		new LoanManager(f, id, d, e, sa, da, l, p, o);	      	    	
	      	    	} else if (d.equals("Deposit Manager")) {
	      	    		new DepositManager(f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Private Customer Manager")) {
	      	    		new PrivateCustomerManager(f, id, d, e, sa, da, l, p, o);	      	    		
	      	    	} else if (d.equals("Teller")) {
	      	    		new Teller(f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Private Customer Manager Vip")) {
	      	    		new PrivateCustomerManagerVip(f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Private Customer Manager Delays")) {
	      	    		new PrivateCustomerManagerDelays (f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Business Customer Manager Vip")) {
	      	    		new BusinessCustomerManagerVip (f, id, d, e, sa, da, l, p, o);	      	    		
	      	    	} else if (d.equals("Business Customer Manager Delays")) {
	      	    		new BusinessCustomerManagerDelays (f, id, d, e, sa, da, l, p, o);	      	    		
	      	    	} else if (d.equals("Business Service Manager")) {
	      	    		new BusinessServiceManager (f, id, d, e, sa, da, l, p, o);	      	    		
	      	    	} 
	      	    }
	      	    
	      	    
	      	    s = "SELECT * FROM Business"; // +Business
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
	            				Employee.getEmployees().get(c).getDepartment().equals("Business Customer Manager")) {
	            			Business.cSM.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Business Customer Manager Vip")) {
	            			Business.cSMVip.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Business Customer Manager Delays")) {
	            			Business.cSMDelays.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
        				Employee.getEmployees().get(c).getDepartment().equals("Business Service Manager")) {
	            			Business.bSM.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Teller")) {
	            			Business.tellerBusiness.add(b);
	            		}
	            	}
	            }
	            
	            s = "SELECT * FROM Private"; // +Business
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
	            				Employee.getEmployees().get(c).getDepartment().equals("Private Customer Manager")) {
	            			Private.pCM.add(p);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Private Customer Manager Vip")) {
	            			Private.pCMVip.add(p);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Private Customer Manager Delays")) {
	            			Private.pCMDelays.add(p);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
        				Employee.getEmployees().get(c).getDepartment().equals("Teller")) {
	            			Private.tellerPrivate.add(p);
	            			break;
	            		}
	            	}
	            }
		      	rs.close();
		      	System.out.println();
		      	stmt.close();
		        con.close();
	            }
	      	catch(SQLException e) {
	      		System.out.print("SQLException: ");
	      		System.out.println(e.getMessage());
	      	} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	    
	public static void main(String[] args) {
		loadmySQLDatabase();
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
			String inputFullname =sc.nextLine();
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
				new Manager (inputFullname, id, "Manager" ,inputEmail,2000,firstday,startLeaves,inputPassword,0);
				break;
			case 2:
				new LoanManager(inputFullname, id, "Loan Manager" ,inputEmail,1700,firstday,startLeaves,inputPassword,0);
				break;
			case 3:
				new DepositManager(inputFullname, id, "Deposit Manager" ,inputEmail,1700,firstday,startLeaves,inputPassword,0);
				break;
			case 4:
				new PrivateCustomerManager(inputFullname, id, "Private Customer Manager" ,inputEmail,1400,firstday,startLeaves,inputPassword,0);
				break;
			case 5:
				new Teller(inputFullname, id, "Teller" ,inputEmail,1500,firstday,startLeaves,inputPassword,0);
				break;
			case 6:
				new PrivateCustomerManagerVip(inputFullname, id, "Private costumer manager Vip" ,inputEmail,1300,firstday,startLeaves,inputPassword,0); 
				break;
			case 7:
				new PrivateCustomerManagerDelays (inputFullname, id, "Private customer manager delays" ,inputEmail,1300,firstday,startLeaves,inputPassword,0);
				break;
			case 8:
				new BusinessCustomerManager (inputFullname, id, "Business customer manager" ,inputEmail,1400,firstday,startLeaves,inputPassword,0);
				break;
			case 9:
				new BusinessCustomerManagerDelays (inputFullname, id, "Business customer manager delays" ,inputEmail,1400,firstday,startLeaves,inputPassword,0);
				break;
			case 10:
				new BusinessCustomerManagerVip (inputFullname, id, "Business customer manager Vip " ,inputEmail,1500,firstday,startLeaves,inputPassword,0);
				break;
			case 11:
				new BusinessServiceManager (inputFullname, id, "Business service manager" ,inputEmail,1500,firstday,startLeaves,inputPassword,0);
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
