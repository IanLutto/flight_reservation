package com.lutomiah.flightreservation.fightreservation.repos;

import com.lutomiah.flightreservation.fightreservation.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
