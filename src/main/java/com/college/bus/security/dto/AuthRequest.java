package com.college.bus.security.dto;

public class AuthRequest {
    private String registrationNumber;
    private String password;
    private String role;
    private String username;
    
    // Constructors
    public AuthRequest() {}

    public AuthRequest(String registrationNumber, String password, String role) {
        this.registrationNumber = registrationNumber;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public void setUsername(String username) {
        this.username = username;
    }
	
	
}




