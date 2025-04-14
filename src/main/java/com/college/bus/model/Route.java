
package com.college.bus.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String routeName;

    @Column(nullable = false)
    private String startLocation;

    @Column(nullable = false)
    private String endLocation;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnoreProperties({"route"})
    @JsonBackReference
    private List<Bus> buses;

    public Route() {}

    public Route(String routeName, String startLocation, String endLocation) {
        this.routeName = routeName;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }

    public String getStartLocation() { return startLocation; }
    public void setStartLocation(String startLocation) { this.startLocation = startLocation; }

    public String getEndLocation() { return endLocation; }
    public void setEndLocation(String endLocation) { this.endLocation = endLocation; }

    public List<Bus> getBuses() { return buses; }
    public void setBuses(List<Bus> buses) { this.buses = buses; }
}
