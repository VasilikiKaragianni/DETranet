package detranet;
import java.util.Scanner;

public class BusinesServiceManager extends Employee {
	
	public void removeCust(int id) {
		int index = Business.bSM.indexOf(id);
		if (index == -1) {
			System.out.println("The id you gave is not valid"); 
		}else {
			Business.bSM.remove(index);
			System.out.println("The customer was deleted succesfuly");
		}	
	}
	
	public void employeeList() {
		System.out.println("Menu"
				+ "\n1.Add customer"
				+ "\n2.Delete customer"
				+ "\n3.View list of customers");
		Scanner sc= new Scanner(System.in);
		int select = sc.nextInt();
		switch (select) {
		case 1: 
			System.out.println("Give your new customer's name");
			String name = sc.nextLine();
			this.setName(name);
			System.out.println("Give the type of his/hers business");
			String type = sc.nextLine();
			this.setType(type);
			System.out.println("Give your new customer's id");
			int businessId = sc.nextInt();
			System.out.println("Give the amount of his/hers depositions");
			double amount = sc.nextDouble();
			this.setAmount(amount);
			System.out.println("Give the number of his/hers loans");
			int nmbrLoans = sc.nextInt();
			this.setNmbrLoans(nmbrLoans);
			Business newCust = new Business(name,type,businessId,amount,nmbrLoans);
			newCust.addPrivate(1);
			System.out.println("Your new customer was added succesfully");
		case 2:
			System.out.println("Type the customer's id you would like to delete");
			int id2= sc.nextInt();
			removeCust(id2);
		case 3:
			for(int i=0; i<=Business.bSM.size(); i++)
				toString();
			}
		}	
	
	public void goals (String bSMgoals) {
		Scanner sc = new Scanner(System.in);
		if (bSMgoals==null) {
			System.out.println("There are no goals available at this time");
		}else { 
			System.out.println("Business manager's goals:\n" + bSMgoals);
		}
		getMenu();
	}
	
	public void leaves() {
		System.out.println("1.Display of days left for absence"
							+ "\n2.Permission for absence");
		Scanner sc = new Scanner(System.in);
		int select=sc.nextInt();
		if(select==1) {
			System.out.println("You deserve " + this.getLeaves() + "days of absence");
		}else if (select==2) {
			if(this.getLeaves()>0) {
				System.out.print("Number of days,that you want,for absence: ");
				int days = sc.nextInt();
				if (days>this.getLeaves()) {
					System.out.println("\nYou still have" + this.getLeaves() + "days of absence");
				}else {
					this.setLeaves(this.getLeaves()-days);
					System.out.println("Your demand has been accepted"
						+ "\nYou still have: " + this.getLeaves() +"days of absence" );
				}
			}else {
				System.out.println("");
			}
		}
		getMenu();
	}
	

	@Override
	public void getMenu() {
		System.out.println("Menu"
				+ "\n1.Customer management"
				+ "\n2.Display of goals"
				+ "\n3.Bonus"
				+ "\n4.Permission for absence"
				+ "\n5.Display of bank's news"
				+ "\n6.Log Out");
		Scanner sc = new Scanner (System.in);
		int select=sc.nextInt();
		switch (select) {
		case 1:
			employeeList();
		case 2:
			goals(bSMgoals);
		case 3:
			computeBonus();
		case 4:
			leaves();
		case 5:
			this.getNews();
		case 6:
			Main.main();
		}


	@Override
	public void setGoals() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double computeBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
 
	
