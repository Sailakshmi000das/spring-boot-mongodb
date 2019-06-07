package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
public class TrackController {


    private TrackService trackService;

    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }

   @PostMapping("track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            Track track1=trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully Added Track", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }


    @GetMapping("track")
    public ResponseEntity<List<Track>> showAllTracks()
    {
        List<Track> track1=trackService.getAllTrack();
        return new ResponseEntity<List<Track>>(track1,HttpStatus.OK);
    }
    @PutMapping("track/{id}")
    public ResponseEntity<Track> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            Track track1=trackService.updateComments(track);
            return new ResponseEntity<Track>(track1,HttpStatus.OK);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }


    @DeleteMapping("track/{id}")
    public ResponseEntity<String> deleteTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            boolean answer=trackService.deleteTrack(track);
            return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }


    /*@GetMapping("track/{trackName}")
    public ResponseEntity<List<Track>> getTrackByName(@PathVariable("trackName") String trackName) throws Exception
    {
        List<Track> track1=trackService.retrieveTrackByName(trackName);
        return new ResponseEntity<List<Track>>(track1, HttpStatus.OK);
    }*/

}


