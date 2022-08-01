package com.lutomiah.flightreservation.fightreservation.service;

import com.lutomiah.flightreservation.fightreservation.entities.User;
import com.lutomiah.flightreservation.fightreservation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUsersByEmail(String email) {
        try {

            return userRepository.findByEmail(email);

        }catch (Exception e){

            return null;

        }
    }
}
