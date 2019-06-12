package com.stackroute.muzixservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;

    private List<Track> list = null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
        track = new Track();
        track.setTrackId(26);
        track.setTrackName("Jonny");
        track.setTrackComments("cute");
        list = new ArrayList();
        list.add(track);
    }


    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void saveTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void saveTrackFailure() throws Exception {
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTrack() throws Exception {
        when(trackService.getAllTrack()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteTrack() throws Exception {
        when(trackService.deleteTrack(track.getTrackId())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/26")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void deleteTrackFailure() throws Exception {
        when(trackService.deleteTrack(track.getTrackId())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/26")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void updateTrack() throws Exception {
        when(trackService.updateComments(track)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/26")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void updateTrackFailure() throws Exception {
        when(trackService.updateComments(track)).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/26")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());


    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}