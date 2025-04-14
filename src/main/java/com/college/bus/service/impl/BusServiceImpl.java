
package com.college.bus.service.impl;

import com.college.bus.model.Bus;
import com.college.bus.repository.BusRepository;
import com.college.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Override
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Bus getBusById(Long id) {
        return busRepository.findById(id).orElse(null);
    }

    @Override
    public Bus getBusByNumber(String busNumber) {
        return busRepository.findByBusNumber(busNumber);
    }

    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @Override
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }
}
