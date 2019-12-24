package detranet;

import java.util.ArrayList;

/* Class for business customers */
public class Business {
	/* Business customer's characteristics */
	private static int count = 1;
	private String name;
	private String type;
	private int idBusiness;
	private double amount;
	private int nmbrLoans;
	/* Lists for each employee */
	public static ArrayList<Business> bSM = new ArrayList<Business>();
	public static ArrayList<Business> cSM = new ArrayList<Business>();
	public static ArrayList<Business> cSMVip = new ArrayList<Business>();
	public static ArrayList<Business> cSMDelays = new ArrayList<Business>();
	public static ArrayList<Business> tellerBusiness = new ArrayList<Business>();

	public static ArrayList<Business> getTellerBusiness() {
		return tellerBusiness;
	}

	public static ArrayList<Business> getbSM() {
		return bSM;
	}

	public static ArrayList<Business> getcSM() {
		return cSM;
	}

	public static ArrayList<Business> getcSMGold() {
		return cSMVip;
	}

	public static ArrayList<Business> getcSMDelays() {
		return cSMDelays;
	}

	public Business(String name, String type, int idBusiness, double amount, int nmbrLoans) {
		super();
		this.name = name;
		this.type = type;
		this.idBusiness = count++;
		;
		this.amount = amount;
		this.nmbrLoans = nmbrLoans;
	}

	public int getNmbrLoans() {
		return nmbrLoans;
	}

	public void setNmbrLoans(int nmbrLoans) {
		this.nmbrLoans = nmbrLoans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIdBusiness() {
		return idBusiness;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	/* Method add customer depending the employee who manage the customer */
	public void addBusiness(int selectList) {
		switch (selectList) {
		case 1:
			bSM.add(this);
			break;
		case 2:
			cSM.add(this);
			break;
		case 3:
			cSMVip.add(this);
			break;
		case 4:
			cSMDelays.add(this);
			break;
		case 5:
			tellerBusiness.add(this);
		}
	}

	/* Method return customer's characteristics */
	public String toString() {
		return ("Costumer information:\nName: " + getName() + getType() + "\nID: " + getIdBusiness() + "\nAmount: "
				+ getAmount() + "\nLoans: " + getNmbrLoans());
	}
}
