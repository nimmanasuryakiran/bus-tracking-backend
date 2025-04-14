
package com.college.bus.service.impl;

import com.college.bus.model.Driver;
import com.college.bus.repository.DriverRepository;
import com.college.bus.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public Driver getDriverByPhone(String phone) {
        return driverRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Driver not found with phone: " + phone));
    }


    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
