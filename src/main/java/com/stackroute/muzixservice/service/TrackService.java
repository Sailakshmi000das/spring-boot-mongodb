package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTrack();
    public boolean deleteTrack(Track track) throws TrackNotFoundException;
    //public List<Track> retrieveTrackByName(String trackName) throws TrackNotFoundException;
    public Track updateComments(Track track) throws TrackNotFoundException;
}
