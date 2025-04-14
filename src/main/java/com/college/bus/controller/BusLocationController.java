
package com.college.bus.controller;

import com.college.bus.entity.BusLocation;
import com.college.bus.service.BusLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@RequestMapping("/bus")
public class BusLocationController {

    @Autowired
    private BusLocationService busLocationService;

    @PostMapping("/update-location")
    @PreAuthorize("hasAuthority('ROLE_DRIVER')")
    public BusLocation updateBusLocation(@RequestBody BusLocation busLocation) {
    	
        return busLocationService.saveBusLocation(busLocation);
    }
    
    
    
    
    @GetMapping("/location/{busNumber}")
    
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_DRIVER')")

    public Optional<BusLocation> getBusLocation(@PathVariable String busNumber) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Logged-in user: " + auth.getName());
        System.out.println("Roles: " + auth.getAuthorities());

        return busLocationService.getLatestLocation(busNumber);
    }
	/*
	 * @GetMapping("/location/{busNumber}")
	 * 
	 * public Optional<BusLocation> getBusLocation(@PathVariable String busNumber) {
	 * return busLocationService.getLatestLocation(busNumber); }
	 */
}



