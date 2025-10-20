package com.MovieDataScraper.MovieDataScraper.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data//this annotations are used for generate getter,setter methods and constructors
public class TvShowDto {

    private String title;
    private String url;
}
