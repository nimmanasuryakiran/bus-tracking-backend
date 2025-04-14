
package com.college.bus.service;

import com.college.bus.model.Driver;
import java.util.List;

public interface DriverService {
    Driver addDriver(Driver driver);
    Driver getDriverById(Long id);
    Driver getDriverByPhone(String phone);
    List<Driver> getAllDrivers();
    void deleteDriver(Long id);
}
