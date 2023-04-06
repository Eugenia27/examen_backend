package com.dh.apimovie.service;

import com.dh.apimovie.event.NewMovieEventProducer;
import com.dh.apimovie.model.Movie;
import com.dh.apimovie.repository.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private NewMovieEventProducer newMovieEventProducer;

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        //NewMovieEventProducer.Message message = new NewMovieEventProducer.Message();
        //message.setGenre(movie.getGenre());
        //message.setName(movie.getName());
        //message.setUrlStream(movie.getUrlStream());
        newMovieEventProducer.publishNewMovieEvent(movie);
        return movieRepository.save(movie);
    }
}
