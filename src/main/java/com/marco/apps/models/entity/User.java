package com.marco.apps.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean isPremium;
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = new Date();
    }

    @Temporal(TemporalType.DATE)
    private Date premiumExpiration;


    public User(){

    }
    public User(Long id, String username, String password, String firstName, String lastName, String email, Boolean isPremium, Date createdAt, Date premiumExpiration) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isPremium = isPremium;
        this.createdAt = createdAt;
        this.premiumExpiration = premiumExpiration;
    }

    public User(String username, String password, String firstName, String lastName, String email, Boolean isPremium, Date createdAt, Date premiumExpiration) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isPremium = isPremium;
        this.createdAt = createdAt;
        this.premiumExpiration = premiumExpiration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPremiumExpiration() {
        return premiumExpiration;
    }

    public void setPremiumExpiration(Date premiumExpiration) {
        this.premiumExpiration = premiumExpiration;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", isPremium=" + isPremium +
                ", createdAt=" + createdAt +
                ", premiumExpiration=" + premiumExpiration +
                '}';
    }
}
