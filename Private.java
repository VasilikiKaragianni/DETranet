package detranet;

import java.util.ArrayList;

public class Private { 
	
	private String name;
	private int idPrivate;
	private double amount;
	private int cards;
	private int nmbrLoans;
	private static int count=1;
	
	public static ArrayList <Private> pCM= new ArrayList<Private>();
	public static ArrayList <Private> tellerPrivate= new ArrayList<Private>();
	public static ArrayList <Private> pCMGold= new ArrayList<Private>();
	public static ArrayList <Private> pCMDelays= new ArrayList<Private>();
	
	
	public static ArrayList<Private> getpCM() {
		return pCM;
	}
	public static ArrayList<Private> getTeller() {
		return tellerPrivate;
	}
	public static ArrayList<Private> getpCMGold() {
		return pCMGold;
	}
	public static ArrayList<Private> getpCMDelays() {
		return pCMDelays;
	}
	public Private(String name, int idPrivate, double amountOfDeposit, int cards, int nmbrLoans) {
		super();
		this.name = name;
		this.idPrivate = count++;
		this.amount = amountOfDeposit;
		this.cards = cards;
		this.nmbrLoans = nmbrLoans;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdPrivate() {
		return idPrivate;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getCards() {
		return cards;
	}
	public void setCards(int cards) {
		this.cards = cards;
	}
	public int getNmbrLoans() {
		return nmbrLoans;
	}
	public void setNmbrLoans(int nmbrLoans) {
		this.nmbrLoans = nmbrLoans;
	}
	
	public void addPrivate(int selectList) {
		switch (selectList) {
		case 1:
			pCM.add(this);
			break;
		case 2:
			tellerPrivate.add(this);
			break;
		case 3:
			pCMGold.add(this);
			break;
		case 4:
			pCMDelays.add(this);
			break;	
		}	
	}
	public String toString() {
		return ("Costumer information:\nName: " + getName() 
				+"\nID: " + getIdPrivate() 
				+ "\nAmount: " + getAmount() 
				+ "\nCards: " + getCards() 
				+ "\nLoans: " + getNmbrLoans()) ;
	}
}
