package com.lutomiah.flightreservation.fightreservation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity{

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
//    @Column(name = "email")
    private String email;
//    @Column(name = "password")
    private String password;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
