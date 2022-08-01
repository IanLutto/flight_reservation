package com.lutomiah.flightreservation.fightreservation.service;

import com.lutomiah.flightreservation.fightreservation.entities.Flight;
import com.lutomiah.flightreservation.fightreservation.entities.Passenger;
import com.lutomiah.flightreservation.fightreservation.entities.Reservation;
import com.lutomiah.flightreservation.fightreservation.models.ApiResponse;
import com.lutomiah.flightreservation.fightreservation.models.ReservationRequest;
import com.lutomiah.flightreservation.fightreservation.repos.FlightRepository;
import com.lutomiah.flightreservation.fightreservation.repos.PassengerRepository;
import com.lutomiah.flightreservation.fightreservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public ResponseEntity<?> bookFlight(ReservationRequest reservationRequest) {

        ResponseEntity<?> responseEntity= null;
        ApiResponse apiResponse = new ApiResponse();
        //Make Payment

        Long flightId = reservationRequest.getFlightId();

        try {
            Flight flight = flightRepository.findById(flightId)
                    .orElseGet(()-> new Flight());

                Passenger passenger = new Passenger();

                passenger.setFirstName(reservationRequest.getPassengerFirstName());
                passenger.setMiddleName(reservationRequest.getPassengerMiddleName());
                passenger.setLastName(reservationRequest.getPassengerLastName());
                passenger.setEmail(reservationRequest.getPassengerEmail());
                passenger.setPhone(reservationRequest.getPassengerPhone());
                Passenger savedPassenger = passengerRepository.save(passenger);

                apiResponse.setResponseCode("00");
                apiResponse.setResponseBody(savedPassenger);

                Reservation reservation = new Reservation();
                reservation.setFlight(flight);
                reservation.setCheckedIn(false);
                reservation.setPassenger(savedPassenger);
                Reservation savedReservation = reservationRepository.save(reservation);

                apiResponse.setResponseCode("00");
                apiResponse.setResponseBody(savedReservation);

                responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return responseEntity;
    }
}
