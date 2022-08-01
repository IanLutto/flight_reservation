package com.lutomiah.flightreservation.fightreservation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lutomiah.flightreservation.fightreservation.entities.User;
import com.lutomiah.flightreservation.fightreservation.repos.UserRepository;
import com.lutomiah.flightreservation.fightreservation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
@Autowired
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody HashMap<String, String> requestMap) {
        LOGGER.info("Registration payload {} ", requestMap);

        String messo;
        Map<String, String> responseMap = new HashMap<>();

        try {
            User user = new User();

            if (requestMap.get("firstName").trim().equals("") || requestMap.get("lastName").trim().equals("") || requestMap.get("email").trim().equals("")
                    || requestMap.get("password").trim().equals("")) {

                messo = "Please ensure you have filled in all the fields";

                responseMap.put("responseMessage", messo);
                responseMap.put("responseCode", "01");

            }

            user.setFirstName(requestMap.get("firstName").trim());
            user.setLastName(requestMap.get("lastName").trim());
            user.setEmail(requestMap.get("email").trim());
            user.setPassword(encoder.encode(requestMap.get("password").trim()));
            LOGGER.info("ENcoded Password! " + user.getPassword());

            userRepository.save(user);

            responseMap.put("responseMessage", "Registered Successfully!!!");
            responseMap.put("responseCode", "00");
        } catch (Exception e) {
            LOGGER.info("Error " + e.getMessage());
            e.printStackTrace();
        }

        return new ResponseEntity(responseMap, HttpStatus.OK);

    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody HashMap<String, String> requestMap) throws JsonProcessingException {
        LOGGER.info("Registration payload {} ", requestMap);

        Map<String, Object> responseMap = new HashMap<>();

        String email = requestMap.get("email").trim();
        String password = requestMap.get("password").trim();

        try {
            User user = userService.getUsersByEmail(email);

            if (null == user) {
                responseMap.put("return_code", "01");
                responseMap.put("return_message", "Email " + email + " does not exist");

                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(responseMap), HttpStatus.OK);

            }

            if (password.equals(user.getPassword())) {
                LOGGER.info("<---USERNAME---> " + user.getFirstName());

                responseMap.put("responseCode", "00");
                responseMap.put("responseMessage", "Logged in Successfully!!!");

                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(responseMap), HttpStatus.OK);

            } else {
                responseMap.put("responseCode", "01");
                responseMap.put("responseMessage", "Wrong Credentials, Please try again");

                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(responseMap), HttpStatus.OK);

            }
        } catch (Exception e) {
            LOGGER.error("ERROR logging in");
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(responseMap), HttpStatus.OK);
    }
}