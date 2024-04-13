package com.example.dice.assignment.service;

import java.io.IOException;

public interface WeatherService {

    String getForecastSummaryByLocation(String locationName);

    String getHourlyForecastByCity(String locationName);
}
