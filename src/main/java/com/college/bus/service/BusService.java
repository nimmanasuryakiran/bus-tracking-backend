
package com.college.bus.service;

import com.college.bus.model.Bus;
import java.util.List;

public interface BusService {
    Bus addBus(Bus bus);
    Bus getBusById(Long id);
    Bus getBusByNumber(String busNumber);
    List<Bus> getAllBuses();
    void deleteBus(Long id);
}
