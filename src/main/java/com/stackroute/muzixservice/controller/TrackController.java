package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
public class TrackController {

    @Autowired
    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //Adding track to trackDb
    @PostMapping("track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            Track track1 = trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully Added Track", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
    return responseEntity;
    }
    //displaying all the tracks
    @GetMapping("tracks")
    public ResponseEntity<List<Track>> showAllTracks() {
        List<Track> track1 = trackService.getAllTrack();
        return new ResponseEntity<List<Track>>(track1, HttpStatus.OK);
    }

    //Updating comments of a track
    @PutMapping("track/{id}")
    public ResponseEntity<Track> updateTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            Track track1 = trackService.updateComments(track);
            return new ResponseEntity<Track>(track1, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
    return responseEntity;
    }
    //Deleting a track
    @DeleteMapping("track/{id}")
    public ResponseEntity<String> deleteTrack(@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        try {
            Track track = trackService.deleteTrack(id);
            return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return responseEntity;
    }



}



