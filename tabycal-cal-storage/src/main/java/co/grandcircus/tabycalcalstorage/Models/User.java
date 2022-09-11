package co.grandcircus.tabycalcalstorage.Models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {

    @Id
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Boolean adminStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        LocalDate date = LocalDate.parse(dateOfBirth);
        this.dateOfBirth = date;
    }

    
    public Boolean getAdminStatus() {
        return adminStatus;
    }    

    public void setAdminStatus(Boolean adminStatus) {
        this.adminStatus = adminStatus;
    }    

    public User() {}

    public User(String userName, String firstName, String lastName, LocalDate dateOfBirth, Boolean adminStatus) {
        this.userName = userName;        
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.adminStatus = adminStatus;
    }    

    @Override
    public String toString() {
        return "User [adminStatus=" + adminStatus + ", dateOfBirth=" + dateOfBirth + ", firstName="
                + firstName + ", id=" + id + ", lastName=" + lastName + ", userName=" + userName
                + "]";
    }            




}