package com.lutomiah.flightreservation.fightreservation.repos;

import com.lutomiah.flightreservation.fightreservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
