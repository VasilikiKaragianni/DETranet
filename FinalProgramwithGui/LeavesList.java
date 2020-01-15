package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LeavesList {

    private final StringProperty startDate;
    private final StringProperty endDate;
    private final StringProperty days;
    private final StringProperty approved;

    public LeavesList(String startDate, String endDate, String days, String approved) {
        this.startDate = new SimpleStringProperty(startDate);
        this.endDate = new SimpleStringProperty(endDate);
        this.days = new SimpleStringProperty(days);
        this.approved = new SimpleStringProperty(approved);
    }

    public String getStartDate() {
        return startDate.get();
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getEndDate() {
        return endDate.get();
    }

    public StringProperty endDateProperty() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public String getDays() {
        return days.get();
    }

    public StringProperty daysProperty() {
        return days;
    }

    public void setDays(String days) {
        this.days.set(days);
    }

    public String getApproved() {
        return approved.get();
    }

    public StringProperty approvedProperty() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved.set(approved);
    }
}
