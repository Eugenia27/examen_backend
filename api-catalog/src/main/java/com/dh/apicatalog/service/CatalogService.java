package com.dh.apicatalog.service;

import com.dh.apicatalog.event.NewMovieEventConsumer;
import com.dh.apicatalog.event.NewSerieEventConsumer;
import com.dh.apicatalog.model.Movie;
import com.dh.apicatalog.model.Serie;
import com.dh.apicatalog.repository.MovieRepository;
import com.dh.apicatalog.repository.SerieRepository;
import com.dh.apicatalog.client.MovieServiceClient;
import com.dh.apicatalog.client.SerieServiceClient;
import com.dh.apicatalog.controller.dto.OnlineCatalogDTO;
import com.dh.apicatalog.controller.dto.OfflineCatalogDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    MovieServiceClient movieServiceClient;
    @Autowired
    SerieServiceClient serieServiceClient;
    @Autowired
    SerieRepository serieRepository;
    @Autowired
    MovieRepository movieRepository;

    // Modo online
    public OnlineCatalogDTO getCatalogByGenreOnline(String genre) {
        List<MovieServiceClient.MovieDTO> moviesResponse = movieServiceClient.getMovieByGenre(genre);
        List<SerieServiceClient.SerieDTO> seriesResponse = serieServiceClient.getSerieByGenre(genre);
        OnlineCatalogDTO catalog = new OnlineCatalogDTO();
        catalog.setGenre(genre);
        catalog.setMovies(moviesResponse);
        catalog.setSeries(seriesResponse);
        return catalog;
    }

    // Modo offline
    public OfflineCatalogDTO getCatalogByGenreOffline(String genre) {
        List<Movie> moviesResponse = movieRepository.findByGenre(genre);
        List<Serie> seriesResponse = serieRepository.findByGenre(genre);
        OfflineCatalogDTO catalog = new OfflineCatalogDTO();
        catalog.setGenre(genre);
        catalog.setMovies(moviesResponse);
        catalog.setSeries(seriesResponse);
        return catalog;
    }

    public String createMovie(NewMovieEventConsumer.MessageMovie message) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(message, movie);
        movieRepository.save(movie);
        return movie.getId();
    }

    public String createSerie(NewSerieEventConsumer.MessageSerie message) {
        Serie serie = new Serie();
        BeanUtils.copyProperties(message, serie);
        serieRepository.save(serie);
        return serie.getId();
    }


}
