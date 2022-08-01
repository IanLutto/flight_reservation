package com.lutomiah.flightreservation.fightreservation.service;

import com.lutomiah.flightreservation.fightreservation.models.ReservationRequest;
import org.springframework.http.ResponseEntity;

public interface ReservationService {

    public ResponseEntity bookFlight(ReservationRequest reservationRequest);
}
