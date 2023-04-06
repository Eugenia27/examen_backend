package com.dh.apicatalog.controller.dto;

import com.dh.apicatalog.client.MovieServiceClient;
import com.dh.apicatalog.client.SerieServiceClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnlineCatalogDTO {

    private String genre;
    private List<MovieServiceClient.MovieDTO> movies;
    private List<SerieServiceClient.SerieDTO> series;
}
