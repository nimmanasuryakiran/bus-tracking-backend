
package com.college.bus.service;

import com.college.bus.entity.BusLocation;
import com.college.bus.repository.BusLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BusLocationService {

    @Autowired
    private BusLocationRepository busLocationRepository;

    
    public BusLocation saveBusLocation(BusLocation busLocation) {
        if (busLocation.getTimestamp() == null) {
            busLocation.setTimestamp(LocalDateTime.now());
        }
        return busLocationRepository.save(busLocation);
    }

    
    public Optional<BusLocation> getLatestLocation(String busNumber) {
        return busLocationRepository.findTopByBusNumberOrderByTimestampDesc(busNumber);
    }


	/*
	 * // Get the latest location of a specific bus public Optional<BusLocation>
	 * getLatestLocation(String busNumber) { List<BusLocation> locations =
	 * busLocationRepository.findTopByBusNumberOrderByTimestampDesc(busNumber);
	 * return locations.isEmpty() ? Optional.empty() :
	 * Optional.of(locations.get(0)); }
	 */
}
