package org.dal.cs5308.t20.Project.course.bo;

import com.opencsv.bean.CsvBindByPosition;
import org.dal.cs5308.t20.Project.user.User;

public class Student {
    @CsvBindByPosition(position = 0)
    private String bannerId;

    @CsvBindByPosition(position = 1)
    private String firstName;

    @CsvBindByPosition(position = 2)
    private String lastName;

    @CsvBindByPosition(position = 3)
    private String email;

    public Student() {
    }

    public Student(String bannerId, String firstName, String lastName, String email) {
        this.bannerId = bannerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
