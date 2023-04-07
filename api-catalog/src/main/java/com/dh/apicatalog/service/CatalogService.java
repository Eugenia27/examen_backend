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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @CircuitBreaker(name = "showCatalog", fallbackMethod = "showCatalogFallBack")
    @Retry(name = "retryCatalog")
    public OnlineCatalogDTO getCatalogByGenreOnline(String genre) {
    //public Optional<T> getCatalogByGenreOnline(String genre) {
        List<MovieServiceClient.MovieDTO> moviesResponse = movieServiceClient.getMovieByGenre(genre);
        List<SerieServiceClient.SerieDTO> seriesResponse = serieServiceClient.getSerieByGenre(genre);
        OnlineCatalogDTO catalog = new OnlineCatalogDTO();
        catalog.setGenre(genre);
        catalog.setMovies(moviesResponse);
        catalog.setSeries(seriesResponse);
        return catalog;
    }

//    public OnlineCatalogDTO showCatalogFallBack(String genre, Throwable t) throws Exception{
//        throw new Exception("Some service is not working");
//    }


    public OnlineCatalogDTO showCatalogFallBack(String genre, Throwable t) {
        //throw new CardException(MessageError.CUSTOMER_SERVICE_UNAVAILABLE);
        OnlineCatalogDTO onlineCatalogDTO = new OnlineCatalogDTO();
        OfflineCatalogDTO offlineCatalogDTO = new OfflineCatalogDTO();
        BeanUtils.copyProperties(offlineCatalogDTO, onlineCatalogDTO);
        //offlineCatalogDTO = getCatalogByGenreOffline(genre);
        //onlineCatalogDTO.setGenre(genre);
        //onlineCatalogDTO.setMovies((MovieServiceClient.MovieDTO)offlineCatalogDTO.getMovies());
        //onlineCatalogDTO.setSeries(offlineCatalogDTO.getSeries());
        return onlineCatalogDTO;
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
