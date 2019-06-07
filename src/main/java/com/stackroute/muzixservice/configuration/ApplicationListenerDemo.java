package com.stackroute.muzixservice.configuration;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationListenerDemo implements ApplicationListener<ContextRefreshedEvent>{

   /* private static final Logger logs = LoggerFactory.getLogger(ApplicationListenerDemo.class);*/
    private TrackRepository trackRepository;

    @Autowired
    private Environment env;
    public ApplicationListenerDemo(TrackRepository trackRepository, Environment env){
        this.trackRepository=trackRepository;

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
       /* logs.info("Inserting data on start");*/

        /*Track track = new Track("1","Sai","Singer : lucky");
        trackRepository.save(track);*/
        /*Track trackTwo = new Track("2","duffer","Singer : dusky");
        trackRepository.save(trackTwo);*/
        Track track = new Track(env.getProperty("app.trackID"),env.getProperty("app.trackName"),env.getProperty("app.trackComments"));
        trackRepository.save(track);
        /*logs.info("data successfully inserted");*/
    }
}