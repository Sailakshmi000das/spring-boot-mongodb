package com.stackroute.muzixservice.configuration;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerDemo implements CommandLineRunner {

    @Value("${track1.id}")
    private String id;
    @Value("${track1.name}")
    private String name;
    @Value("${track1.comment}")
    private String comments;

    @Autowired
    TrackRepository trackRepository;
    Track track=new Track();


   /* public CommandLineRunnerDemo(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }*/

    @Override
    public void run(String... args) throws Exception{
        track.setTrackId(id);
        track.setTrackName(name);
        track.setTrackComments(comments);
        trackRepository.save(track);

    }
}