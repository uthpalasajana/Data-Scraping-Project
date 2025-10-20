package com.MovieDataScraper.MovieDataScraper.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data//this annotations are used for generate getter,setter methods and constructors
public class MovieDto {
//this is the class that contains the data that we need
    private String title;
    private String poster;
    private String type;
    private String time;
    private String description;

}
