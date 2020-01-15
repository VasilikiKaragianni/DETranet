package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeesList {

    private final StringProperty id;
    private final StringProperty fullname;
    private final StringProperty department;
    private final StringProperty firstDate;
    private final StringProperty email;

    public EmployeesList(String id, String fullname, String department, String firstDate, String email) {

        this.id = new SimpleStringProperty(id);
        this.fullname = new SimpleStringProperty(fullname);
        this.department = new SimpleStringProperty(department);
        this.firstDate = new SimpleStringProperty(firstDate);
        this.email = new SimpleStringProperty(email);

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

    public String getFullname() {
        return fullname.get();
    }

    public StringProperty fullnameProperty() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname.set(fullname);
    }

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getFirstDate() {
        return firstDate.get();
    }

    public StringProperty firstDateProperty() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate.set(firstDate);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
