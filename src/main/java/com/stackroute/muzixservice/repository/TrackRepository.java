package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Track;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track ,String> {

  /* *//* public List<Track> getTrackByName(String trackName);
    *//*@Query(value = "select t from Track t where t.trackName=:trackName")
    public List<Track> retrieveTrackByName(String trackName);*/
}
