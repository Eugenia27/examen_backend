package com.dh.apicatalog.controller.dto;

import com.dh.apicatalog.model.Movie;
import com.dh.apicatalog.model.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfflineCatalogDTO {

    private String genre;
    private List<Movie> movies;
    private List<Serie> series;
}


