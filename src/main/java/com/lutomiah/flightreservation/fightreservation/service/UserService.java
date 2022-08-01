package com.lutomiah.flightreservation.fightreservation.service;

import com.lutomiah.flightreservation.fightreservation.entities.User;

public interface UserService {

    User getUsersByEmail(String email);
}
