
package com.college.bus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String driverName;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String licenseNumber;
    
    @Column(name = "password", nullable = false)
    private String password;  // Ensure this field exists

    // Other fields...

    // Getters and Setters
   

    

    @OneToOne
    @JoinColumn(name = "bus_id", unique = true)
    private Bus bus;

    public Driver() {}

    public Driver(String driverName, String phone, String licenseNumber, Bus bus) {
        this.driverName = driverName;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.bus = bus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public Bus getBus() { return bus; }
    public void setBus(Bus bus) { this.bus = bus; }

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	public void setPassword(String password) {
        this.password = password;
    }
}
