package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId(5);
        track.setTrackName("sai");
        track.setTrackComments("Reddy");

    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }


    @Test
    public void saveTrack(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        System.out.println(fetchTrack);
        Assert.assertEquals(5,fetchTrack.getTrackId());

    }

    @Test
    public void saveTrackFailure(){
        Track testTrack = new Track(1,"lucky","hahhahahhahhah");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }
    @Test
    public void GetAllTrack(){
        Track track1 = new Track(2,"fhgh","etryuu");
        Track track2 = new Track(3,"uioijj","dsdgh");
        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals(2,list.get(0).getTrackId());
    }
    @Test
    public void deleteTrack(){
        trackRepository.delete(track);
        Assert.assertEquals(false,trackRepository.existsById(track.getTrackId()));
    }
    @Test
    public void deleteTrackFailure(){
        trackRepository.delete(track);
        Assert.assertNotSame(true,trackRepository.existsById(track.getTrackId()));
    }

    @Test
    public void testUpdateTrackSuccess() {
        trackRepository.save(track);
        trackRepository.findById(track.getTrackId()).get().setTrackComments("saii saiii");
        List<Track> list=trackRepository.findAll();
        Assert.assertEquals("saii saiii",list.get(0).getTrackComments());
    }

    @Test                                                                                                  //test for failure of update method
    public void testUpdateTrackFailure() {
        // Track testUser = new Track(1, "aadi", "good album");
        trackRepository.save(track);
        trackRepository.findById(track.getTrackId()).get().setTrackComments("saii saiii");
        List<Track> list=trackRepository.findAll();
        Assert.assertNotSame("saii saii",list.get(0).getTrackComments());
    }


}
