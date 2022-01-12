package com.ale.third.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;

    public void getWeather(String city) {
//       restTemplate.getForEntity();
    }
}
