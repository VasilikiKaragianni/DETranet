package detranet;

import java.util.ArrayList;


public abstract class Employee {
	
	public static ArrayList <Employee> employees= new ArrayList<Employee>();
	public static ArrayList <String> employees2= new ArrayList<String>();
	public static ArrayList <Integer> idEmployees= new ArrayList<Integer>();
	public static ArrayList <Double> overalls= new ArrayList<Double>();
	
	private String fullname;
	private static int count=0;
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
			int leaves, String username, String password, Double overall) {
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
		employees2.add(email+password);
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
	public abstract void setGoals();
	public abstract double computeBonus();
	public abstract void getMenu();
	public abstract void leaves();
	
	public static void getNews() {
		
		   
		  
	}

	public static Employee logIn(String email, String password) {
		if(employees2.contains(email+password))
			return employees.get(employees2.indexOf(email+password));
		else
			return null;
	}
}