package com.lutomiah.flightreservation.fightreservation.service;

import com.lutomiah.flightreservation.fightreservation.entities.Flight;
import com.lutomiah.flightreservation.fightreservation.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> getFightsByFilter(String from, String to, String departureDate) {
        try {
            return flightRepository.findFlight(from, to, departureDate);
        }catch (Exception e){
            return null;
        }
    }

}
