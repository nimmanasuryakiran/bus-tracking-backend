
package com.college.bus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
   

    @ManyToOne(fetch = FetchType.EAGER)  // Force Hibernate to fetch Bus details
    @JoinColumn(name = "bus_id")
    private Bus bus;


    // Constructors
    public Student() {}

    public Student(String registrationNumber, String name, String email, String password, Bus bus) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bus = bus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Bus getBus() { return bus; }
    public void setBus(Bus bus) { this.bus = bus; }
}
