
package com.college.bus.controller;
import com.college.bus.dto.BusLocationDto;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.concurrent.ConcurrentHashMap;


import com.college.bus.model.Bus;
import com.college.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buses")
@CrossOrigin("*")
public class BusController {
	


    @Autowired
    private BusService busService;
    private final ConcurrentHashMap<Long, BusLocationDto> busLocations = new ConcurrentHashMap<>();
    
    
    @PostMapping("/update-location")
    public String updateBusLocation(@RequestBody BusLocationDto busLocationDto) {
        busLocations.put(busLocationDto.getBusId(), busLocationDto);
        return "Bus location updated successfully!";
    }
    
    @GetMapping("/location/{busId}")
    public BusLocationDto getBusLocation(@PathVariable Long busId) {
        return busLocations.getOrDefault(busId, new BusLocationDto(busId, 0.0, 0.0));
    }




    @PostMapping("/add")
    public Bus addBus(@RequestBody Bus bus) {
        return busService.addBus(bus);
    }

    @GetMapping("/{id}")
    public Bus getBusById(@PathVariable Long id) {
        return busService.getBusById(id);
    }

    @GetMapping("/number/{busNumber}")
    public Bus getBusByNumber(@PathVariable String busNumber) {
        return busService.getBusByNumber(busNumber);
    }

    @GetMapping
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }

    @DeleteMapping("/{id}")
    public void deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
    }
}
