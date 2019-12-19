package detranet;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
/* Class for loan Manager */
public class LoanManager extends Employee {

	public LoanManager(String fullname, int idEmployee, String department, String email, double salary,
			Date firstDate, int leaves, String username, String password, double overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
	}
	
	
	/* Method for select  employees of loan department */
	public void SelectDepartmentFoSetGoals() {
		Scanner sc = new Scanner (System.in);
		boolean endOfProcedure = true;
		do {
			boolean flag = true;
			int select = 0;
			do {
				try {
					System.out.printf("\nGoals sharing for loans."
							+ "\n1.Business service manager"
							+ "\n2.Customer service manager"
							+ "\n3.Customer service manager vip"
							+ "\n4.Customer service manager delays"
							+ "\n5.Εnd of procedure\n");
					select = sc.nextInt();
					if (select > 0 && select < 6) {
						flag = false;
					}else {
						System.out.printf("Please insert an integer between 1-5.Try again...");
						}
					}
				catch (InputMismatchException inputmismatchexception) {
					System.err.printf("%nException%n: %s%n", inputmismatchexception);
					sc.nextLine();
					System.out.printf("Please insert an integer between 1-5.Try again...");
				}
			}while (flag);
			/* create or open and write a new file for chosen employee */
			switch (select) {
			case 1:
				System.out.printf("Business service manager goals:\n");
				setGoals("Business service manager goals");
				break;
			case 2:
				System.out.printf("Customer service manager goals:\n");
				setGoals("Customer service manager goals");
				break;
			case 3:
				System.out.printf("Customer service manager Vip goals:\n");
				setGoals("Customer service manager vip goals");
				break;
			case 4:
				System.out.printf("Customer service manager delays goals:\n");
				setGoals("Customer service manager delays goals");
				break;
			case 5:
				endOfProcedure = false;
				break;
			}
		} while (endOfProcedure);
	}
	
	
	/* Method compute bonus for loan manager 
	 * based on the average overall of the employees of the loan department */
	@Override
	public double computeBonus() {
		int nmbrOfEmployees = 0;
		double sumOverall = 0;
		double rate = 0;
		double bonus = 0;
		/* Find loan employees and sum overalls */
		for(int i=0; i < employees.size(); i++) {
			Employee emp = employees.get(i);
			if (emp.getDepartment() == "Business Service Manager" 
					|| emp.getDepartment() == "Custumer Service Manager" 
					|| emp.getDepartment() == "Customer Service Manager Vip" 
					|| emp.getDepartment() == "Customer Service Manager Delays") {
				int idCurrEmployee = emp.getIdEmployee();
				nmbrOfEmployees += 1;
				for (int j = 0; j < idEmployees.size(); j++ ) {		/* The positions of employees are not the same in lists employees and idEmployees */
					if (idCurrEmployee == idEmployees.get(j)) { 	/* the same in lists employees and idEmployees */
						sumOverall += overalls.get(j);
						break;
					}
				}
			}
		}
		rate = sumOverall / nmbrOfEmployees;
		if(rate > 75) {
			bonus = rate * 3;
		}	
		return bonus;
	}
	
	/* Add rate of loan manager */
	public void evaluationLoanManager() {
		double rate = (computeBonus()/3);
		for(int j = 0; j < employees.size(); j++) {
			Employee empLM = employees.get(j);
			if(empLM.getDepartment() == "Loan Manager") {
				empLM.setOverall(rate);
				idEmployees.add(empLM.getIdEmployee());
				overalls.add(rate);
				break;
			}
		}	
	}
	
	public double finalSalary() {
		return (this.getSalary() + computeBonus());
	}
	
	/* Display the menu for loan manager */
	@Override
	public void getMenu() {
		Scanner sc = new Scanner (System.in);
		for(;;) {
			int select=0;
			boolean flag=true;
			do {
				try {
					System.out.printf("\nWelcome to the Loan Manager menu!"
							+ "\n1.Display department goals"
							+ "\n2.Goals sharing"
							+ "\n3.Leaves"
							+ "\n4.Compute BONUS"
							+ "\n5.News"
							+ "\n6.Log Out\n");
					select = sc.nextInt();
					if (select > 0 && select < 7) {
						flag=false;
					}else {
						System.out.printf("Please insert an integer between 1-6.Try again...");
					}
				}	
				catch (InputMismatchException inputmismatchexception) {
					System.err.printf("%nException%n: %s%n", inputmismatchexception);
					sc.nextLine();
					System.out.printf("Please insert an integer between 1-6.Try again...");
				}
			}while(flag);
			switch (select) {
			case 1:
				goals("Loan manager goals");
				break;
			case 2:
				SelectDepartmentFoSetGoals();
				break;
			case 3:
				leaves();
				break;
			case 4:
				evaluationLoanManager();
				System.out.println("Goals achievment rate for loan manager: " + (computeBonus()/3) + "%" +
						"\nBonus: " + computeBonus() + "$" +
						"\nFinal salary: " + finalSalary() + "$");
				break;
			case 5:
				Employee.getNews();
				break;
			case 6:
				Main.main(null);
				break;
			}
		}
	}
}
