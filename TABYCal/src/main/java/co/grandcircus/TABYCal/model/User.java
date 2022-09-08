package co.grandcircus.TABYCal.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {

    @Id
    private String id;
    @Indexed
    private String userName;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
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


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int birthDay, int birthMonth, int birthYear) {

        this.dateOfBirth = birthDay + "/" + birthMonth + "/" + birthYear;
    }

    public Boolean getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public User() {}

    public User(String userName, String firstName, String lastName, int dayBirth, int monthBirth,
            int yearBirth, String dateOfBirth, Boolean adminStatus) {
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

    public User orElseThrow(Object object) {
        return null;
    }


}
