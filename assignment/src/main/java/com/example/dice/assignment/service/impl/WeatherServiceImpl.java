package com.example.dice.assignment.service.impl;

import com.example.dice.assignment.exceptions.BadRequestException;
import com.example.dice.assignment.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.host}")
    private String rapidApiHost;

    private HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", rapidApiHost);
        return new HttpEntity<>(headers);
    }

    @Override
    public String getForecastSummaryByLocation(String locationName) throws BadRequestException {
        if (locationName == null || locationName.isEmpty()) {
            throw new BadRequestException("locationName cannot be null or empty.");
        }
        String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/" + locationName + "/summary/";
        try {
            return restTemplate.exchange(url, HttpMethod.GET, getHeaders(), String.class).getBody();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String getHourlyForecastByCity(String locationName) throws BadRequestException {
        if (locationName == null || locationName.trim().isEmpty()) {
            throw new BadRequestException("locationName cannot be null or empty.");
        }
        String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/" + locationName + "/hourly/";
        try {
            return restTemplate.exchange(url, HttpMethod.GET, getHeaders(), String.class).getBody();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
