package com.mycompany.myapp.service.dto;

/**
 * Created by ibara on 2/24/2017.
 */
public class DateTimeDao {

    private String dateTime;

    public DateTimeDao(String dateTime) {
        this.dateTime = dateTime;
    }

    public DateTimeDao() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateTimeDao)) return false;

        DateTimeDao that = (DateTimeDao) o;

        return dateTime != null ? dateTime.equals(that.dateTime) : that.dateTime == null;
    }

    @Override
    public int hashCode() {
        return dateTime != null ? dateTime.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DateTimeDao{" +
            "dateTime='" + dateTime + '\'' +
            '}';
    }
}
