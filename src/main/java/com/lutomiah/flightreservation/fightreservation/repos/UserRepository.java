package com.lutomiah.flightreservation.fightreservation.repos;

import com.lutomiah.flightreservation.fightreservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
