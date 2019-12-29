package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomersList {


    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty loans;
    private final StringProperty amount;
    private final StringProperty type;


    public CustomersList(String id, String name, String loans, String amount, String type) {
        this.id = new SimpleStringProperty(id) ;
        this.name = new SimpleStringProperty(name);
        this.loans = new SimpleStringProperty(loans);
        this.amount = new SimpleStringProperty(amount);
        this.type = new SimpleStringProperty(type);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLoans() {
        return loans.get();
    }

    public StringProperty loansProperty() {
        return loans;
    }

    public void setLoans(String loans) {
        this.loans.set(loans);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
