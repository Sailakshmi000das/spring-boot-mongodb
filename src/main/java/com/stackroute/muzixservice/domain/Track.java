package com.stackroute.muzixservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "track")
public class Track {

    @Id
    private int trackId;

    private String trackName;

    private String trackComments;
}
