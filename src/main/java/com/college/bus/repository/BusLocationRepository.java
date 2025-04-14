package com.college.bus.repository;

import com.college.bus.entity.BusLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusLocationRepository extends JpaRepository<BusLocation, Long> {

    // Fetch the latest location of a specific bus
    Optional<BusLocation> findTopByBusNumberOrderByTimestampDesc(String busNumber);
}
