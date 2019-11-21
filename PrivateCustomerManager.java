package detranet

import java.util.Scanner;
import Employee;

public class PrivateCustomerManager extends Employee {

	public void removePrivate(int id) {
		pCM.remove(this);
	}
	
	public void custMenu() {
		System.out.println("Menu"
				+ "\n1.Addition of Customer"
				+ "\n2.Delete of Customer"
				+ "\n3.Display of Customer");
		Scanner sc= new Scanner (System.in);
		int option=sc.nextInt();
		switch(option) {
		case 1:
			System.out.println("Give customer's name");
			String name= sc.nextLine();
			this.setName(name);
			System.out.println("Give customer's id");
			int id = sc.nextInt();
			System.out.println("Give customer's amount of deposit");
			double amount = sc.nextDouble();
			this.setAmount(amount);
			System.out.println("Give customer's number of cars");
			int cards = sc.nextInt();
			this.setCards(cards);
			System.out.println("Give customer's number of loans");
			int loans = sc.nextInt();
			this.setNmbrLoans(loans);
			Private p=new Private(name,id,amount,cards,loans);
			
			p.addPrivate(pCM);
		case 2:
			System.out.println("Give customer's id that you want to delete");
			int delid = sc.nextInt();
			removePrivate(delid);
		case 3:
			toString();
		}
	}
	
	public void getMenu() {
		System.out.println("Menu"
				+ "\n1.Customers' management"
				+ "\n2.Goals'display"
				+ "\n3.Bonus"
				+ "\n4.Leaves"
				+ "\n5.Display of the bank's news"
				+ "\n6.Complaints'management"
				+ "\n7.Log Out");
		Scanner sc = new Scanner (System.in);
		int option=sc.nextInt();
		switch (option) {
		case 1:
			custMenu();
		case 2:
			this.goals(pCMgoal);
		case 3:
			this.computeBonus();
		case 4:
			this.leaves();
		case 5:
			this.getNews();
		case 6:
		case 7:
			Main.main();
			
		}
	}
}

