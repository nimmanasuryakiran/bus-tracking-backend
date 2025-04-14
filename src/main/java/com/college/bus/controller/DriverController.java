package com.college.bus.controller;

import com.college.bus.model.Driver;
import com.college.bus.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@CrossOrigin("*")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/add")
    public Driver addDriver(@RequestBody Driver driver) {
        return driverService.addDriver(driver);
    }

	/*
	 * @GetMapping("/{id}") public Driver getDriverById(@PathVariable Long id) {
	 * return driverService.getDriverById(id); }
	 */
    @GetMapping("/phone/{phone}")
    public Driver getDriverByPhone(@PathVariable String phone) {
        return driverService.getDriverByPhone(phone);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable Long id) {
        Driver driver = driverService.getDriverById(id);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");
        }
    }


    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }
}
