package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Location;
import com.prabeshcodes.student.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }
}
