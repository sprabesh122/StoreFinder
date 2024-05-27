package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Location;
import com.prabeshcodes.student.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins = "http://localhost:3000")
public class LocationController {
    @Autowired
    private LocationService locationService;

    // Logic to get location by ID
    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }
}