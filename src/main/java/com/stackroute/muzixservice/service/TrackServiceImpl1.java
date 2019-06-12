package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
@EnableCaching
@CacheConfig(cacheNames = {"track"})
public class TrackServiceImpl1 implements TrackService {

    @Autowired
    TrackRepository trackRepository;

    public TrackServiceImpl1(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void simulateDelay(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Adding track to trackDb
    @CacheEvict(allEntries = true)
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        Track savedTrack = null;
        if (trackRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException("Track already exists");
        } else {
            savedTrack = trackRepository.save(track);
            if (savedTrack == null) {
                throw new TrackAlreadyExistsException("track not empty");
            }
            return savedTrack;

        }
    }

    //Getting all the Tracks
    @Cacheable("track")
    @Override
    public List<Track> getAllTrack() {
        simulateDelay();
        return trackRepository.findAll();
    }


    //Deleting a track from trackDb
    @CacheEvict(allEntries = true)
    @Override
    public Track deleteTrack(int id) throws TrackNotFoundException{
        if (trackRepository.existsById(id)){
            trackRepository.deleteById(id);
        }
        else {
            throw new TrackNotFoundException("Exception in deleteTrack");
        }

        return null;
    }



    //Updating comments of a track
    @CacheEvict(allEntries = true)
    @Override
    public Track updateComments(Track track) throws TrackNotFoundException{
        if (trackRepository.existsById(track.getTrackId())) {
            Track track1 = trackRepository.findById(track.getTrackId()).get();
            track1.setTrackComments(track.getTrackComments());
            trackRepository.save(track1);
            return track1;
        } else {
            throw new TrackNotFoundException("track not found");
        }

    }

}
