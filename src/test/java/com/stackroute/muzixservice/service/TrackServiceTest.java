package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    private Track track;

    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list= null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackId(1);
        track.setTrackName("sai");
        track.setTrackComments("hahahahhaahaha");
        list = new ArrayList<>();
        list.add(track);
    }


        @Test
        public void saveTrackTestSuccess ()throws TrackAlreadyExistsException {

            when(trackRepository.save((Track) any())).thenReturn(track);
            Track savedTrack = trackService.saveTrack(track);
            Assert.assertEquals(track, savedTrack);

            //verify here verifies that userRepository save method is only called once
            verify(trackRepository, times(1)).save(track);

        }

        @Test(expected = TrackAlreadyExistsException.class)
        public void saveTrackTestFailure () throws TrackAlreadyExistsException {
            when(trackRepository.save((Track) any())).thenReturn(null);
            Track savedTrack = trackService.saveTrack(track);
            System.out.println("savedUser" + savedTrack);
            Assert.assertEquals(track, savedTrack);


        }

        @Test
        public void getAllTrack () {

            trackRepository.save(track);
            //stubbing the mock to return specific data
            when(trackRepository.findAll()).thenReturn(list);
            List<Track> userlist = trackService.getAllTrack();
            Assert.assertEquals(list, userlist);
        }

        @Test
       public void deleteTrackSuccess()throws TrackNotFoundException {
       when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
       Track status=trackService.deleteTrack(track.getTrackId());
       Assert.assertEquals(null,status);
        }

        @Test(expected = TrackNotFoundException.class)
        public void deleteTrackFailure() throws TrackNotFoundException {
            when(trackRepository.existsById(track.getTrackId())).thenReturn(false);
            Track status=trackService.deleteTrack(track.getTrackId());
            Assert.assertNotSame(null,status);
    }
    @Test
    public void testUpdateTrackCommentsSuccess() throws TrackNotFoundException{

        when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
        when(trackRepository.save((Track)any())).thenReturn(track);
        track.setTrackComments("superb....");
        Track track1=trackService.updateComments(track);
        Assert.assertEquals("superb....",track1.getTrackComments());
    }
    @Test(expected = TrackNotFoundException.class)
    public void testUpdateTrackCommentsFailure() throws TrackNotFoundException{

        when(trackRepository.existsById(track.getTrackId())).thenReturn(false);
        when(trackRepository.save((Track)any())).thenReturn(track);
        track.setTrackComments("superb....");
        Track track1=trackService.updateComments(track);
        Assert.assertNotSame("superb....",track1.getTrackComments());
    }

    }
