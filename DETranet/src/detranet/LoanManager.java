package detranet;

import java.util.Scanner;

public class LoanManager extends Employee {
	public static String bSMGoals;
	public static String cSMGoals;
	public static String cSMGoldGoals;
	public static String cSMDelaysGoals;

	public LoanManager(String fullname, int idEmployee, String department, String email, double salary,
			String firstDate, int leaves, String username, String password, int overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
	}

	@Override
	public void goals(String loanMngrGoals) {
		Scanner sc = new Scanner(System.in);
		if (loanMngrGoals==null) {
			System.out.println("Δεν υπάρχουν διαθέσιμοι στόχοι προς επίτευξη αυτή τη στιγμή!");
		}else {
			System.out.println("Στόχοι τμήματος:\n" + loanMngrGoals);
		}
		String goBack = sc.next();
		getMenu();
	}

	@Override
	public void setGoals() {
		for(;;) {
		System.out.println("Καταμερισμός στόχων τους υπαλλήλους του τμήματος χορηγήσεων."
				+ "\n1.Υπεύθυνος εξυπηρέτησης επιχειρήσεων"
				+ "\n2.Υπεύθυνος πελατείας επιχειρήσεων"
				+ "\n3.Υπεύθυνος πελατείας επιχειρήσεων GOLD"
				+ "\n4.Υπεύθυνος πελατείας καθυστερήσεων επιχειρήσεων"
				+ "\n5.Ολοκλήρωση διαδικασίας");
		Scanner sc = new Scanner (System.in);
		int select=sc.nextInt();
		switch (select) {
		case 1:
			bSMGoals = sc.nextLine();
		case 2:
			cSMGoals = sc.nextLine();
		case 3:
			cSMGoldGoals = sc.nextLine();
		case 4:
			cSMDelaysGoals = sc.nextLine();
		case 5:
			getMenu();
			}
		}
	}

	@Override
	public double computeBonus() {
		int nmbrOfEmployees=0;
		int sumOverall=0;
		int rate = 0;
		double bonus=0;
		for(int i=0; i<employees.size(); i++) {
			Employee emp = employees.get(i);
			if (emp.getDepartment()=="BusinessServiceManager" 
					|| emp.getDepartment()=="CostumerServiceManager" 
					|| emp.getDepartment()=="CostumerServiceGoldManager" 
					|| emp.getDepartment()=="CostumerServiceDelaysManager") {
				nmbrOfEmployees =+ 1;
				sumOverall =+ overalls.get(i);
			}
		for(int j=0; j<employees.size(); j++) {
				Employee empLM = employees.get(j);
				if(empLM.getDepartment()=="LoanManager") {
					rate=sumOverall/nmbrOfEmployees;
					empLM.setOverall(rate);
					idEmployees.add(empLM.getIdEmployee());
					overalls.add(rate);
				}
		} 
		System.out.println("Το ποσοστό επίτευξης των στόχων του τμήματος χορηγήσεων είναι: " + rate );
		if(rate>75) {
			bonus = rate * 3;
		}
		}
		return bonus;
	}
	
	public void leaves() {
		System.out.println("1.Εμφάνηση εναπομείναντων αδειών"
							+ "\n2.Αίτηση άδειας");
		Scanner sc = new Scanner(System.in);
		int select=sc.nextInt();
		if(select==1) {
			System.out.println("Δικαιούστε ακόμα " + this.getLeaves() + "ημέρες άδειας!");
		}else if (select==2) {
			if(this.getLeaves()>0) {
				System.out.print("Επιθυμητές μέρες άδειας: ");
				int days = sc.nextInt();
				if (days>this.getLeaves()) {
					System.out.println("\nΣας απομένουν μόλις" + this.getLeaves() + "μέρες άδειας!");
				}else {
					this.setLeaves(this.getLeaves()-days);
					System.out.println("Το αίτημα σας έγινε δεκτό!"
						+ "\nΕναπομείναντες άδειες: " + this.getLeaves() );
				}
			}else {
				System.out.println("");
			}
		}
		String goBack = sc.next();
		getMenu();
	}

	@Override
	public void getMenu() {
		System.out.println("Menu"
				+ "\n1.Εμφάνηση στόχων τμήματος"
				+ "\n2.Καταμερισμός στόχων"
				+ "\n3.Αίτηση για άδεια"
				+ "\n4.Υπολογισμός BONUS"
				+ "\n5.Εμφάνηση νέων τράπεζας"
				+ "\n6.Log Out");
		Scanner sc = new Scanner (System.in);
		int select=sc.nextInt();
		switch (select) {
		case 1:
			goals(LoanMngrGoals);
		case 2:
			setGoals();
		case 3:
			leaves();
		case 4:
			System.out.println("Το ποσοστό επίτευξης των στόχων του τμήματος χορηγήσεων είναι: " + this.getOverall() +
					"\nBonus: " + computeBonus() + "$");
		case 5:
			this.getNews();
		case 6:
			Main.main();
		}
		
	}
}
