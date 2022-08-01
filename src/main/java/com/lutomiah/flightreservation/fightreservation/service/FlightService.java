package com.lutomiah.flightreservation.fightreservation.service;

import com.lutomiah.flightreservation.fightreservation.entities.Flight;

import java.util.List;

public interface FlightService {

    List<Flight> getFightsByFilter(String from, String to, String departureDate);

//    Flight getFightById(String flightId);
}
