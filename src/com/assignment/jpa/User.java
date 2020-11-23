package com.assignment.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "User")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

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
}
