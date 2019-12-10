package detranet;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class Employee {
	
	public static ArrayList <Employee> employees = new ArrayList<Employee>();
	public static ArrayList <String> employees2 = new ArrayList<String>();
	public static ArrayList <Integer> idEmployees = new ArrayList<Integer>();
	public static ArrayList <Double> overalls = new ArrayList<Double>();
	
	private String fullname;
	public static int count = 0;
	private int idEmployee;
	private String department;
	private String email;
	private double salary;
	private String firstDate;
	private int leaves;
	private String username;
	private String password;
	private double overall;

	public Employee(String fullname, int idEmployee, String department, String email, double salary, String firstDate,
			int leaves, String username, String password, double overall) {
		super();
		this.fullname = fullname;
		this.idEmployee = count++;
		this.department = department;
		this.email = email;
		this.salary = salary;
		this.firstDate = firstDate;
		this.leaves = leaves;
		this.username = username;
		this.password = password;
		this.overall = overall;
		employees.add(this);
		employees2.add(username+password);
		idEmployees.add(idEmployee);
		overalls.add(overall);
	}

	public static ArrayList<Employee> getEmployees() {
		return employees;
	}

	
	public static ArrayList<Integer> getIdEmployees() {
		return idEmployees;
	}

	public static ArrayList<Double> getOveralls() {
		return overalls;
	}

	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public int getLeaves() {
		return leaves;
	}

	public void setLeaves(int leaves) {
		this.leaves = leaves;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getOverall() {
		return overall;
	}

	public void setOverall(double overall) {
		this.overall = overall;
	}

	
	public abstract void goals();
	public abstract double computeBonus();
	public abstract void getMenu();
	public static void getNews() {  }
	
	public void leaves() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			boolean loop = true;
			do {
				try {	
					System.out.println("Please press 1 if you want to see the remaining leave days. \n "
					 		 		 + "Please press 2 if you want to aply for leave.");
					choice = sc.nextInt();
					if (choice < 1 || choice > 2)
						System.out.println("Please insert either 1 or 2: ");
					loop = false;
				}
				catch (InputMismatchException ex) {
					System.err.println("exception "+ ex);
					sc.nextLine();
					System.out.println("Please insert an integer number! ");
				}
			}while(loop);
		}while(choice<1 || choice>2);
		if(choice == 1) 
			System.out.println("You have " + this.getLeaves() + "days of leave!");
		if (choice == 2) {
			boolean loop = true;
			while (loop) {
				if(this.getLeaves() > 0) {
					System.out.print("Please insert the number of days of leave you want to take: ");
					int days = sc.nextInt();
					if (days > this.getLeaves()) {
						System.out.println("You have just" + this.getLeaves() + "days of leave!");
						int ans = 0;
						do {
							boolean cloop = true;
							do {
								try {
									System.out.println("Please press 3 if you want to aply for leave again"
													 + "or press 4 if you want to return to menu.");
									ans = sc.nextInt();
									if (ans<3 || ans>4)
										System.out.println("Please insert either 3 or 4: ");
									cloop = false;
								}
								catch (InputMismatchException ex) {
									System.err.println("exception "+ ex);
									sc.nextLine();
									System.out.println("Please insert an integer number! ");
								}
							}while(cloop);
						}while(ans<3 || ans>4);
						if (ans == 3)
							System.out.print("Please insert the number of days of leave you want to take: ");
						else
							getMenu();
					}else {
						this.setLeaves(this.getLeaves()-days);
						System.out.println("Your request has been accepted! \n"
										 + "remaining days of leave: " + this.getLeaves() );
						loop = false;
					}
				}else {
					System.out.println("Yoy have not remaining days of leave!");
					loop = false;
				}
			}
		}
	}
	public static Employee logIn(String email, String password) {
		if(employees2.contains(email+password))
			return employees.get(employees2.indexOf(email+password));
		else
			return null;
	}
}