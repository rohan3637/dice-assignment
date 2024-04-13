package com.example.dice.assignment.controller;

import com.example.dice.assignment.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/forecast/summary")
    public ResponseEntity<String> getForecastSummary(@RequestParam String locationName) {
        return new ResponseEntity<>(weatherService.getForecastSummaryByLocation(locationName), HttpStatus.OK);
    }

    @GetMapping("/forecast/hourly")
    public ResponseEntity<String> getHourlyForecast(@RequestParam String locationName) {
        return new ResponseEntity<>(weatherService.getHourlyForecastByCity(locationName), HttpStatus.OK);
    }

}

