package com.example.Bookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	

 public User(String name, String pwd, String email, String role) {
	 this.username = name;
	 this.passwordHash = pwd;
	 this.email = email;
	 this.role = role;
 }
 
 public User() {
	 
 }
 
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id", nullable = false, updatable = false)
 private Long id;
// Username with unique constraint
 @Column(name = "username", nullable = false, unique = true)
 private String username;
 @Column(name = "password", nullable = false)
 private String passwordHash;
 @Column(name = "email", nullable = false)
 private String email;
 @Column(name = "role", nullable = false)
 private String role;

 




public void setId(Long id) {
	this.id = id;
}

public void setUsername(String username) {
	this.username = username;
}

public void setPasswordHash(String passwordHash) {
	this.passwordHash = passwordHash;
}

public void setEmail(String email) {
	this.email = email;
}

public void setRole(String role) {
	this.role = role;
}

public String getPasswordHash() {
	
	return passwordHash;
}


public String getRole() {
	
	return role;
}



public String getUsername() {
	
	return username;
}
}
