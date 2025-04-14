
package com.college.bus.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "buses")
@JsonIgnoreProperties({"students"})
public class Bus {
	
	
	@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Student> students;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String busNumber;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private String driverPhone;

    

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id", nullable= true)
    @JsonBackReference
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties({"buses"})
    @JsonManagedReference 
    private Route route;

    public Bus() {}

    public Bus(String busNumber, String driverName, String driverPhone, Route route) {
        this.busNumber = busNumber;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.route = route;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getDriverPhone() { return driverPhone; }
    public void setDriverPhone(String driverPhone) { this.driverPhone = driverPhone; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }

    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }
}
