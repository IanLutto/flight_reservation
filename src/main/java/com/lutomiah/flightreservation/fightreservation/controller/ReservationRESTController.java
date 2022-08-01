package com.lutomiah.flightreservation.fightreservation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lutomiah.flightreservation.fightreservation.entities.Reservation;
import com.lutomiah.flightreservation.fightreservation.models.ApiResponse;
import com.lutomiah.flightreservation.fightreservation.repos.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class ReservationRESTController {
    @Autowired
    ReservationRepository reservationRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRESTController.class);

    @RequestMapping(value = "/findReservation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findReservation(@PathVariable("id") Long id) throws JsonProcessingException {
        LOGGER.info("REQUEST PAYLOAD {}", id);
        ApiResponse apiResponse = new ApiResponse();

        Optional<Reservation> reservation = reservationRepository.findById(id);

        if (reservation.isPresent()){
            LOGGER.info("WELL..." + reservation.get().getNumberOfBags());
            apiResponse.setResponseCode("00");
            apiResponse.setResponseBody(reservation);
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(apiResponse) , HttpStatus.OK);
        }
        apiResponse.setResponseCode("01");
        apiResponse.setMessage("No reservation found with id " + id);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @RequestMapping(value = "/updateReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateReservation(@RequestBody Map<String, String> requestMap){
        LOGGER.info("REQUEST PAYLOAD {}", requestMap);
        ApiResponse apiResponse = new ApiResponse();
        Long id = Long.valueOf(requestMap.get("Id").trim());
        Boolean checkedIn = Boolean.valueOf(requestMap.get("checkedIn").trim());
        int noOfBags = Integer.parseInt(requestMap.get("numberOfBags").trim());

        Optional<Reservation> reservation = reservationRepository.findById(id);

        if (reservation.isPresent()){
            LOGGER.info("RESERVATION PRESENT");
            reservation.get().setCheckedIn(checkedIn);
            reservation.get().setNumberOfBags(noOfBags);
            reservationRepository.save(reservation.get());

            apiResponse.setResponseCode("00");
            apiResponse.setResponseBody(reservation);
        }else {
            LOGGER.info("EESERVATION !PRESENT");
            apiResponse.setResponseCode("01");
            apiResponse.setMessage("There is no reservation of id " + id);
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
